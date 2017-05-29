job('example') {
    label('kubernetes')
    steps {
        shell('echo "Hello, My name is Inigo Montoya"')
    }
}

pipelineJob('example-pipeline') {
    definition {
        cps {
            script("""
node('kubernetes') {
    stage('test1') {
        container('ruby') {
            sh('ruby --version')
        }
    }
    stage('test2') {
        container('python') {
            sh('python --version')
        }
    }
}
""")
            sandbox()
        }
    }
}
