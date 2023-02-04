# API REST DE LIBRERIA
<hr>

La finalidad de esta aplicación es que pueda:
<br>
Cargar autor, editorial (mostrandote un mensaje con el nombre que ingresaste y el id que va tener):
<br>
[![editorial-guardada.jpg](https://i.postimg.cc/tCrHD7Fh/editorial-guardada.jpg)](https://postimg.cc/ykS581Qd)
[![guardado-autor.jpg](https://i.postimg.cc/66cs5TjD/guardado-autor.jpg)](https://postimg.cc/YjvDd2w3)
<br>
Ya teniendo los id de todo lo anterior podrás cargar el libro:
<br>
[![enviamos-bien-libro.jpg](https://i.postimg.cc/rwn596Vh/enviamos-bien-libro.jpg)](https://postimg.cc/PCDLtR8Z)
<br>
Como se muestra en el base de datos:
<br>
[![libro-guardado-con-exito.jpg](https://i.postimg.cc/nLmM9NC9/libro-guardado-con-exito.jpg)](https://postimg.cc/t1X9LSd9)
<br>
Pero si no llenas los campos te lo advertirá:
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




