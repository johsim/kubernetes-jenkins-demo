# CD Meetup - Jenkins + Kubernetes = <3 ?

## Requirements

* Minikube is installed

## Steps

make init # Configure minikube to disable dynamic provisioning of volumes (so it uses the one we create by hand)
          # Creates a directory in the minikube host to store jenkins data

make pv     # Make the directory /data/jenkins available to kubernetes and claim it to jenkins
make deploy # Deploys jenkins to kubernetes
make svc    # Creates a Service matching the jenkins Pod created by the Deployment and return its url
