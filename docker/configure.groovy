import jenkins.model.*
import hudson.slaves.Cloud
import org.csanchez.jenkins.plugins.kubernetes.PodTemplate
import org.csanchez.jenkins.plugins.kubernetes.ContainerTemplate
import org.csanchez.jenkins.plugins.kubernetes.KubernetesCloud
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

println "--> Configuring Kubernetes Cloud"
def createContainer(name, image, requestCpu, requestMemory) {
  container = new ContainerTemplate(image)
  container.setName(name)
  container.setCommand('/bin/sh -c')
  container.setArgs('cat')
  container.setTtyEnabled(true)
  container.setResourceRequestCpu(requestCpu)
  container.setResourceRequestMemory(requestMemory)
  container.setResourceLimitMemory(requestMemory)

  return container
}

template = new PodTemplate()
template.setLabel('kubernetes')
template.setName('kubernetes-agent')
template.setInstanceCap(3)
template.setIdleMinutes(10)

containers = []
containers.add(createContainer('ruby', 'ruby', '150m', '200Mi'))
containers.add(createContainer('python', 'python', '150m', '200Mi'))
template.setContainers(containers)

// Global Settings
server    = "https://kubernetes.default.svc"
namespace = "default"
address   = "http://jenkins.default.svc/"

c = new KubernetesCloud(
    "kubernetes", [template], server, namespace, address, "10", 5, 15, 5)
c.setSkipTlsVerify(true)

cloudList = Jenkins.getInstance().clouds
// avoid duplicate cloud provider on the cloud list
if (cloudList.getByName(c.name) ) {
  cloudList.remove(cloudList.getByName(c.name))
}
cloudList.add(c)

println "--> Creating example job"
jm = new JenkinsJobManagement(System.out, [:], new File('.'))
dslScriptLoader = new DslScriptLoader(jm)
exampleDslFile = new File("/usr/share/jenkins/dsl/example.dsl")
dslScriptLoader.runScript(exampleDslFile.text)
