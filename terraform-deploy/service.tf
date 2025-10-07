resource "kubernetes_service" "app" {
  metadata {
    name      = "techchallenge-service"
    namespace = kubernetes_namespace.techchallenge.metadata[0].name
  }

  spec {
    selector = {
      app = "techchallenge-api"
    }

    port {
      port        = 80
      target_port = 8080
    }

    type = "LoadBalancer"
  }
}

