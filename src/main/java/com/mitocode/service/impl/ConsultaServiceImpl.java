package com.mitocode.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.service.IConsultaService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConsultaServiceImpl implements IConsultaService{

	@Autowired
	private IConsultaRepo repo;
	
	@Autowired
	private IConsultaExamenRepo ceRepo;
	
	
	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO) {
		// TODO Auto-generated method stub
//		consultaDTO.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(consultaDTO.getConsulta()));
//		repo.save(consultaDTO.getConsulta());
		consultaDTO.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(consultaDTO.getConsulta()));
		repo.save(consultaDTO.getConsulta());
		
		consultaDTO.getListExamen().forEach(ex -> ceRepo.registrar(consultaDTO.getConsulta().getIdConsulta(), ex.getIdExamen()));
		
		return consultaDTO.getConsulta();
	}
	
	@Override
	public Consulta registrar(Consulta obj) {
		
		obj.getDetalleConsulta().forEach(det ->{
			det.setConsulta(obj);
		});
		
		/*
	for (DetalleConsulta de : obj.getDetalleConsulta){
	det.setConsulta(obj);
	}
		*/

		return repo.save(obj);
		
	}

	@Override
	public void modificar(Consulta obj) {
		repo.save(obj);
		
	}

	@Override
	public List<Consulta> listar() {
		return repo.findAll();
	}

	@Override
	public Consulta leerporId(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public void eliminar(Integer id) {
		repo.delete(id);
	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		return repo.buscar(filtro.getDni() , filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(FiltroConsultaDTO filtro) {
		LocalDateTime fechaSfte = filtro.getFechaConsulta().plusDays(1);
		return repo.buscarFecha(filtro.getFechaConsulta(), fechaSfte );
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList();
		repo.listarResumen().forEach(x->{
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		return consultas;
	}

	@Override
	public byte[] generarReporte()  {
		byte[] data= null;
		try {
			//PARA ENVIAR PARAMETROS JRException
			//HashMap<String, String>params = new HashMap<String, String>();
			//params.put("txt_empresa","dato");
			File file= new ClassPathResource("/reportes/consulta.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return data;
	}



	
}
