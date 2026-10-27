# Guía para levantar el proyecto Spring Boot con Docker Compose y PostgreSQL

Esta guía explica cómo iniciar el contenedor de PostgreSQL, comprobar que la base de datos y sus tablas estén disponibles y ejecutar tu proyecto Spring Boot.

## 1. Requisitos previos

Verifica que tengas instalados:

- Docker Desktop o Docker Engine.
- Java JDK, compatible con la versión de tu proyecto Spring Boot.
- Maven o el Maven Wrapper (`mvnw`) incluido en el proyecto.
- Tu proyecto `Proyecto_Viajo` descargado o abierto en tu IDE, como IntelliJ IDEA, Eclipse o VS Code.

Comprueba Docker desde la terminal:

```
docker --version
docker compose version
```

## 2. Levantar el contenedor de PostgreSQL

Tu archivo `docker-compose.yml` se encuentra dentro de la carpeta `docker` del proyecto.

### Paso 1. Entrar a la carpeta `docker`

Abre una terminal en la carpeta raíz de `Proyecto_Viajo` y ejecuta:

```
cd docker
```

Si estás en Windows y utilizas PowerShell, el comando es el mismo.

Comprueba que el archivo esté allí:

```
dir
```

En Linux o macOS:

```
ls
```

Debes encontrar el archivo `docker-compose.yml` o `compose.yaml`.

### Paso 2. Iniciar el contenedor

Ejecuta:

```
docker compose up -d
```

Este comando descarga la imagen de PostgreSQL 17 si es necesario y crea e inicia el contenedor en segundo plano.

Tu configuración utiliza estos datos:

| Parámetro              | Valor                     |
| ---------------------- | ------------------------- |
| Contenedor             | `proyecto_viajo_postgres` |
| Motor de base de datos | PostgreSQL 17             |
| Base de datos          | `proyecto_viajo`          |
| Usuario                | `viajo_user`              |
| Contraseña             | `viajo_password`          |
| Puerto                 | `5432`                    |

### Paso 3. Comprobar que el contenedor está activo

```
docker compose ps
```

También puedes consultar todos los contenedores:

```
docker ps
```

Deberías ver `proyecto_viajo_postgres` con estado `Up`. Si todavía está iniciando, espera unos segundos y vuelve a comprobarlo.

Para revisar los registros de PostgreSQL:

```
docker compose logs -f postgres
```

Cuando aparezca un mensaje similar a `database system is ready to accept connections`, PostgreSQL estará listo. Para salir de los registros, presiona `Ctrl + C`; esto no detiene el contenedor.

## 3. Revisar la base de datos y sus tablas

Puedes hacerlo desde la terminal con `psql` o mediante una herramienta gráfica.

### Opción A. Entrar a PostgreSQL desde Docker

Desde la carpeta `docker`, ejecuta:

```
docker exec -it proyecto_viajo_postgres psql -U viajo_user -d proyecto_viajo
```

Si la conexión es correcta, aparecerá una consola de PostgreSQL.

Para listar las bases de datos:

```
\l
```

Para conectarte a la base de datos del proyecto:

```
\c proyecto_viajo
```

Para listar las tablas del esquema actual:

```
\dt
```

Para listar las tablas de todos los esquemas:

```
\dt *.*
```

Para revisar la estructura de una tabla, por ejemplo `rol`:

```
\d rol
```

Para consultar los registros de esa tabla:

```
SELECT * FROM rol;
```

Para contar cuántas tablas hay en el esquema `public`:

```
SELECT COUNT(*)
FROM information_schema.tables
WHERE table_schema = 'public'
  AND table_type = 'BASE TABLE';
```

Para salir de la consola:

```
\q
```

Importante: que la base de datos exista no significa que las tablas ya estén creadas. Si `\dt` no muestra tablas, debes ejecutar las migraciones o el script SQL correspondiente, o configurar Hibernate para generarlas, según cómo esté preparado tu proyecto.

### Opción B. Usar pgAdmin o DBeaver

También puedes conectarte con una interfaz gráfica utilizando estos datos:

## Conexión a PostgreSQL

Host

`localhost`

Puerto

`5432`

Base de datos

