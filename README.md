Microservice Account
=====

**Stack del proyecto:**

* Spring Boot
* SQL Server
* Kafka
* Docker

## Pre-Requisitos

* Tener java configurado.
* Tener docker y docker-compose instalados.
* En SQL Server, crear una base de datos llamada **`db_account`**.

## Ejecución del proyecto en local

* Levantar los servicios externos ejecutando **`make up`** en linux y **`docker-compose up`** en windows.
* Obtener detalle de la cuenta: **` Tipo GET - http://localhost:8081/v1/detail/{accountId}`**.
* Listar todas las cuentas: **`Tipo GET - http://localhost:8081/v1/list`**.
