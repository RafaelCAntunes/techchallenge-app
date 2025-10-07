terraform {
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.0"
    }
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0"
    }
  }
}

terraform {
  backend "remote" {
    organization = "techchallenge-lanchonete"

    workspaces {
      name = "techchallenge-infra-k8s"
    }
  }
}


provider "aws" {
  region = var.aws_region
}

data "terraform_remote_state" "eks" {
  backend = "remote"

  config = {
    organization = "techchallenge-lanchonete"
    workspaces = {
      name = "techchallenge-infra-k8s"
    }
  }
}

data "terraform_remote_state" "db" {
  backend = "remote"

  config = {
    organization = "techchallenge-lanchonete"
    workspaces = {
      name = "techchallenge-infra-db"
    }
  }
}

provider "kubernetes" {
  host                   = data.terraform_remote_state.eks.outputs.cluster_endpoint
  cluster_ca_certificate = base64decode(data.terraform_remote_state.eks.outputs.eks_cluster_ca_certificate)

}

