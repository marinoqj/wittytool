package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Proyecto;
import es.golemdr.wittytool.repository.ProyectosRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class ProyectosService {

		@Autowired
		private ProyectosRepository proyectosRepository;


		public List<Proyecto> getProyectos() {

			return proyectosRepository.findAll();

		}


		public List<Proyecto> getProyectos(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalProyectos());

			return proyectosRepository.findAll(paginacion).getContent();

		}


		public int getTotalProyectos(){

			return proyectosRepository.findAll().size();

		}


		public Proyecto insertarActualizarProyecto(Proyecto proyecto) {

			return proyectosRepository.save(proyecto);

		}


		public Proyecto getById(Long idProyecto) {

			Proyecto resultado = null;

			Optional<Proyecto> proyecto = proyectosRepository.findById(idProyecto);

			if(proyecto.isPresent()) {
				resultado = proyecto.get();
			}

			return resultado;

		}


		public void borrarProyecto(Long idProyecto) {

			proyectosRepository.deleteById(idProyecto);

		}

		public List<Proyecto> findProyectosByExample(Proyecto proyecto, PaginacionBean paginacion) {

			return proyectosRepository.findProyectos(proyecto, paginacion);

		}

		public int countProyectosByExample(Proyecto proyecto) {

			return proyectosRepository.findProyectos(proyecto, null).size();

		}

}

