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
import es.golemdr.wittytool.domain.Bitacora;
import es.golemdr.wittytool.domain.form.BitacoraForm;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionFactory;
import es.golemdr.wittytool.service.BitacorasService;



@Controller
public class BitacorasController {

	private static Logger log = LogManager.getLogger(BitacorasController.class);

	private static final String BITACORAS = "bitacoras";
	private static final String BITACORA = "bitacora";

	@Autowired
	private BitacorasService bitacorasService;



	@GetMapping(value=UrlConstants.URL_LISTADO_BITACORAS)
	public String list(@PathVariable("inicio") int inicio,Map<String, Object> map, HttpServletRequest request) {

		List<Bitacora> resultado = null;
        boolean hayFiltro = false;


		PaginacionBean paginacion = new PaginacionBean();
		paginacion.setInicio(inicio - 1);

		resultado = bitacorasService.getBitacoras(paginacion);

		map.put("paginacion", paginacion);
		map.put(BITACORAS, resultado);
		map.put(BITACORA,new BitacoraForm());
        map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_BITACORAS;
	}


	@PostMapping(value=UrlConstants.URL_INSERTAR_BITACORA)
	public String insertar(BitacoraForm formulario, Model model) {

		Bitacora entity = new Bitacora();

		try {

			BeanUtils.copyProperties(entity, formulario);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		bitacorasService.insertarActualizarBitacora(entity);

		return ForwardConstants.RED_LISTADO_BITACORAS; 
	}


	@PostMapping(value=UrlConstants.URL_EDITAR_BITACORA)
	public String editar(String idBitacora, Map<String, Object> map) {

		Bitacora entity = bitacorasService.getById(Long.valueOf(idBitacora));

		BitacoraForm formulario = new BitacoraForm();

		try {

			BeanUtils.copyProperties(formulario, entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}


		map.put("modo", "actualizar");
		map.put(BITACORA,formulario);

		return ForwardConstants.FWD_BITACORA_FORM;
	}


	@PostMapping(value=UrlConstants.URL_ACTUALIZAR_BITACORA)
	public String actualizar(@Valid BitacoraForm formulario, BindingResult result, Model model) {


		String destino = null;

		if (result.hasErrors()) {

			model.addAttribute("modo", "actualizar");
			destino = ForwardConstants.FWD_BITACORA_FORM;

		}else{

			Bitacora entity = new Bitacora();

			try {

				BeanUtils.copyProperties(entity, formulario);

				bitacorasService.insertarActualizarBitacora(entity);

				destino = ForwardConstants.RED_LISTADO_BITACORAS;


			} catch (IllegalAccessException | InvocationTargetException e) {

				log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
			}

		}

		return destino;
	}


	@PostMapping(value=UrlConstants.URL_BORRAR_BITACORA)
	public String borrar(String idBitacora, Map<String, Object> map) {

		bitacorasService.borrarBitacora(Long.valueOf(idBitacora));

		return ForwardConstants.RED_LISTADO_BITACORAS;

	}


	@PostMapping(UrlConstants.URL_BUSCAR_BITACORAS)
	public String buscarBitacoras(BitacoraForm formulario, Map<String, Object> map, HttpServletRequest request) {

		//Antes de nada quitamos el filtro de sesi?n si lo hay...
		request.getSession(false).removeAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		boolean hayFiltro = true;

		int total = 0;

		paginacion.setInicio(0);

		List<Bitacora> resultado = null;

		Bitacora entity = new Bitacora();

		try {

			BeanUtils.copyProperties(entity, formulario);

			resultado = bitacorasService.findBitacorasByExample(entity, paginacion);
			total = bitacorasService.countBitacorasByExample(entity);

		} catch (IllegalAccessException | InvocationTargetException e) {

			log.error(Constantes.PREFIJO_MENSAJE_ERROR,e.getMessage());
		}

		if(total > paginacion.getElementosXpagina()){

			request.getSession(false).setAttribute(Constantes.ATRIBUTO_SESSION_FILTRO, entity);


		}

		paginacion.setInicio(0);
		paginacion.setTotalRegistros(total);

		map.put(BITACORAS, resultado);
		map.put(BITACORA,new BitacoraForm());
		map.put(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_BITACORAS;

	}

	@GetMapping(value=UrlConstants.URL_LISTADO_BITACORAS_FILTRADO)
	public String listadoFiltrado(@PathVariable("inicio") int inicio,Model model, HttpServletRequest request) {

		List<Bitacora> resultado = null;
		PaginacionBean paginacion = PaginacionFactory.getPaginacionBean(request);
		Bitacora bitacora = null;
		boolean hayFiltro = true;

		int total = 0;

		Object filtro = request.getSession(false).getAttribute(Constantes.ATRIBUTO_SESSION_FILTRO);

		if(filtro instanceof Bitacora){

			bitacora = (Bitacora)filtro;
		}

		if(inicio > 1) {
			inicio = (inicio - 1) * paginacion.getElementosXpagina();
		}

		paginacion.setInicio(inicio);


		resultado = bitacorasService.findBitacorasByExample(bitacora, paginacion);
		total = bitacorasService.countBitacorasByExample(bitacora);

		// Actualizamos la paginaci?n
		paginacion.setInicio(inicio/paginacion.getElementosXpagina());
		paginacion.setTotalRegistros(total);


		model.addAttribute(BITACORA, new BitacoraForm());
		model.addAttribute(BITACORAS, resultado);
		model.addAttribute(Constantes.ATRIBUTO_SESSION_HAY_FILTRO, hayFiltro);

		return ForwardConstants.FWD_LISTADO_BITACORAS;
	}



}

