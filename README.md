# API REST DE LIBRERIA
<hr>
Esta api proporciona los diferentes libros que existen en una bbdd,proporcionando diferentes roles en la aplicación de acuerdo 
a lo que quieran hacer.<br>
Se realizó con el propósito de como se puede hacer con el framework spring y utilizando el lenguaje java. <br>
Se ha documentado con Swagger para que pueda servir como una guía para los desarrolladores si quieren esta integrar esta api en sus propias aplicaciones.<br>
La api se implementa utilizando el patrón MVC de spring y se puede acceder a ella a través de endpoints RESTfull como:<br>
http://localhost:8080/api/libro/lista -> para ver todos los libros <br>
http://localhost:8080/apI/libro/buscar/{id}-> si quiere buscar un libro en particular <br>
Si quiere ver más endpoints ir a **http://localhost:8080/swagger** ya que ahí están los otros endpoints.<br>
Los usuarios que se registren en ella tendrán como rol "USER" . Para que pueda ser "ADMIN" los desarrolladores lo tendrán que configurar en la base de datos.           Estas son algunas acciones que podrá hacer cada rol en la aplicación (para ver más ir a la endpoint de swagger):                                                        -USUARIO<br>
1-ver todos los libros <br>
2-buscar por libro según el id <br>
3-buscar por autor y editorial<br>
-ADMIN <br>
1-agregar libro<br>
2-buscar por libro según el id <br>
3-buscar por autor y editorial<br>
4-actualizar libro<br>
5-eliminar libro<br>
Este proyecto en lo que respecta a seguridad se maneja con token Jwt que durará 3 horas. Asi que si quieres acceder a ciertos recursos debes tener esto en el request
ya que sino no podrás acceder. Si intentas manipular esto tampoco te darán permiso.Para poder obtenerlo solamente tenés que iniciar sesión en la aplicación.
A continuación se muestran algunas imágenes de la aplicación: <br>
![token en login](https://user-images.githubusercontent.com/92380676/236342058-decc46f3-783d-4e56-8c6d-e3c58ba2b810.JPG)
![imagen post](https://user-images.githubusercontent.com/92380676/236341576-9a254ab1-8878-49ea-bb4a-b8d03432d6fb.jpg)
![sino llenas los campos bien te advertirá](https://user-images.githubusercontent.com/92380676/236341611-f8b615ce-3fa0-44a7-8817-a60b2fe2e8c4.jpg)
![ir url sin token](https://user-images.githubusercontent.com/92380676/236342200-0770b2cf-1f11-4e86-8ed8-e08fd6d811a8.JPG)
![swagger](https://user-images.githubusercontent.com/92380676/236342241-2aa9b7dc-c7e8-4e87-9226-93ebc3178db1.JPG)
<hr>
## Tecnologías utilizadas:

* Visual Studio Code
* Jdk Java 8
* Spring Framework :<br>
1- Spring Boot<br>
2- Spring Data Jpa<br>
3- Spring Security
* Postman
* Swagger




