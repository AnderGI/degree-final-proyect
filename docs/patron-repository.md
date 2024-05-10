### Abstracciones de base de datos

![alt text](../documentacion/app/car/domain/CarRepository.png)

Esta interfaz tiene como fin respetar el principio de inversión de dependencias (DIP) de solid, el cual nos va a ser de gran utilidad, para en los casos de uso de nuestra aplicación (capa application), codificar contra esta abstracción y no contra implementaciones específicas.
