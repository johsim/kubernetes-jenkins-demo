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
    stage('ruby') {
        container('ruby') {
            sh('ruby --version')
        }
    }
    stage('python') {
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
