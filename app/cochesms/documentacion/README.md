# Documentación del microservicio de coches

Este microservicio se ha desarrollado utilizando arquitectura hexagonal (puertos y adapatadores), con el fin de : 1. Minimizar al máximo el acoplamiento entre clases (codificando contra abstracciones y no implementaciones específicas) 2. Mejorar la mantenibilidad y extensibilidad del código 3. Respetar los principios S.O.L.I.D

## Capas de la arquitectura hexagonal

## Capa de dominio

> [!IMPORTANT]
> Esta capa contendrá toda la lógica de nuestro negocio (entidades de dominio, value objects, abstracciones / interfaces de repositorios, servicios de dominio...) Es decir, todo aquello que dependa exclusivamente de criterios internos propios.

## Capa de aplicación

> [!IMPORTANT]
> Esta capa será el punto de entrada de nuestra aplicación (los controladores por lo tanto residirán aquí). Además, aquí es donde se añadirán todos los casos de uso (application services) que nuestro microservicio utilizará.

> [!NOTE]
> Los servicios de dominio y aplicación se diferencian en que estos últimos serán procesos atómicos que representarán aquellas casuísticas que un cliente pueda llevar a cabo. Los de dominio, será creados con el fin de minimizar al máximo partes de código reutilizadas por varios servicios de aplicación.

## Capa de infraestructure

> [!IMPORTANT]
> Esta capa contendrá todas aquellas dependencias de terceros que nosotros utilizaremos y que realizen acciones de entrada y salida, como puede ser aqullas realacionadas, con bbdd (implementaciones específicas de las abstracciones de dominio) o con web.
