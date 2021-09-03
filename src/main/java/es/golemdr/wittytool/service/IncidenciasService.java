package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Incidencia;
import es.golemdr.wittytool.repository.IncidenciasRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class IncidenciasService {

		@Autowired
		private IncidenciasRepository incidenciasRepository;


		public List<Incidencia> getIncidencias() {

			return incidenciasRepository.findAll();

		}


		public List<Incidencia> getIncidencias(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalIncidencias());

			return incidenciasRepository.findAll(paginacion).getContent();

		}


		public int getTotalIncidencias(){

			return incidenciasRepository.findAll().size();

		}


		public Incidencia insertarActualizarIncidencia(Incidencia incidencia) {

			return incidenciasRepository.save(incidencia);

		}


		public Incidencia getById(Long idIncidencia) {

			Incidencia resultado = null;

			Optional<Incidencia> incidencia = incidenciasRepository.findById(idIncidencia);

			if(incidencia.isPresent()) {
				resultado = incidencia.get();
			}

			return resultado;

		}


		public void borrarIncidencia(Long idIncidencia) {

			incidenciasRepository.deleteById(idIncidencia);

		}

		public List<Incidencia> findIncidenciasByExample(Incidencia incidencia, PaginacionBean paginacion) {

			return incidenciasRepository.findIncidencias(incidencia, paginacion);

		}

		public int countIncidenciasByExample(Incidencia incidencia) {

			return incidenciasRepository.findIncidencias(incidencia, null).size();

		}

}

