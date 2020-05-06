package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Paciente;

public interface ICRUD<T> {

	T registrar(T pac);
	void modificar(T pac);
	List<T>listar();
	T leerporId(Integer id);
	void eliminar(Integer id);
	
}
