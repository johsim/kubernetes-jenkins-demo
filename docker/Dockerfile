FROM jenkinsci/jenkins:2.62-alpine

RUN /usr/local/bin/install-plugins.sh \
  kubernetes:0.10 \
  workflow-aggregator:2.5 \
  workflow-remote-loader:1.4 \
  job-dsl:1.63

COPY configure.groovy /usr/share/jenkins/ref/init.groovy.d/configure.groovy

COPY example.dsl /usr/share/jenkins/dsl/example.dsl

USER root
