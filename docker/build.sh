#!/bin/bash
eval $(minikube docker-env) # Build the image in the context of the minikube VM
docker build -t jenkins:demo .
docker images | grep "jenkins"
