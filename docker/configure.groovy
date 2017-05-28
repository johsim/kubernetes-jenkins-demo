import java.util.Collections;

import jenkins.model.*
import hudson.slaves.Cloud
import org.csanchez.jenkins.plugins.kubernetes.PodTemplate
import org.csanchez.jenkins.plugins.kubernetes.ContainerTemplate
import org.csanchez.jenkins.plugins.kubernetes.KubernetesCloud

println "--> Configuring Kubernetes Cloud"
def PodTemplate createTemplate(name, image, requestCpu, requestMemory) {
  // JNLP Container
  ContainerTemplate container = new ContainerTemplate(image)
  container.setName(name)
  container.setCommand('/bin/sh -c')
  container.setArgs('cat')
  container.setTtyEnabled(false)
  container.setResourceRequestCpu(requestCpu)
  container.setResourceRequestMemory(requestMemory)
  container.setResourceLimitMemory(requestMemory)

  List<ContainerTemplate> containers = new ArrayList<ContainerTemplate>()
  containers.add(container)

  PodTemplate pod = new PodTemplate()
  pod.setLabel(name)
  pod.setName(name)
  pod.setInstanceCap(3)
  pod.setContainers(containers)
  pod.setIdleMinutes(10)

  return pod
}

// Template List
List<PodTemplate> templates = new ArrayList<PodTemplate>()
templates.add(createTemplate('ruby', 'ruby', '150m', '1024Mi'))
templates.add(createTemplate('python', 'python', '150m', '1024Mi'))

// Global Settings
server    = "https://kubernetes.default.svc"
namespace = "default"
address   = "http://jenkins.default.svc/"
Cloud c = new KubernetesCloud(
    "kubernetes", templates, server, namespace, address, "10", 5, 15, 5)
c.setSkipTlsVerify(true)

cloudList = Jenkins.getInstance().clouds
// avoid duplicate cloud provider on the cloud list
if (cloudList.getByName(c.name) ) {
  cloudList.remove(cloudList.getByName(c.name))
}
cloudList.add(c)