`proyecto_viajo`

Usuario

`viajo_user`

Contraseña

`viajo_password`

Una vez conectado, abre la base de datos `proyecto_viajo` y navega a Schemas → public → Tables.

## 4. Configurar Spring Boot para conectarse a PostgreSQL

Abre el archivo `src/main/resources/application.properties` de tu proyecto.

Si utilizas Spring Data JPA con el controlador JDBC de PostgreSQL, la configuración puede ser:

```

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/proyecto_viajo
    username: viajo_user
    password: viajo_password
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

Si ya tienes un archivo `application.properties` o `application.yml`, revisa primero su configuración y adapta estos valores en lugar de duplicarlos.

Sobre `ddl-auto`: `update` puede crear o actualizar tablas a partir de tus entidades JPA, pero no ejecuta automáticamente cualquier script SQL que tengas. Si tu proyecto usa Flyway, Liquibase o un script SQL propio, debes seguir ese mecanismo. En un entorno de producción, conviene gestionar los cambios del esquema con migraciones.

Verifica también que el `pom.xml` incluya las dependencias necesarias para Spring Data JPA y PostgreSQL, si ese es el enfoque utilizado en tu proyecto.

## 5. Levantar el proyecto Spring Boot

Mantén PostgreSQL ejecutándose y abre una segunda terminal en la carpeta raíz del proyecto, donde se encuentra el `pom.xml`.

### Si usas Maven Wrapper

En Windows:

```
.\mvnw.cmd spring-boot:run
```

En Linux o macOS:

```
./mvnw spring-boot:run
```

### Si tienes Maven instalado

```
mvn spring-boot:run
```

Espera a que Spring Boot termine de iniciar. En los registros deberías ver un mensaje similar a:

```
Started ProyectoViajoApplication
```

El nombre exacto depende de la clase principal de tu aplicación.

Si ejecutas desde IntelliJ IDEA, también puedes abrir la clase que contiene `@SpringBootApplication` y pulsar Run.

## 6. Probar los endpoints de la API

Si tu aplicación utiliza el puerto predeterminado `8080` y tienes el controlador de roles que compartiste anteriormente, puedes probarlo desde Postman.

GET

Listar roles

`http://localhost:8080/api/roles`

GET

Buscar un rol

`http://localhost:8080/api/roles/1`

POST

Crear un rol

`http://localhost:8080/api/roles`

Body → raw → JSON:

```
{
  "nombreRol": "GUIA_TURISTICO",
  "descripcion": "Rol para guías turísticos"
}
```

Estos ejemplos suponen que el endpoint existe, que el modelo tiene esos campos y que el identificador enviado coincide con el tipo de dato de la entidad.

Si tienes habilitado Spring Boot Actuator, también puedes comprobar el estado de la aplicación mediante su endpoint de salud, si está expuesto.

## 7. Detener el proyecto y el contenedor

Para detener Spring Boot, presiona `Ctrl + C` en la terminal donde lo ejecutaste.

Para detener PostgreSQL, desde la carpeta `docker` ejecuta:

```
docker compose down
```

Esto elimina el contenedor, pero conserva los datos en el volumen `postgres_data`.

Para volver a levantarlo otro día, entra nuevamente en `docker` y ejecuta:

```
docker compose up -d
```

No ejecutes `docker compose down -v` si deseas conservar tus datos, porque la opción `-v` elimina los volúmenes asociados.

## Resumen del procedimiento

1. Entrar a la carpeta del proyecto y luego a `docker`.
2. Ejecutar `docker compose up -d`.
3. Comprobar el contenedor con `docker compose ps`.
4. Revisar la base de datos y las tablas con `docker exec ... psql` y `\dt`.
5. Volver a la raíz de `Proyecto_Viajo`.
6. Verificar `application.properties` y las dependencias.
7. Ejecutar `mvn spring-boot:run` o el Maven Wrapper.
8. Probar los endpoints en Postman.

Nota importante: esta guía está adaptada a tu configuración actual de PostgreSQL. Si tu proyecto todavía conserva la configuración anterior de MySQL, tendrás que cambiar el controlador, las dependencias y la URL de conexión para utilizar PostgreSQL correctamente.
