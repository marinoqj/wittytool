package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Funcionalidad;
import es.golemdr.wittytool.repository.FuncionalidadesRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class FuncionalidadesService {

		@Autowired
		private FuncionalidadesRepository funcionalidadesRepository;


		public List<Funcionalidad> getFuncionalidades() {

			return funcionalidadesRepository.findAll();

		}


		public List<Funcionalidad> getFuncionalidades(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalFuncionalidades());

			return funcionalidadesRepository.findAll(paginacion).getContent();

		}


		public int getTotalFuncionalidades(){

			return funcionalidadesRepository.findAll().size();

		}


		public Funcionalidad insertarActualizarFuncionalidad(Funcionalidad funcionalidad) {

			return funcionalidadesRepository.save(funcionalidad);

		}


		public Funcionalidad getById(Long idFuncionalidad) {

			Funcionalidad resultado = null;

			Optional<Funcionalidad> funcionalidad = funcionalidadesRepository.findById(idFuncionalidad);

			if(funcionalidad.isPresent()) {
				resultado = funcionalidad.get();
			}

			return resultado;

		}


		public void borrarFuncionalidad(Long idFuncionalidad) {

			funcionalidadesRepository.deleteById(idFuncionalidad);

		}

		public List<Funcionalidad> findFuncionalidadesByExample(Funcionalidad funcionalidad, PaginacionBean paginacion) {

			return funcionalidadesRepository.findFuncionalidades(funcionalidad, paginacion);

		}

		public int countFuncionalidadesByExample(Funcionalidad funcionalidad) {

			return funcionalidadesRepository.findFuncionalidades(funcionalidad, null).size();

		}

}

