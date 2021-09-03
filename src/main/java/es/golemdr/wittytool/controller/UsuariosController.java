package es.golemdr.wittytool.controller;



import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.golemdr.wittytool.ext.Constantes;
import es.golemdr.wittytool.controller.constantes.ForwardConstants;
import es.golemdr.wittytool.controller.constantes.UrlConstants;
import es.golemdr.wittytool.domain.Usuario;
import es.golemdr.wittytool.domain.form.UsuarioForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.UsuariosService;



@Controller
public class UsuariosController {

	private static Logger log = LogManager.getLogger(UsuariosController.class);

	private static final String USUARIOS = "usuarios";
	private static final String USUARIO = "usuario";

	@Autowired
	private UsuariosService usuariosService;



	@GetMapping(value=UrlConstants.URL_LISTADO_USUARIOS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Usuario> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = usuariosService.getUsuarios(paginacion);

		map.put("paginacion", paginacion);
		map.put(USUARIOS, resultado);
		map.put(USUARIO,new UsuarioForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_USUARIOS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_USUARIO)
	public String insertar(UsuarioForm formulario, Model model) {

		Usuario entity = new Usuario();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		usuariosService.insertarActualizarUsuario(entity);

		return ForwardConstants.RED_LISTADO_USUARIOS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_USUARIO)
	public String editar(String idUsuario, Map<String, Object> map) {

		Usuario entity = usuariosService.getById(Long.valueOf(idUsuario));

		UsuarioForm formulario = new UsuarioForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(USUARIO,formulario);

		return ForwardConstants.FWD_USUARIO_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_USUARIO)
	public String actualizar(@Valid UsuarioForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_USUARIO_FORM;

		}else{

			Usuario entity = new Usuario();

			try {

				BeanUtils.copyProperties(entity, formulario);

				usuariosService.insertarActualizarUsuario(entity);

				destino = ForwardConstants.RED_LISTADO_USUARIOS;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_USUARIO)
	public String borrar(String idUsuario, Map<String, Object> map) {

		usuariosService.borrarUsuario(Long.valueOf(idUsuario));

		return ForwardConstants.RED_LISTADO_USUARIOS;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_USUARIOS)
	public String buscarUsuarios(UsuarioForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Usuario> resultado = null;

		Usuario entity = new Usuario();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = usuariosService.findUsuariosByExample(entity, paginacion);
			total = usuariosService.countUsuariosByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(USUARIOS, resultado);
		map.put(USUARIO,new UsuarioForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_USUARIOS;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_USUARIOS_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Usuario> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Usuario usuario = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Usuario){

			usuario = (Usuario)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = usuariosService.findUsuariosByExample(usuario, paginacion);
		total = usuariosService.countUsuariosByExample(usuario);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(USUARIO, new UsuarioForm());
		model.addAttribute(USUARIOS, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_USUARIOS;
	}



}

