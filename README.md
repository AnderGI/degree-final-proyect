# Proyecto de Plataforma de Compra y Venta de Coches Clásicos

## Descripción:

Este proyecto busca desarrollar una plataforma que, mediante el uso de scrapping, visualize y centralize ofertas, anuncios y apuestas de coches clásicos.

## Objetivo

El principal objetivo de este proyecto es aprender y poner en practica lo siguiente:

- Patrón SOLID
- Patrón Value Object
- Arquitectura hexagonal
- Patrón Criteria

> [!IMPORTANT]
> Teniendo en cuenta los objetivos especificados, el backend y los módulos del front-end de la aplicación sera a lo que primordialmente se le dará énfasis, dejando a un lado el aspecto visual de la misma.

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

> [!IMPORTANT]
> Por las casuísticas del proyecto, de momento solo será lógico usar las acciones tanto get como post de cara a poder generar algún tipo de front-end. Las demás acciones, aún estando testadas no tienen sentido en el contexto de esta aplicación si no hay usuarios de por medio.

### GET

- Todos los coches: <code>/cars</code>
- Buscar coche por id (UUID): <code>/cars/{id}</code>
- Buscar coche por id (UUID): <code>/cars/brands</code>
- Filtrar coches mediante criteria: <code>/cars/criteria?filters?jsonDelCampoFiltersCodificado&orderBy=campoDelCochePorElQueSeVaAOrdenar&orderType=criterioDeOrdenacion</code>
  > [!TIP]
  > Ejemplo del uso del patrón criteria

```
GET http://localhost:8090/cars/criteria?filters=X&orderBy=Y&orderType=Z
```

Siendo este un JSON que represente un criteria

```
{
  "filters":[
    {
      "field"="brand",
      "operator"="=",
      "value"="bmw"
    },

    {
      "field"="price",
      "operator"=">",
      "value"="200000"
    },
  ],
  "orderBy":"price",
  "orderType":"eq"
}
```

X tendrá en valor del array de filters codifciado para pasarlo a un parámetro de URL:

```
[{"field"="brand","operator"="=","value"="bmw"},{"field"="price","operator"=">","value"="200000"}]
```

Y el valor de orderBy y Z el valor de orderType.

### POST

- Añadir nuevo coches:

  ```
  POST http://localhost:8090/cars
  Content-type: application/json

  {
    "id": {
        "value": "f4d19d12-b2e8-4245-95db-dcec4c06b398"
      },
    "title": {
        "value": "1966 MERCEDES 230SL PAGODA"
      },
    "description": {
        "value": "Dos propietarios | Hardtop | Bajo kilometraje"
      },
    "brand": {
        "value": "mercedes-benz"
      },
    "carImageURL": {
        "value": "https://soulauto.com/wp-content/uploads/2024/02/00_00_M12-copia-420x280.jpg?x10431"
      },
    "carAnnouncementURL": {
        "value": "https://soulauto.com/1966-mercedes-benz-230sl-pagoda-2/"
      },
    "price": {
        "value": "38750"
      }
  }
  ```

  > [!NOTE]
  > Utilizo esta estructura con objetos con la propiedad de value anidada para poder hacer uso del patron criteria y unir este JSON con el coche del dominio.

### DELETE

- Eliminar coche según id: <code>/cars/{id}</code>

### PUT

- Actualizar coche existente: <code>/cars/{id}</code>
  > [!NOTE]
  > En la request body se le pasarçn los nuevos campos a actualizar siguiendo el mismo patrón que en el post.

## Uso del scrapper de NodeJS

> [!NOTE]
> Este microservicio scrapeara la página de (soulauto)[https://soulauto.com/], renderizará los resultado por pantalla y lo publicara en una cola de rabbit llamada car_scrapping.
> Pasos para ejecutar el microservicio:

```
cd ./app
cd ./scrapperms
node app.cjs
```

## Ejecutar docker-compose

```
cd ./app
docker-compose up
```

### Cosas a realizar

1.- Intentar migrar el repository de un interface con sus funciones a funciones específicas exportadas como types :

```
export interface CarRepository {
  saveCourse : (course:Course) => Promise<void>
}

export type saveCourse = (course:Course) => Promise<void>
```

2. Añadir testing [Jest con angular](https://medium.com/@philip.mutua/setting-up-jest-in-your-angular-16-project-3638ef65f3a3)
   Los test unitarios vamos a hacerlos sin tener en cuenta los componentes (mockeamos una implementacion especifica creando una funcion a mano que sea del typeGetCar, o de cualquiera de los otros),
   Mirar la seria de videos de FunFunFunctions de Javscript Unit Testing para más info
   Aún asi, si se pueden realizar los tests con Jasmine y los mocks aunque sea a mano, también puede ser una alternativa.

3. Añadir currying al repository, basicamente es crear una funcion tipo createCourse que reciba el repository y devueñva a su vez un función anónima que recibe el course : `createCourse(repository)({parametros del course})`

4.- Añadir value objects

### BBDD

Para la base de datos he empleado MongoDb. Mas concentramente el servicio que MongoDb provee en la nube de MongoDb atlas para evitar tener que instalar toda la base de datos en el mismo equipo.

Como la aplicación deberia de funcionar junto con un scrapper hecho en NodeJS, los datos, hasta el momento han sido metidos a mano mediante peticiones POST y usando POSTMAN. La idea es que una vez quede bien implementado en scrapper en conjunto con el resto de la aplicación, sea este el que, al arrancar la app scrapee distintas páginas web y extraiga datos de los coches. Estos se enviarían a una cola de rabbitmq como un evento. El microservicio de coches estaría escuchando ese evento y de ahí construiría el resto de la app, guardando los coches que se le pasan en la cola en la base de datos, en este caso de MongoDB.

Para poder ver el funcionamiento de scrapper, es necesario ejecutar, dentro de la carpeta de ./app/scrapperms el comando de node app.cjs. Esto abre un servidor en el puerto 3001 de localhost y una vez accedido a el se renderizaran como json todos los coche scrapeados. Este formato representa las entidades de dominio de coche del microservicio de coches y además representa el mismo formato en el que serían enviados a la cola de rabbit (como un array de jsons).
