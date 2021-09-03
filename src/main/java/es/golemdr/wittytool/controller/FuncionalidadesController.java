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
import es.golemdr.wittytool.domain.Funcionalidad;
import es.golemdr.wittytool.domain.form.FuncionalidadForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.FuncionalidadesService;



@Controller
public class FuncionalidadesController {

	private static Logger log = LogManager.getLogger(FuncionalidadesController.class);

	private static final String FUNCIONALIDADES = "funcionalidades";
	private static final String FUNCIONALIDAD = "funcionalidad";

	@Autowired
	private FuncionalidadesService funcionalidadesService;



	@GetMapping(value=UrlConstants.URL_LISTADO_FUNCIONALIDADES)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Funcionalidad> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = funcionalidadesService.getFuncionalidades(paginacion);

		map.put("paginacion", paginacion);
		map.put(FUNCIONALIDADES, resultado);
		map.put(FUNCIONALIDAD,new FuncionalidadForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_FUNCIONALIDADES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_FUNCIONALIDAD)
	public String insertar(FuncionalidadForm formulario, Model model) {

		Funcionalidad entity = new Funcionalidad();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		funcionalidadesService.insertarActualizarFuncionalidad(entity);

		return ForwardConstants.RED_LISTADO_FUNCIONALIDADES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_FUNCIONALIDAD)
	public String editar(String idFuncionalidad, Map<String, Object> map) {

		Funcionalidad entity = funcionalidadesService.getById(Long.valueOf(idFuncionalidad));

		FuncionalidadForm formulario = new FuncionalidadForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(FUNCIONALIDAD,formulario);

		return ForwardConstants.FWD_FUNCIONALIDAD_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_FUNCIONALIDAD)
	public String actualizar(@Valid FuncionalidadForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_FUNCIONALIDAD_FORM;

		}else{

			Funcionalidad entity = new Funcionalidad();

			try {

				BeanUtils.copyProperties(entity, formulario);

				funcionalidadesService.insertarActualizarFuncionalidad(entity);

				destino = ForwardConstants.RED_LISTADO_FUNCIONALIDADES;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_FUNCIONALIDAD)
	public String borrar(String idFuncionalidad, Map<String, Object> map) {

		funcionalidadesService.borrarFuncionalidad(Long.valueOf(idFuncionalidad));

		return ForwardConstants.RED_LISTADO_FUNCIONALIDADES;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_FUNCIONALIDADES)
	public String buscarFuncionalidades(FuncionalidadForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Funcionalidad> resultado = null;

		Funcionalidad entity = new Funcionalidad();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = funcionalidadesService.findFuncionalidadesByExample(entity, paginacion);
			total = funcionalidadesService.countFuncionalidadesByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(FUNCIONALIDADES, resultado);
		map.put(FUNCIONALIDAD,new FuncionalidadForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_FUNCIONALIDADES;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_FUNCIONALIDADES_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Funcionalidad> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Funcionalidad funcionalidad = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Funcionalidad){

			funcionalidad = (Funcionalidad)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = funcionalidadesService.findFuncionalidadesByExample(funcionalidad, paginacion);
		total = funcionalidadesService.countFuncionalidadesByExample(funcionalidad);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(FUNCIONALIDAD, new FuncionalidadForm());
		model.addAttribute(FUNCIONALIDADES, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_FUNCIONALIDADES;
	}



}

