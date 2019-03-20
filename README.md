# Agenda-Contactos
![Screenshot](https://i.imgur.com/8d8F78Q.png)

## Para cada contacto se debe incluir la siguiente información:

* Identificador
* Apodo, Nombre o Empresa
* En caso de Nombre o Apodo indicar Sexo
* En caso de Nombre incluir Apellidos
* [Opcional: Dirección completa: Calle, Número, Piso, Puerta, Código Postal, Ciudad, Provincia]
* Teléfonos identificando si son móviles o fijos
* Correos electrónicos
* Aficiones
* Notas
* [Opcional: Las provincias serán elegidas de las existentes en España.]
* Las aficiones serán elegidas de las existentes en una lista de aficiones. Si se quieren incluir nuevas habrá que añadirlas a esa lista.

## ACCIONES DE LA AGENDA
* Añadir un nuevo contacto
* Modificar un contacto existente
* Eliminar un contacto existente
* Buscar uno o varios contactos por diversos campos
* Añadir una nueva afición
* Modificar una afición existente avisando si se ha utilizado previamente
* Eliminar una afición existente comprobando que no se haya utilizado previamente

## DISEÑO DE LA BASE DE DATOS
* El sistema gestor de base de datos a utilizar será SQLite.
* El nombre del fichero de la base de datos será Agenda.sqlite.
* El tipo y tamaño de cada campo será decidido por el/la desarrollador/a.

## Se sugieren, al menos, las siguiente tablas:

* contacto (1 a N) telefono
* contacto (1 a N) correo electronico
* contacto (N a M) aficion
