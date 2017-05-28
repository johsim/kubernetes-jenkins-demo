FROM jenkinsci/jenkins:2.62-alpine

RUN /usr/local/bin/install-plugins.sh \
  kubernetes:0.11 \
  workflow-aggregator:2.5 \
  workflow-remote-loader:1.4

COPY configure.groovy /usr/share/jenkins/ref/init.groovy.d/configure.groovy
