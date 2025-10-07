resource "kubernetes_deployment" "app" {
  metadata {
    name      = "techchallenge-api"
    namespace = kubernetes_namespace.techchallenge.metadata[0].name
    labels = {
      app = "techchallenge-api"
    }
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "techchallenge-api"
      }
    }

    template {
      metadata {
        labels = {
          app = "techchallenge-api"
        }
      }

      spec {
        container {
          name  = "techchallenge-api"
          image = "165835313479.dkr.ecr.us-east-1.amazonaws.com/techchallenge_lanchonete:latest"

          port {
            container_port = 8080
          }

          env {
            name = "SPRING_DATASOURCE_USERNAME"
            value_from {
              secret_key_ref {
                name = "mysql-credentials"
                key  = "app-username"
              }
            }
          }

          env {
            name = "SPRING_DATASOURCE_PASSWORD"
            value_from {
              secret_key_ref {
                name = "mysql-credentials"
                key  = "app-password"
              }
            }
          }

          env {
            name  = "SPRING_CONFIG_LOCATION"
            value = "/config/application.yml"
          }

          volume_mount {
            name       = "app-config"
            mount_path = "/config"
            read_only  = true
          }

          resources {
            limits = {
              cpu    = "1"
              memory = "1Gi"
            }
            requests = {
              cpu    = "200m"
              memory = "512Mi"
            }
          }

          liveness_probe {
            http_get {
              path = "/actuator/health"
              port = 8080
            }
            initial_delay_seconds = 30
            period_seconds        = 30
          }

          readiness_probe {
            http_get {
              path = "/actuator/health"
              port = 8080
            }
            initial_delay_seconds = 10
            period_seconds        = 10
            failure_threshold     = 6
          }
        }

        volume {
          name = "app-config"

          config_map {
            name = kubernetes_config_map.app_config.metadata[0].name
          }
        }
      }
    }
  }
}