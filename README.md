# Proyecto de Plataforma de Compra y Venta de Coches Clásicos y de Calidad

## Descripción:
Este proyecto busca desarrollar una plataforma web que permita a los usuarios comprar y vender coches clásicos y de alta calidad.

## Tecnologías Utilizadas:
- <strong>Backend</strong>: Se utilizarán tecnologías como Node.js, Typescript, Vanilla JS y Spring Boot para desarrollar microservicios que gestionen la lógica de negocio y la persistencia de datos.
- <strong>Frontend</strong>: Se empleará Angular, Typescript, Vanilla JS para crear interfaces de usuario interactivas y atractivas.
- <strong>Base de Datos</strong>: Se utilizarán diferentes bases de datos para gestionar la persistencia de estos. Hasta el momento:
  - MongoDB para almacenar y gestionar los datos de los coches, se utilizará esta base de datos porque habrá campos cambiantes.
  - El uso de alguna base de datos relacional para gestionar usuarios.
- <strong>Web Scraping</strong>: Se empleará la librería Puppeteer de NodeJS para realizar el scraping de diferentes páginas web y obtener información sobre coches clásicos disponibles en el mercado en diferentes páginas web.
- <strong>Comunicación entre Microservicios</strong>: Se utilizará RabbitMQ para facilitar la comunicación entre los distintos microservicios y garantizar la coherencia de los datos.

## Funcionalidades Posibles ha añadir:
- <strong>Búsqueda y Filtros Avanzados</strong>: Los usuarios podrán buscar coches clásicos por marca, modelo, año, etc.
- <strong>Listado de Coches</strong>: Se mostrará un listado de coches clásicos disponibles para la venta, con detalles como imágenes, descripciones, precios, etc.
- <strong>Registro y Autenticación de Usuarios</strong>: Los usuarios podrán registrarse, iniciar sesión y gestionar sus cuentas.

## Posibles Funcionalidades
- <strong>Subida de Coches por Usuarios</strong>: Los usuarios podrán subir sus propios coches clásicos para venderlos en la plataforma.
- <strong>Recursos Informativos</strong>: Se ofrecerá una sección de recursos informativos donde los usuarios podrán encontrar videos, artículos y otros contenidos relacionados con coches clásicos.
- <strong>Notificaciones y Mensajería</strong>: Los usuarios recibirán notificaciones sobre nuevos coches disponibles, ofertas especiales, etc.

## Utilización posible de diferentes patrones y arquitectura
- Arquitectura Hexagoanl
- Patrón Value Object
- Patrón Criteria

