package com.mitocode.service;

import java.io.IOException;
import java.util.List;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;

import net.sf.jasperreports.engine.JRException;

public interface IConsultaService extends ICRUD<Consulta>{

	Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO);
	
	List<Consulta> buscar(FiltroConsultaDTO filtro);
	List<Consulta> buscarFecha(FiltroConsultaDTO filtro);
	List<ConsultaResumenDTO> listarResumen();
	byte[] generarReporte();
}