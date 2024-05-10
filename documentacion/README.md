# Proyecto de Plataforma de Compra y Venta de Coches Clásicos

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

> [!NOTE]
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

- X = array de filters codifciado para pasarlo a un parámetro de URL:

```
[{"field"="brand","operator"="=","value"="bmw"},{"field"="price","operator"=">","value"="200000"}]
```

- Y =  valor de orderBy 
- Z =  valor de orderType.

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
> En la request body se le pasaran los nuevos campos a actualizar siguiendo el mismo patrón que en el post.

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

## BBDD

Para la base de datos he empleado MongoDb. Mas concentramente el servicio que MongoDb provee en la nube de MongoDb atlas para evitar tener que instalar toda la base de datos en el mismo equipo.

Como la aplicación deberia de funcionar junto con un scrapper hecho en NodeJS, los datos, hasta el momento han sido metidos a mano mediante peticiones POST y usando POSTMAN. La idea es que una vez quede bien implementado el scrapper en conjunto con el resto de la aplicación, sea este el que, al arrancar la app scrapee distintas páginas web y extraiga datos de los coches. Estos se enviarían a una cola de rabbitmq como un evento. El microservicio de coches estaría escuchando ese evento y de ahí construiría el resto de la app, guardando los coches que se le pasan en la cola en la base de datos, en este caso de MongoDB.

Para poder ver el funcionamiento de scrapper, es necesario ejecutar, dentro de la carpeta de ./app/scrapperms el comando de node app.cjs. Esto abre un servidor en el puerto 3001 de localhost y una vez accedido a el se renderizaran como json todos los coche scrapeados. Este formato representa las entidades de dominio de coche del microservicio de coches y además representa el mismo formato en el que serían enviados a la cola de rabbit (como un array de jsons).

## Programación funcional en el frontend

Aún habiendo usado Angular en el frontend y siendo este un framework algo opinionado, he decidido implementar este paradigma frente a POO. He divido la aplicación en modules (código de dominio, aplicación e infraestructura) y sections (los componentes). De esta manera, y aún siendo algo complicado desacoplarse completamente del framework o libreria, especialmente en el frontend, se consigue modularizar algo mas la app. Para implementar el paradigma de la programación funcional he utilizado typescript, que proporciona ciertas utilidades en tiempo de desarrollo al ser un lenguaje de tipado fuerte y estático. Algunas caracteristicas de este paradigma implementado en typescript son:
- Uso de [currying](https://javascript.info/currying-partials) un casos de uso de la capa de <code>/modules/application</code>
- Patrón repository mediante el uso de types para definir las cabeceras (y por tanto los contratos de las funciones). Por ejemplo:
```
export type GetCar = ({value}: CarId) => Promise<Car>; 
export type GetAllCars = () => Promise<Car[]>;
export type GetAllCarBrands = () => Promise<CarBrand[]>;
export type GetCarsMatchingCriteria = (criteria:CriteriaJSON) => Promise<Car[]>; 
```
- Para el uso de las implementaciones de los repositorios, ha sido necesario usar, classes de tipo service y poder usar los patrones de inyección de dependencias que Angular provee. Aún así, estos servicios representan clases con métodos únicos que respetan la firma del repositorio que "implementan":
```
export class GetAllCarServiceHttpClientService {

  
  constructor(private client:HttpClient) { }

  getAllCars:GetAllCars = async () => {
    const GET_ALL_CARS_ENDPOINT = "http://localhost:8090/cars";
    return firstValueFrom(this.client.get<Car[]>(`${GET_ALL_CARS_ENDPOINT}`));
  }
}
```
