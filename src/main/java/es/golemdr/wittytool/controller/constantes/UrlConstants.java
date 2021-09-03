package es.golemdr.wittytool.controller.constantes;


public class UrlConstants {

	
	private UrlConstants() {
		throw new IllegalStateException("UrlConstants class");
	}


	public static final String SUFIJO_PAGINACION = 	"{inicio}.do";
	public static final String URL_LOGIN = "/login.do";
	public static final String URL_DENIED = "/denied.do";
	public static final String URL_LOGIN_CONTROLLER = "/loginController.do";
	public static final String URL_LOGIN_FAILURE = "/login-failure.do";	
	public static final String URL_HOME = "/home.do";
	public static final String URL_ACCESO_OK = "/accesook.do";
	public static final String URL_ENTRADA_APLICACION = "/inicio.do";


	public static final String URL_VER_LOGIN = "/login.do";
	public static final String URL_CAMBIAR_PASSWORD="/cambiarPassword.do";

	
	public static final String SEGURIDAD_ANOTACIONES =  "/sec";	
	public static final String SEGURIDAD_BITACORAS =  "/sec";	
	public static final String SEGURIDAD_CONSTANTESAUXILIARES =  "/sec";	
	public static final String SEGURIDAD_EVENTOS =  "/sec";	
	public static final String SEGURIDAD_FUNCIONALIDADES =  "/sec";	
	public static final String SEGURIDAD_INCIDENCIAS =  "/sec";	
	public static final String SEGURIDAD_PROYECTOS =  "/sec";	
	public static final String SEGURIDAD_ROLES =  "/sec";	
	public static final String SEGURIDAD_SPRINTS =  "/sec";	
	public static final String SEGURIDAD_USUARIOS =  "/sec";		

	

	// Anotaciones
	public static final String URL_LISTADO_ANOTACIONES = SEGURIDAD_ANOTACIONES + "/listadoAnotaciones" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_ANOTACIONES_FILTRADO = SEGURIDAD_ANOTACIONES + "/listadoAnotacionesFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_ANOTACION = SEGURIDAD_ANOTACIONES + "/altaAnotacion.do";
	public static final String URL_INSERTAR_ANOTACION = SEGURIDAD_ANOTACIONES + "/insertarAnotacion.do";
	public static final String URL_EDITAR_ANOTACION = SEGURIDAD_ANOTACIONES + "/editarAnotacion.do";
	public static final String URL_ACTUALIZAR_ANOTACION = SEGURIDAD_ANOTACIONES + "/actualizarAnotacion.do";
	public static final String URL_BORRAR_ANOTACION = SEGURIDAD_ANOTACIONES + "/borrarAnotacion.do";
    public static final String URL_BUSCAR_ANOTACIONES = SEGURIDAD_ANOTACIONES + "/buscarAnotaciones.do";


	

	// Bitacoras
	public static final String URL_LISTADO_BITACORAS = SEGURIDAD_BITACORAS + "/listadoBitacoras" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_BITACORAS_FILTRADO = SEGURIDAD_BITACORAS + "/listadoBitacorasFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_BITACORA = SEGURIDAD_BITACORAS + "/altaBitacora.do";
	public static final String URL_INSERTAR_BITACORA = SEGURIDAD_BITACORAS + "/insertarBitacora.do";
	public static final String URL_EDITAR_BITACORA = SEGURIDAD_BITACORAS + "/editarBitacora.do";
	public static final String URL_ACTUALIZAR_BITACORA = SEGURIDAD_BITACORAS + "/actualizarBitacora.do";
	public static final String URL_BORRAR_BITACORA = SEGURIDAD_BITACORAS + "/borrarBitacora.do";
    public static final String URL_BUSCAR_BITACORAS = SEGURIDAD_BITACORAS + "/buscarBitacoras.do";


	

