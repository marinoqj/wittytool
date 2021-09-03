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
import es.golemdr.wittytool.domain.ConstanteAuxiliar;
import es.golemdr.wittytool.domain.form.ConstanteAuxiliarForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.ConstantesAuxiliaresService;



@Controller
public class ConstantesAuxiliaresController {

	private static Logger log = LogManager.getLogger(ConstantesAuxiliaresController.class);

	private static final String CONSTANTESAUXILIARES = "constantesauxiliares";
	private static final String CONSTANTEAUXILIAR = "constanteauxiliar";

	@Autowired
	private ConstantesAuxiliaresService constantesauxiliaresService;



	@GetMapping(value=UrlConstants.URL_LISTADO_CONSTANTESAUXILIARES)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<ConstanteAuxiliar> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = constantesauxiliaresService.getConstantesAuxiliares(paginacion);

		map.put("paginacion", paginacion);
		map.put(CONSTANTESAUXILIARES, resultado);
		map.put(CONSTANTEAUXILIAR,new ConstanteAuxiliarForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_CONSTANTESAUXILIARES;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_CONSTANTEAUXILIAR)
	public String insertar(ConstanteAuxiliarForm formulario, Model model) {

		ConstanteAuxiliar entity = new ConstanteAuxiliar();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		constantesauxiliaresService.insertarActualizarConstanteAuxiliar(entity);

		return ForwardConstants.RED_LISTADO_CONSTANTESAUXILIARES; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_CONSTANTEAUXILIAR)
	public String editar(String idConstanteAuxiliar, Map<String, Object> map) {

		ConstanteAuxiliar entity = constantesauxiliaresService.getById(Long.valueOf(idConstanteAuxiliar));

		ConstanteAuxiliarForm formulario = new ConstanteAuxiliarForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(CONSTANTEAUXILIAR,formulario);

		return ForwardConstants.FWD_CONSTANTEAUXILIAR_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_CONSTANTEAUXILIAR)
	public String actualizar(@Valid ConstanteAuxiliarForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_CONSTANTEAUXILIAR_FORM;

		}else{

			ConstanteAuxiliar entity = new ConstanteAuxiliar();

			try {

				BeanUtils.copyProperties(entity, formulario);

				constantesauxiliaresService.insertarActualizarConstanteAuxiliar(entity);

				destino = ForwardConstants.RED_LISTADO_CONSTANTESAUXILIARES;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_CONSTANTEAUXILIAR)
	public String borrar(String idConstanteAuxiliar, Map<String, Object> map) {

		constantesauxiliaresService.borrarConstanteAuxiliar(Long.valueOf(idConstanteAuxiliar));

		return ForwardConstants.RED_LISTADO_CONSTANTESAUXILIARES;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_CONSTANTESAUXILIARES)
	public String buscarConstantesAuxiliares(ConstanteAuxiliarForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<ConstanteAuxiliar> resultado = null;

		ConstanteAuxiliar entity = new ConstanteAuxiliar();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = constantesauxiliaresService.findConstantesAuxiliaresByExample(entity, paginacion);
			total = constantesauxiliaresService.countConstantesAuxiliaresByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(CONSTANTESAUXILIARES, resultado);
		map.put(CONSTANTEAUXILIAR,new ConstanteAuxiliarForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_CONSTANTESAUXILIARES;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_CONSTANTESAUXILIARES_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<ConstanteAuxiliar> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		ConstanteAuxiliar constanteauxiliar = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof ConstanteAuxiliar){

			constanteauxiliar = (ConstanteAuxiliar)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = constantesauxiliaresService.findConstantesAuxiliaresByExample(constanteauxiliar, paginacion);
		total = constantesauxiliaresService.countConstantesAuxiliaresByExample(constanteauxiliar);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(CONSTANTEAUXILIAR, new ConstanteAuxiliarForm());
		model.addAttribute(CONSTANTESAUXILIARES, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_CONSTANTESAUXILIARES;
	}



}

