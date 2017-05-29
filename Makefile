build:
	cd docker && bash build.sh

pvc:
	kubectl apply -f pvc.yaml

deploy:
	kubectl delete -f deploy.yaml &>/dev/null || echo "Jenkins deployment does not exist"
	kubectl create -f deploy.yaml

svc:
	kubectl apply -f svc.yaml
	minikube service jenkins --url

all: build pvc deploy svc