	// ConstantesAuxiliares
	public static final String URL_LISTADO_CONSTANTESAUXILIARES = SEGURIDAD_CONSTANTESAUXILIARES + "/listadoConstantesAuxiliares" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_CONSTANTESAUXILIARES_FILTRADO = SEGURIDAD_CONSTANTESAUXILIARES + "/listadoConstantesAuxiliaresFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_CONSTANTEAUXILIAR = SEGURIDAD_CONSTANTESAUXILIARES + "/altaConstanteAuxiliar.do";
	public static final String URL_INSERTAR_CONSTANTEAUXILIAR = SEGURIDAD_CONSTANTESAUXILIARES + "/insertarConstanteAuxiliar.do";
	public static final String URL_EDITAR_CONSTANTEAUXILIAR = SEGURIDAD_CONSTANTESAUXILIARES + "/editarConstanteAuxiliar.do";
	public static final String URL_ACTUALIZAR_CONSTANTEAUXILIAR = SEGURIDAD_CONSTANTESAUXILIARES + "/actualizarConstanteAuxiliar.do";
	public static final String URL_BORRAR_CONSTANTEAUXILIAR = SEGURIDAD_CONSTANTESAUXILIARES + "/borrarConstanteAuxiliar.do";
    public static final String URL_BUSCAR_CONSTANTESAUXILIARES = SEGURIDAD_CONSTANTESAUXILIARES + "/buscarConstantesAuxiliares.do";


	

	// Eventos
	public static final String URL_LISTADO_EVENTOS = SEGURIDAD_EVENTOS + "/listadoEventos" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_EVENTOS_FILTRADO = SEGURIDAD_EVENTOS + "/listadoEventosFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_EVENTO = SEGURIDAD_EVENTOS + "/altaEvento.do";
	public static final String URL_INSERTAR_EVENTO = SEGURIDAD_EVENTOS + "/insertarEvento.do";
	public static final String URL_EDITAR_EVENTO = SEGURIDAD_EVENTOS + "/editarEvento.do";
	public static final String URL_ACTUALIZAR_EVENTO = SEGURIDAD_EVENTOS + "/actualizarEvento.do";
	public static final String URL_BORRAR_EVENTO = SEGURIDAD_EVENTOS + "/borrarEvento.do";
    public static final String URL_BUSCAR_EVENTOS = SEGURIDAD_EVENTOS + "/buscarEventos.do";


	

	// Funcionalidades
	public static final String URL_LISTADO_FUNCIONALIDADES = SEGURIDAD_FUNCIONALIDADES + "/listadoFuncionalidades" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_FUNCIONALIDADES_FILTRADO = SEGURIDAD_FUNCIONALIDADES + "/listadoFuncionalidadesFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_FUNCIONALIDAD = SEGURIDAD_FUNCIONALIDADES + "/altaFuncionalidad.do";
	public static final String URL_INSERTAR_FUNCIONALIDAD = SEGURIDAD_FUNCIONALIDADES + "/insertarFuncionalidad.do";
	public static final String URL_EDITAR_FUNCIONALIDAD = SEGURIDAD_FUNCIONALIDADES + "/editarFuncionalidad.do";
	public static final String URL_ACTUALIZAR_FUNCIONALIDAD = SEGURIDAD_FUNCIONALIDADES + "/actualizarFuncionalidad.do";
	public static final String URL_BORRAR_FUNCIONALIDAD = SEGURIDAD_FUNCIONALIDADES + "/borrarFuncionalidad.do";
    public static final String URL_BUSCAR_FUNCIONALIDADES = SEGURIDAD_FUNCIONALIDADES + "/buscarFuncionalidades.do";


	

	// Incidencias
	public static final String URL_LISTADO_INCIDENCIAS = SEGURIDAD_INCIDENCIAS + "/listadoIncidencias" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_INCIDENCIAS_FILTRADO = SEGURIDAD_INCIDENCIAS + "/listadoIncidenciasFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_INCIDENCIA = SEGURIDAD_INCIDENCIAS + "/altaIncidencia.do";
	public static final String URL_INSERTAR_INCIDENCIA = SEGURIDAD_INCIDENCIAS + "/insertarIncidencia.do";
	public static final String URL_EDITAR_INCIDENCIA = SEGURIDAD_INCIDENCIAS + "/editarIncidencia.do";
	public static final String URL_ACTUALIZAR_INCIDENCIA = SEGURIDAD_INCIDENCIAS + "/actualizarIncidencia.do";
	public static final String URL_BORRAR_INCIDENCIA = SEGURIDAD_INCIDENCIAS + "/borrarIncidencia.do";
    public static final String URL_BUSCAR_INCIDENCIAS = SEGURIDAD_INCIDENCIAS + "/buscarIncidencias.do";


	

