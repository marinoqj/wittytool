package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Anotacion;
import es.golemdr.wittytool.repository.AnotacionesRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class AnotacionesService {

		@Autowired
		private AnotacionesRepository anotacionesRepository;


		public List<Anotacion> getAnotaciones() {

			return anotacionesRepository.findAll();

		}


		public List<Anotacion> getAnotaciones(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalAnotaciones());

			return anotacionesRepository.findAll(paginacion).getContent();

		}


		public int getTotalAnotaciones(){

			return anotacionesRepository.findAll().size();

		}


		public Anotacion insertarActualizarAnotacion(Anotacion anotacion) {

			return anotacionesRepository.save(anotacion);

		}


		public Anotacion getById(Long idAnotacion) {

			Anotacion resultado = null;

			Optional<Anotacion> anotacion = anotacionesRepository.findById(idAnotacion);

			if(anotacion.isPresent()) {
				resultado = anotacion.get();
			}

			return resultado;

		}


		public void borrarAnotacion(Long idAnotacion) {

			anotacionesRepository.deleteById(idAnotacion);

		}

		public List<Anotacion> findAnotacionesByExample(Anotacion anotacion, PaginacionBean paginacion) {

			return anotacionesRepository.findAnotaciones(anotacion, paginacion);

		}

		public int countAnotacionesByExample(Anotacion anotacion) {

			return anotacionesRepository.findAnotaciones(anotacion, null).size();

		}

}

