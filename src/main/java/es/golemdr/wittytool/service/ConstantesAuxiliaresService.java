package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.ConstanteAuxiliar;
import es.golemdr.wittytool.repository.ConstantesAuxiliaresRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class ConstantesAuxiliaresService {

		@Autowired
		private ConstantesAuxiliaresRepository constantesauxiliaresRepository;


		public List<ConstanteAuxiliar> getConstantesAuxiliares() {

			return constantesauxiliaresRepository.findAll();

		}


		public List<ConstanteAuxiliar> getConstantesAuxiliares(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalConstantesAuxiliares());

			return constantesauxiliaresRepository.findAll(paginacion).getContent();

		}


		public int getTotalConstantesAuxiliares(){

			return constantesauxiliaresRepository.findAll().size();

		}


		public ConstanteAuxiliar insertarActualizarConstanteAuxiliar(ConstanteAuxiliar constanteauxiliar) {

			return constantesauxiliaresRepository.save(constanteauxiliar);

		}


		public ConstanteAuxiliar getById(Long idConstanteAuxiliar) {

			ConstanteAuxiliar resultado = null;

			Optional<ConstanteAuxiliar> constanteauxiliar = constantesauxiliaresRepository.findById(idConstanteAuxiliar);

			if(constanteauxiliar.isPresent()) {
				resultado = constanteauxiliar.get();
			}

			return resultado;

		}


		public void borrarConstanteAuxiliar(Long idConstanteAuxiliar) {

			constantesauxiliaresRepository.deleteById(idConstanteAuxiliar);

		}

		public List<ConstanteAuxiliar> findConstantesAuxiliaresByExample(ConstanteAuxiliar constanteauxiliar, PaginacionBean paginacion) {

			return constantesauxiliaresRepository.findConstantesAuxiliares(constanteauxiliar, paginacion);

		}

		public int countConstantesAuxiliaresByExample(ConstanteAuxiliar constanteauxiliar) {

			return constantesauxiliaresRepository.findConstantesAuxiliares(constanteauxiliar, null).size();

		}

}

