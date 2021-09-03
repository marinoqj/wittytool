package es.golemdr.wittytool.ext;

public class Constantes {
	
	private Constantes() {
		throw new IllegalStateException("Constantes class");
	}
	
	public static final String 	PASSWORD_DEFAULT = "Password_2021";
	public static final String SI = "1";
	public static final String NO = "0";
	
	public static final String ATRIBUTO_MENSAJE = "mensaje";
	public static final String ATRIBUTO_CODIGO = "codigo";
	public static final String ATRIBUTO_TRAZA = "traza";
	
	public static final String ATRIBUTO_SESSION_USUARIO = "usuarioSesion";
	public static final String ATRIBUTO_SESSION_FILTRO = "filtro";
	public static final String ATRIBUTO_SESSION_HAY_FILTRO = "hayFiltro";
	
	public static final String PREFIJO_MENSAJE_ERROR = "Se produjo el error : {0}";
	public static final String PREFIJO_MENSAJE_TIPO_EXCEPCION = "Se produjo una excepción de tipo : {0}";
	public static final String PREFIJO_MENSAJE_CODIGO_EXCEPCION = "El código de la excepción es : {0}";
	public static final String PREFIJO_MENSAJE_TRAZA_EXCEPCION = "La traza de la excepción es : {0}";
	
	public static final String USUARIO_LOGADO_CORRECTAMENTE = "El usuario {0} se logó correctamente";

}
