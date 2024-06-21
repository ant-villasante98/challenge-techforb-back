# Monitoreo de Plantas 

## Descripción

Este proyecto es una solución para el challenge técnico de Techforb. El objetivo es desarrollar un sistema de monitoreo de plantas para proporcionar información útil de las mismas.

## Acceso al servicio deployado
El backend fue deployado en Render.
- URL : `https://challenge-techforb-w7ba.onrender.com`
- Swagger : `https://challenge-techforb-w7ba.onrender.com/swagger-ui/index.html`

## Ejecuacion en local con Docker

Para ejecutar con docker clone el repositorio y dentro de la carpeteda del proyecto ejecutar lo siguiente:
```bash
docker compose up
```
La aplicaion se ejecuta en el puerto: `8091`

## Uso en local

Para utilizar el sistema de monitoreo, sigue estos pasos:

1. Ejecuta la aplicación con el comando mencionado anteriormente.
2. Accede a la interfaz de Swagger en `http://localhost:8091/swagger-ui/index.html`.

### Ejemplos de Uso

- Todas las endpoints estas protegidas excepto las sub-rutas de: `/api/v1/auth/**`

### Detalles 
- Para obtener informacion de los paises y sus banderas se realizo el consumo de la api [RestCountries](https://restcountries.com/)
- Se implento una arquitectura referente a la arquitectura limpia dividida en 3 capas (WebAPI, Core,Infrastructure).
- Se implemento la autorizacion con access token y refresh token.

### Tegnologias usadas
- Spring boot
- Maven
- Hibernet
- JUnitest
- Postgres
- Jwt
- Docker
- VSCoke
- DBeaver

### Servicios para el deploy
- [Neon](https://neon.tech/)
- [Render](https://render.com/)

## Autor

- Helí Antonio Villasante Hilares - [perfil](https://www.linkedin.com/in/hel%C3%AD-antonio-villasante-hilares-96465b263/)
- Agradecimientos especiales a Techforb por el desafío técnico y la oportunidad de desarrollar este proyecto.

### Estructura del Proyecto

    ├── db
    |── techforb-webapi
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── techforb
        │   │           └── techforb_webapi
        │   │               ├── core
        │   │               │   ├── dtos
        │   │               │   │   ├── Request
        │   │               │   │   └── Response
        │   │               │   ├── exceptions
        │   │               │   ├── mappers
        │   │               │   ├── models
        │   │               │   ├── reposittories
        │   │               │   └── services
        │   │               │       └── Implement
        │   │               ├── infrastructure
        │   │               │   ├── configuration
        │   │               │   ├── externalServices
        │   │               │   │   ├── dtos
        │   │               │   │   │   └── restCountries
        │   │               │   │   └── services
        │   │               │   └── services
        │   │               └── webapi
        │   │                   ├── controllers
        │   │                   └── Filters
        │   └── resources
        │       ├── static
        │       └── templates
        └── test
            └── java
                └── com
                    └── techforb
                        └── techforb_webapi
                            └── core
                                └── services
                                    └── Implement


