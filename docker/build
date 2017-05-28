#!/bin/bash
eval $(minikube docker-env)
docker build -t jenkins:demo .
docker images | grep "jenkins"
