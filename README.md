# CD Meetup - Jenkins + Kubernetes = <3 ?

## Goals

* Get kubernetes running
* Build a custom jenkins master docker image with kubernetes plugin + custom configuration + example jobs
* Deploy to kubernetes
* Get kubernetes agents provisioned automatically for the example the jobs

## Requirements

* Minikube

## Intro

Quick intro on minikube

minikube start
minikube status
minikube ip
minikube ssh

Quick intro on kubectl

kubectl get pods
kubectl create -f <file>
kubectl apply -f <file>
kubectl delete -f <file>

## Steps

make build  # Builds docker image with kubernetes plugin and its configuration
            # docker/build.sh

make pvc    # Claim 100mi volume for jenkins usage 

make deploy # Deploys jenkins to kubernetes
            # deploy.yaml 

make svc    # Creates a Service matching the jenkins Pod created by the Deployment and return its url
            # svc.yaml 
