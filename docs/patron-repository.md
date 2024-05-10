### Patrón repository

Este patrón es una de las bases que conforman la arquitectura de este proyecto (y de cualquiera que siga la arquitectura de puertos y adaptadores): el repositorio es el "puerto" de los "adaptadores".

Basicamente, es un contrato (interfaz) que especifíca una serie de métodos que serán utilizados contra
una fuente de datos sin ocultar ningún tipo de detalle de implementación específico, ya que, es completamente agnóstico a la base de datos.

He decidido optar por este patrón por estar más centrado en las propias entidades de dominio y no tanto de las tablas de una base de datos.

👇 ℹ️
[Repository vs DAO: Patrones de diseño para acceder a bases de datos](https://www.youtube.com/watch?v=QqsH0OgqafA)

[Data Access Objects Vs Repositories](https://medium.com/@jotauribe/data-access-objects-vs-repositories-b1497565a873#:~:text=Una%20de%20las%20principales%20diferencias,m%C3%A1s%20arriba%20que%20el%20primero.)
