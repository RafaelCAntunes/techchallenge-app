resource "kubernetes_config_map" "app_config" {
  metadata {
    name      = "app-config"
    namespace = kubernetes_namespace.techchallenge.metadata[0].name
  }

  data = {
    "application.yml" = <<EOT
spring:
  datasource:
    url: jdbc:mysql://mysql-service:3306/tech_challenge
    username: \${SPRING_DATASOURCE_USERNAME}
    password: \${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
  sql:
    init:
      mode: always
EOT
  }
}