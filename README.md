# CD Meetup - Jenkins + Kubernetes = <3 ?

## Requirements

* Minikube

## Steps

make init # Configure minikube to disable dynamic provisioning of volumes (so it will use the one we create by hand)
          # Creates a directory in the minikube host to store jenkins data

make pvc    # Make the directory /data/jenkins available to kubernetes and claim it to jenkins
            # pvc.yaml

make build  # Builds docker image with kubernetes plugin and its configuration
            # docker/build.sh

make deploy # Deploys jenkins to kubernetes
            # deploy.yaml 

make svc    # Creates a Service matching the jenkins Pod created by the Deployment and return its url
            # svc.yaml 
