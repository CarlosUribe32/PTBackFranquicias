# Prueba técnica back de franquicias - Accenture
Para esta API de prueba se usó Spring Boot Java con Firebase Database como sistema de persistencia de los datos.

Para poder ejecutar el proyecto de manera local se necesita:
* Tener instalado el IDE Visual Studio Code, y una vez instalado descargar la extensión "Spring Boot Extension Pack" encontrado en la opción Extensions del IDE
* Clonar el repositorio de manera local
* Abrir el proyecto con el IDE Visual Studio Code
* En la ruta del proyecto api/src/main/resources/ incluir el archivo serviceAccountKey.json
* En la opción "Spring Boot Dashboard" (la cual aparecerá en el IDE una vez que se tenga instalada la extensión antes dicha), en la sección APPS aparecerá el proyecto "api". Al pasar el cursor por encima aparecerá
la opción para ejecutar el proyecto o realizar Debug

Una vez funcionando el proyecto se montará localmente en el puerto 8080. Los siguientes son los edpoints con sus respectivos body para las peticiones (todas son POST):
* localhost:8080/InsertarFranquicia: {"nombreFranquicia": "valorCampo"} para crear una franquicia
* localhost:8080/InsertarSucursal: {"nombreFranquicia": "valorCampo", "nombreSucursal": "valorCampo"} para crear una sucursal
* localhost:8080/InsertarProducto: {"nombreSucursal": "valorCampo", "nombreProducto": "valorCampo", "stockProducto": valorCampo} para crear un producto
* localhost:8080/EliminarProducto: {"nombreSucursal": "valorCampo", "nombreProducto": "valorCampo"} para eliminar un producto
* localhost:8080/ModificarStockProducto: {"nombreSucursal": "valorCampo", "nombreProducto": "valorCampo", "stockProducto": valorCampo} para modificar el stock de un producto
* localhost:8080/TopProductosSucursal: {"nombreFranquicia": "valorCampo"} para obtener el producto con mayor stock por sucursal con la franquicia especificada