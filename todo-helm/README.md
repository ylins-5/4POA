# Lista de Tarefas — Helm + Umbrella + Kubernetes

## Estrutura
- app/                  -> aplicação (Node.js + API + HTML) e Dockerfile
- helm/todo-frontend    -> chart do front-end
- helm/sqlserver        -> chart do SQL Server
- helm/todo-umbrella    -> umbrella que une os dois

## 1. Build da imagem
    docker build -t todo-frontend:1.0.0 ./app
    # minikube: minikube image load todo-frontend:1.0.0
    # kind:     kind load docker-image todo-frontend:1.0.0

## 2. Instalar
    cd helm/todo-umbrella
    helm dependency update
    helm lint .
    helm install todo . -n todo --create-namespace

## 3. Acessar
    kubectl -n todo get pods -w
    kubectl -n todo port-forward svc/todo-frontend 8080:80
    # http://localhost:8080

## 4. Atualizar / remover
    helm upgrade todo . -n todo --set todo-frontend.replicaCount=2
    helm uninstall todo -n todo
    # o PVC do SQL Server permanece: kubectl -n todo delete pvc --all