	// Proyectos
	public static final String URL_LISTADO_PROYECTOS = SEGURIDAD_PROYECTOS + "/listadoProyectos" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_PROYECTOS_FILTRADO = SEGURIDAD_PROYECTOS + "/listadoProyectosFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_PROYECTO = SEGURIDAD_PROYECTOS + "/altaProyecto.do";
	public static final String URL_INSERTAR_PROYECTO = SEGURIDAD_PROYECTOS + "/insertarProyecto.do";
	public static final String URL_EDITAR_PROYECTO = SEGURIDAD_PROYECTOS + "/editarProyecto.do";
	public static final String URL_ACTUALIZAR_PROYECTO = SEGURIDAD_PROYECTOS + "/actualizarProyecto.do";
	public static final String URL_BORRAR_PROYECTO = SEGURIDAD_PROYECTOS + "/borrarProyecto.do";
    public static final String URL_BUSCAR_PROYECTOS = SEGURIDAD_PROYECTOS + "/buscarProyectos.do";


	

	// Roles
	public static final String URL_LISTADO_ROLES = SEGURIDAD_ROLES + "/listadoRoles" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_ROLES_FILTRADO = SEGURIDAD_ROLES + "/listadoRolesFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_ROL = SEGURIDAD_ROLES + "/altaRol.do";
	public static final String URL_INSERTAR_ROL = SEGURIDAD_ROLES + "/insertarRol.do";
	public static final String URL_EDITAR_ROL = SEGURIDAD_ROLES + "/editarRol.do";
	public static final String URL_ACTUALIZAR_ROL = SEGURIDAD_ROLES + "/actualizarRol.do";
	public static final String URL_BORRAR_ROL = SEGURIDAD_ROLES + "/borrarRol.do";
    public static final String URL_BUSCAR_ROLES = SEGURIDAD_ROLES + "/buscarRoles.do";


	

	// Sprints
	public static final String URL_LISTADO_SPRINTS = SEGURIDAD_SPRINTS + "/listadoSprints" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_SPRINTS_FILTRADO = SEGURIDAD_SPRINTS + "/listadoSprintsFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_SPRINT = SEGURIDAD_SPRINTS + "/altaSprint.do";
	public static final String URL_INSERTAR_SPRINT = SEGURIDAD_SPRINTS + "/insertarSprint.do";
	public static final String URL_EDITAR_SPRINT = SEGURIDAD_SPRINTS + "/editarSprint.do";
	public static final String URL_ACTUALIZAR_SPRINT = SEGURIDAD_SPRINTS + "/actualizarSprint.do";
	public static final String URL_BORRAR_SPRINT = SEGURIDAD_SPRINTS + "/borrarSprint.do";
    public static final String URL_BUSCAR_SPRINTS = SEGURIDAD_SPRINTS + "/buscarSprints.do";


	

	// Usuarios
	public static final String URL_LISTADO_USUARIOS = SEGURIDAD_USUARIOS + "/listadoUsuarios" + SUFIJO_PAGINACION;
	public static final String URL_LISTADO_USUARIOS_FILTRADO = SEGURIDAD_USUARIOS + "/listadoUsuariosFiltrado" + SUFIJO_PAGINACION;
	public static final String URL_ALTA_USUARIO = SEGURIDAD_USUARIOS + "/altaUsuario.do";
	public static final String URL_INSERTAR_USUARIO = SEGURIDAD_USUARIOS + "/insertarUsuario.do";
	public static final String URL_EDITAR_USUARIO = SEGURIDAD_USUARIOS + "/editarUsuario.do";
	public static final String URL_ACTUALIZAR_USUARIO = SEGURIDAD_USUARIOS + "/actualizarUsuario.do";
	public static final String URL_BORRAR_USUARIO = SEGURIDAD_USUARIOS + "/borrarUsuario.do";
    public static final String URL_BUSCAR_USUARIOS = SEGURIDAD_USUARIOS + "/buscarUsuarios.do";


	

}
