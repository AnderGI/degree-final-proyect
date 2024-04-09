# Proyecto de Plataforma de Compra y Venta de Coches Clásicos

## Descripción:
Este proyecto busca desarrollar una plataforma que, mediante el uso de scrapping, visualize y centralize ofertas, anuncios y apuestas de coches clásicos. 

## Objetivo
El principal objetivo de este proyecto es aprender y poner en practica lo siguiente:
- Patrón SOLID
- Patrón Value Object
- Arquitectura hexagonal
- Patrón Criteria

>[!IMPORTANT]
>Teniendo en cuenta los objetivos especificados, el backend y los módulos del front-end de la aplicación sera a lo que primordialmente se le dará énfasis, dejando a un lado el aspecto visual de la misma.

## Tecnologías utilizadas:
- <strong>Backend</strong>: SpringBoot y NodeJS.
- <strong>Frontend</strong>: Angular con Typescript.
- <strong>Lenguajes</strong>: Java y Javascript.
- <strong>Base de Datos</strong>: MongoDB
- <strong>DevOps</strong>: Docker usando docker-comnpose.

## Web Scrapping
Se empleará la librería Puppeteer de NodeJS para realizar el scraping de diferentes páginas web y obtener información sobre coches clásicos disponibles en el mercado en diferentes páginas web.

## Comunicación entre Microservicios
Se utilizará RabbitMQ para facilitar la comunicación entre los distintos microservicios.

## Funcionalidades existentes:
- Renderizado de todos los coches, con su información detallada.
- Uso del patrón criteria para el filtrado por varios campos (actualmente por marca y precio mínimo de los coches).

## Funcionalidades ha añadir:
- Siguiendo con el patrón criteria, se buscará generar un filtrado por ordern ascendente o descendente según precio.
- Implementar un sistema que permita la comunicación entre el scrapper de NodeJS y el gateway creado en SpringBoot.
- Al ser una página con interacción de usuarios, se implementaría un sistema de autenticación mediante JWT. Posiblemente haciendo esto en el gateway.
- Los usuario podrán hacer uso de la funcionalidad de crear, actualizar y eliminar posibles anuncios.

## Recursos para RabbitMQ
-How to Use RabbitMQ with NodeJS to Send and Receive Messages [FreeCodeCamp](https://www.freecodecamp.org/news/how-to-use-rabbitmq-with-nodejs/)
- How to Consume/Publish RabbitMQ queue in NodeJS [Medium](https://medium.com/@rafael.guzman/how-to-consume-publish-rabbitmq-message-in-nodejs-cb68b5a6484c)

## Endpoints
### Acceso al Gateway : 
```
http://localhost:8090
```
### Acceso al microservicio de coches
```
/cars
```
### Endpoints por acciones HTTP 
<strong>GET:</strong>
- Todos los coches: <code>/cars</code>
- Buscar coche por id (UUID): <code>/cars/{id}</code>
- Buscar coche por id (UUID): <code>/cars/brands</code>
- Filtrar coches mediante criteria: <code>/cars/criteria?filters?jsonDelCampoFiltersCodificado&orderBy=campoDelCochePorElQueSeVaAOrdenar&orderType=criterioDeOrdenacion
