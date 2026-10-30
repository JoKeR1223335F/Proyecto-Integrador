# Plan de desarrollo de entidades — `agencia_viajes_ica`

Para la base de datos `agencia_viajes_ica` en MySQL 8, se recomienda construir las entidades Java siguiendo el orden de dependencias de las claves foráneas. Se empieza por los catálogos independientes y se continúa con las entidades que dependen de otras tablas.

Este orden ayuda a evitar referencias a clases que todavía no existen y facilita la implementación y las pruebas de las capas de acceso a datos.

## 1. Orden recomendado de construcción

### Fase 1 — Catálogos independientes

Estas tablas no tienen claves foráneas hacia otras tablas.

| N.º | Entidad Java | Tabla MySQL | Estado | Responsable |
|---:|---|---|---|---|
| 1 | `Rol` | `rol` | **Completado** | Cristofer |
| 2 | `RolProceso` | `rol_proceso` | **Completado** | Cristofer |
| 3 | `TipoTurismo` | `tipo_turismo` | **Completado** | Cristofer |
| 4 | `CampoFormulario` | `campo_formulario` | **Completado** | Cristofer |
| 5 | `EstadoRegistro` | `estado_registro` | Pendiente | Por asignar |
| 6 | `EstadoModeracion` | `estado_moderacion` | Pendiente | Por asignar |
| 7 | `MotivoRechazo` | `motivo_rechazo` | Pendiente | Por asignar |
| 8 | `CategoriaDestino` | `categoria_destino` | Pendiente | Por asignar |

### Fase 2 — Validaciones y seguridad

| N.º | Entidad Java | Dependencia | Estado | Responsable |
|---:|---|---|---|---|
| 9 | `ReglaValidacion` | `CampoFormulario` | En proceso | Cristofer |
| 10 | `Administrador` | `Rol` | **Completado** | Cristofer |
| 11 | `Usuario` | `Rol` | **Completado** | Cristofer |

### Fase 3 — Visitantes y preferencias

| N.º | Entidad Java | Dependencia | Estado | Responsable |
|---:|---|---|---|---|
| 12 | `Visitante` | `Usuario`, `TipoTurismo` | Pendiente | Por asignar |
| 13 | `PreferenciaVisitante` | `Visitante`, `TipoTurismo` | Pendiente | Por asignar |

### Fase 4 — Gestión de destinos turísticos

| N.º | Entidad Java | Dependencia | Estado | Responsable |
|---:|---|---|---|---|
| 14 | `Destino` | `CategoriaDestino`, `Administrador` | Pendiente | Por asignar |
| 15 | `DestinoImagen` | `Destino` | Pendiente | Por asignar |
| 16 | `SolicitudOperacion` | `Destino`, `Administrador` | Pendiente | Por asignar |

### Fase 5 — Publicaciones, comentarios y moderación

| N.º | Entidad Java | Dependencia | Estado | Responsable |
|---:|---|---|---|---|
| 17 | `Publicacion` | `Destino` | Pendiente | Por asignar |
| 18 | `Comentario` | `Usuario`, `Publicacion`, `EstadoModeracion` | Pendiente | Por asignar |
| 19 | `ReporteComentario` | `Comentario`, `Usuario`, `MotivoRechazo` | Pendiente | Por asignar |
| 20 | `Moderacion` | `Comentario`, `Administrador`, `MotivoRechazo` | Pendiente | Por asignar |
| 21 | `Notificacion` | `Moderacion`, `Usuario` | Pendiente | Por asignar |

### Fase 6 — Registro y trazabilidad del proceso

| N.º | Entidad Java | Dependencia | Estado | Responsable |
|---:|---|---|---|---|
| 22 | `SolicitudRegistro` | `Visitante`, `EstadoRegistro` | Pendiente | Por asignar |
| 23 | `ErrorValidacion` | `SolicitudRegistro`, `CampoFormulario` | Pendiente | Por asignar |
| 24 | `ConfirmacionRegistro` | `SolicitudRegistro` | Pendiente | Por asignar |
| 25 | `IncidenteTecnico` | `SolicitudRegistro` | Pendiente | Por asignar |
| 26 | `BitacoraProceso` | `SolicitudRegistro`, `RolProceso`, `EstadoRegistro` | Pendiente | Por asignar |

> **Nota:** Las dependencias de esta tabla siguen el plan de trabajo descrito. Antes de implementar las relaciones JPA, verifica que coincidan con las claves foráneas definidas en el archivo SQL.

## 2. Estados de seguimiento

Utiliza los siguientes estados para mantener un seguimiento uniforme del trabajo:

- **Pendiente:** la tarea todavía no ha comenzado.
- **En progreso:** la entidad se encuentra en desarrollo.
- **En revisión:** la implementación está pendiente de validación o pruebas.
- **Completado:** la entidad está implementada y probada.

## 3. Orden para construir las capas Java

Para cada entidad o módulo, se recomienda seguir esta secuencia, adaptándola a la arquitectura elegida para el proyecto:

1. **Model:** crear la entidad Java y mapear sus columnas y relaciones.
2. **DAO:** definir las operaciones de acceso a datos.
3. **Implementación DAO:** implementar las consultas SQL si se utiliza JDBC.
4. **Repository y Service:** organizar el acceso a datos y las reglas de negocio, según corresponda.
5. **Controller y pruebas:** exponer los endpoints y comprobar las operaciones CRUD.

No es necesario terminar las 26 entidades antes de comenzar las demás capas. El equipo puede desarrollar, integrar y probar cada módulo de forma incremental.

## 4. Recomendación para comenzar

Se recomienda iniciar con el módulo de seguridad y usuarios:

1. `Rol`
2. `Administrador`
3. `Usuario`
4. DAO o Repository y su implementación, según la arquitectura del proyecto.
5. Pruebas de conexión, consultas y operaciones CRUD.

Después, se puede continuar con los módulos de visitantes y destinos turísticos.

El orden de construcción no obliga a completar todos los DAO de una fase antes de avanzar. Es posible implementar y probar un módulo completo antes de pasar al siguiente.

## 5. Asignación de responsables

Por ahora, `Rol` figura como completado y asignado a Cristofer. Las demás entidades quedan como **Pendiente** y con responsable **Por asignar**, ya que no se han indicado otros integrantes ni sus asignaciones.

Cuando el equipo distribuya las tareas, actualiza las columnas **Estado** y **Responsable** de las tablas correspondientes.
