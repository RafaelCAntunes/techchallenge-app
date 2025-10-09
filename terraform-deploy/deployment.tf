resource "kubernetes_deployment" "app" {
  metadata {
    name      = "techchallenge-api"
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
            name  = "SPRING_DATASOURCE_URL"
            value = "jdbc:mysql://${data.terraform_remote_state.db.outputs.rds_endpoint}/lanchonete"  # URL do DB
          }

          env {
            name  = "SPRING_DATASOURCE_USERNAME"
            value = data.terraform_remote_state.db.outputs.db_username # Variável de usuário do DB
          }

          env {
            name  = "SPRING_DATASOURCE_PASSWORD"
            value = data.terraform_remote_state.db.outputs.db_password # Variável de senha do DB
          }

          env {
            name  = "SPRING_DATASOURCE_DRIVER_CLASS_NAME"
            value = "com.mysql.cj.jdbc.Driver"
          }

          env {
            name  = "SPRING_HIKARI_MAXIMUM_POOL_SIZE"
            value = "10"
          }

          env {
            name  = "SPRING_HIKARI_CONNECTION_TIMEOUT"
            value = "30000"
          }

          env {
            name  = "SPRING_HIKARI_IDLE_TIMEOUT"
            value = "600000"
          }

          env {
            name  = "SPRING_HIKARI_MAX_LIFETIME"
            value = "1800000"
          }

          env {
            name  = "SPRING_JPA_HIBERNATE_DDL_AUTO"
            value = "update"
          }

          env {
            name  = "SPRING_JPA_SHOW_SQL"
            value = "true"
          }

          env {
            name  = "SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT"
            value = "org.hibernate.dialect.MySQLDialect"
          }

          resources {
            limits = {
              cpu    = "300m"
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
            initial_delay_seconds = 90
            period_seconds        = 30
            failure_threshold     = 3
          }

          readiness_probe {
            http_get {
              path = "/actuator/health"
              port = 8080
            }
            initial_delay_seconds = 120
            period_seconds        = 15
            failure_threshold     = 6
          }
        }
      }
    }
  }
}