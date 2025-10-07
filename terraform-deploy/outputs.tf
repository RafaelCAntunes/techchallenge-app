utput "service_hostname" {
  value = try(
    kubernetes_service.app.status[0].load_balancer[0].ingress[0].hostname,
    "pending"
  )
  description = "O hostname público do LoadBalancer. Pode demorar alguns minutos para aparecer após o apply."
}