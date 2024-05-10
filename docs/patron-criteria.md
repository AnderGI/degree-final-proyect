### Patrón Criteria

Este patrón sera utilizado para especificar mediante un solo método dentro del repositorio, los posibles criterios de búsqueda, filtradoc y ordenación.

Es un patrón, que utiliza una clase propia de dominio, en mi caso y normalmente Criteria, que tiene dos campos. Una lista de filtros y un campo de ordenación.

Cada filtro tendrá, un campo (cualquiera presente en la entidad de coche), un operador (igual, mayor que, menor que) y el valor que el cliente pase. En el caso de la aplicación de cliente, el valor pasado por el formulario.

La clase Order, es la que tendrá el tipo de ordenación (ascendente, descendente o ninguno) y el campo sobre el que se ordenara (uno de los posibles campos de la entidad de coche).

Aunque sea algo excesivo para este proyecto, he decicido implementarlo por las ventajas que ofrece en términos de escalabilidad (aunque añade cierta complejidad a la hora de instanciar la clase Criteria y de trabajar con ella).

👇 ℹ️
[Patrón de diseño Criteria: Explicado!](https://www.youtube.com/watch?v=RKWb3eI4wO4)
