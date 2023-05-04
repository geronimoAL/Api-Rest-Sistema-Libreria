# API REST DE LIBRERIA
<hr>
Esta api proporciona los diferentes libros que existen en una bbdd,proporcionando diferentes roles en la aplicación de acuerdo 
a lo que quieran hacer.<br>
Se realizó con el propósito de como se puede hacer con el framework spring y utilizando el lenguaje java. <br>
Se ha documentado con Swagger para que pueda servir como una guía para los desarrolladores si quieren esta integrar esta api en sus propias aplicaciones.<br>
La api se implementa utilizando el patrón MVC de spring y se puede acceder a ella a través de endpoints RESTfull como:<br>
-**http://localhost:8080/api/libro/lista -> para ver todos los libros <br>
-**http://localhost:8080/apI/libro/buscar/{id}-> si quiere buscar un libro en particular <br>
Si quiere ver más endpoints ir a **http://localhost:8080/swagger ya que ahí están los otros endpoints.<br>
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
Este proyecto en lo que respecta a seguridad se maneja con token Jwt. Asi que si quieres acceder a ciertos recursos debes tener esto en el request
ya que sino no podrás acceder. Para poder obtenerlo solamente tenés que iniciar sesión en la aplicación.<br>
A continuación se muestran algunas imágenes de la aplicación:<br>

La finalidad de esta aplicación es que pueda:
<br>
Cargar autor, editorial (mostrandote un mensaje con el nombre que ingresaste y el id que va tener):
<br>
[![editorial-guardada.jpg](https://i.postimg.cc/tCrHD7Fh/editorial-guardada.jpg)](https://postimg.cc/ykS581Qd)
[![guardado-autor.jpg](https://i.postimg.cc/66cs5TjD/guardado-autor.jpg)](https://postimg.cc/YjvDd2w3)
<br>
Ya teniendo los id de todo lo anterior podrás cargar el libro
<br>
![enviamos-bien-libro](https://user-images.githubusercontent.com/92380676/236204531-b1bfa6f9-ac94-4fc5-8c7f-41b537feaea0.jpg)
<br>
Como se muestra en el base de datos
<br>
[![libro-guardado-con-exito.jpg](https://i.postimg.cc/nLmM9NC9/libro-guardado-con-exito.jpg)](https://postimg.cc/t1X9LSd9)
<br>
Pero si no llenas los campos te lo advertirá
<br>
[![errores-sino-mandamos-lo-q-piden.jpg](https://i.postimg.cc/MZy7yS0b/errores-sino-mandamos-lo-q-piden.jpg)](https://postimg.cc/xq1N2B4c)
<br>
Para poder hacer todo lo anterior tenes que tener una cuenta. 
Hay 2 tipos usuarios: ADMIN y USER.<br>
El primero podrá hacer todo lo anterior y el otro podrá ver la lista de autores, editoriales y de libros (que puede filtrar por autor y editorial).
<br>
El usuario que inicie sesión se le dará un token
<br>
[![login-token.jpg](https://i.postimg.cc/6QQSqNqt/login-token.jpg)](https://postimg.cc/BjdN7k2V)
<br>
Con la finalidad de que lo que quiera hacer deberá tenerlo en el header-autorization 
<br>
[![token-en-authorization.jpg](https://i.postimg.cc/T1yQvRtH/token-en-authorization.jpg)](https://postimg.cc/GTCvk1wk)
<br>
Si intenta hacer una acción que no está dentro de lo establecido le mostrará acceso denegado:
<br>
[![sino-tiene-acceso-para-guardar.jpg](https://i.postimg.cc/JnHB1GWJ/sino-tiene-acceso-para-guardar.jpg)](https://postimg.cc/62wQc6q5)

<hr>

## Tecnologías utilizadas:

* Visual Studio Code
* Jdk Java 8
* Spring Framework :<br>
1- Spring Boot<br>
2- Spring Data Jpa<br>
3- Spring Security
* Postman




