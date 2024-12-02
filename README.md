# productos-api

#La aplicación de desafío se ha desplegado en la nube en la siguiente ruta:
https://productoapp-f3cmatc6gfhzawg3.canadacentral-01.azurewebsites.net/

#Se ha creado una Interfaz gráfica sencilla para facilitar el consumo de la api, la cual se encuentra en la siguiente ruta:
https://productoweb-d2cegagcdfa5gmhf.canadacentral-01.azurewebsites.net/

#La API requiere autenticación básica HTTP. Las credenciales de acceso son las siguientes:

Usuario: admin
Contraseña: adminpassword

#Endpoints

#URL: /api/productos
#METHOD: POST
#Descripción: Crea un nuevo producto en la base de datos.
#Cuerpo de la solicitud (ejemplo):

{
    "nombre": "Producto 1",
    "descripcion": "Descripción del producto",
    "precio": 19.99,
    "categoria": "Electrónica"
}

#URL: /api/productos
#Método: GET
#Descripción: Obtiene una lista de todos los productos en la base de datos.
#Respuesta (ejemplo):

[
    {
    "id": 1,
    "nombre": "Producto 1",
    "descripcion": "Descripción del producto",
    "precio": 19.99,
    "categoria": "Categoría A"
    },
    {
    "id": 2,
    "nombre": "Producto 2",
    "descripcion": "Descripción del producto 2",
    "precio": 29.99,
    "categoria": "Categoría B"
    }
]

#URL: /api/productos/{id}
#Método: GET
#Descripción: Obtiene los detalles de un producto por su id.
#Respuesta (ejemplo):

{
    "id": 1,
    "nombre": "Producto 1",
    "descripcion": "Descripción del producto",
    "precio": 19.99,
    "categoria": "Categoría A"
}

#URL: /api/productos/{id}
#Método: PUT
#Descripción: Actualiza los detalles de un producto existente.
#Cuerpo de la solicitud (ejemplo):

{
    "nombre": "Producto 1 actualizado",
    "descripcion": "Descripción actualizada",
    "precio": 24.99,
    "categoria": "Categoría A"
}


#URL: /api/productos/{id}
#Método: DELETE
#Descripción: Elimina un producto por su id.
#Respuesta: 204 No Content (sin contenido)

#URL: /api/productos/buscar?nombre={nombre}
#Método: GET
#Descripción: Busca productos por su nombre (por coincidencia parcial).
#Parámetro de consulta:
    #nombre: Nombre o parte del nombre del producto.
    #Respuesta (ejemplo):

[
    {
    "id": 1,
    "nombre": "Producto 1",
    "descripcion": "Descripción del producto",
    "precio": 19.99,
    "categoria": "Categoría A"
    }
]

#URL: /api/estadisticas
#Método: GET
#Descripción: Obtiene las estadísticas almacenadas en la base de datos.
#Respuesta (ejemplo):

[
    {
    id: 1,
    categoria: "prueba",
    cantidad: 4,
    fechaActualizacion: "2024-12-02T17:21:52.768894"
    },
    {
    id: 2,
    categoria: "Electrónica",
    cantidad: 2,
    fechaActualizacion: "2024-12-02T17:42:53.499592"
    }
]


#Ejecutar proyecto localmente

#Se requiere Docker
#Java 21

#clonar repositorio:
https://github.com/Carloslatorrev/productos-api

#Modificar el archivo application.properties para asignarle un valor a
#spring.profiles.active , en este caso debe tomar el valor de:
            local
#Actualmente tiene el valor de spring.profiles.active=@profile.active@ ya que toma el valor de una variable de entorno

#Ejecutar docker-compose.xml en un terminal dentro del proyecto
comando:
docker-compose up --build

#Esto debería permitir la ejecución del docker-compose, 
#crear la base de datos local postgres y la dockerización de la aplicación

#Los Endpoint funcionaran de la misma forma pero en la ruta:
localhost:8080
