terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "3.0.2"
    }
  }
  required_version = ">= 0.14"
}

# provider "docker" {
#   host = "unix:///var/run/docker.sock"
# }

variable "db_user" {
  type        = string
  default     = "postgres"
  description = "The username for the database"
}

variable "db_password" {
  type        = string
  default     = "password"
  description = "The password for the database"
}

resource "docker_image" "db" {
  name = "postgres:latest"
  keep_locally = true
}

resource "docker_container" "db" {
  image = docker_image.db.image_id
  name  = "db"
  ports {
    internal = 5432
    external = 5432
  }
  healthcheck {
    test     = ["CMD-SHELL", "pg_isready -U ${var.db_user}"]
    interval = "10s"
    timeout  = "5s"
    retries  = 5
  }
  env = [
    "POSTGRES_USER=${var.db_user}",
    "POSTGRES_PASSWORD=${var.db_password}",
    "POSTGRES_DB=banking",
  ]
  restart = "unless-stopped"
}

output "db_port_external" {
  value = docker_container.db.ports[0].external
}
