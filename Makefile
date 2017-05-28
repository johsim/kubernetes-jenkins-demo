build:
	cd docker && bash build.sh

init:
	minikube addons disable default-storageclass
	minikube ssh sudo -- mkdir -p /data/jenkins
	minikube ssh sudo -- chown 1000:1000 /data/jenkins

pv:
	kubectl apply -f pvc.yaml

deploy:
	kubectl delete -f deploy.yaml
	kubectl create -f deploy.yaml

svc:
	kubectl apply -f svc.yaml
	minikube service jenkins --url

all: init pv build deploy svc
