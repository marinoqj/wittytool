package es.golemdr.wittytool.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.golemdr.wittytool.domain.Bitacora;
import es.golemdr.wittytool.repository.BitacorasRepository;
import es.golemdr.wittytool.ext.utils.paginacion.PaginacionBean;

@Service
public class BitacorasService {

		@Autowired
		private BitacorasRepository bitacorasRepository;


		public List<Bitacora> getBitacoras() {

			return bitacorasRepository.findAll();

		}


		public List<Bitacora> getBitacoras(PaginacionBean paginacionBean) {

			Pageable paginacion = PageRequest.of(paginacionBean.getInicio(),paginacionBean.getElementosXpagina());

			paginacionBean.setTotalRegistros(getTotalBitacoras());

			return bitacorasRepository.findAll(paginacion).getContent();

		}


		public int getTotalBitacoras(){

			return bitacorasRepository.findAll().size();

		}


		public Bitacora insertarActualizarBitacora(Bitacora bitacora) {

			return bitacorasRepository.save(bitacora);

		}


		public Bitacora getById(Long idBitacora) {

			Bitacora resultado = null;

			Optional<Bitacora> bitacora = bitacorasRepository.findById(idBitacora);

			if(bitacora.isPresent()) {
				resultado = bitacora.get();
			}

			return resultado;

		}


		public void borrarBitacora(Long idBitacora) {

			bitacorasRepository.deleteById(idBitacora);

		}

		public List<Bitacora> findBitacorasByExample(Bitacora bitacora, PaginacionBean paginacion) {

			return bitacorasRepository.findBitacoras(bitacora, paginacion);

		}

		public int countBitacorasByExample(Bitacora bitacora) {

			return bitacorasRepository.findBitacoras(bitacora, null).size();

		}

}

