resource "kubernetes_service" "app" {
  metadata {
    name      = "techchallenge-service"
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

