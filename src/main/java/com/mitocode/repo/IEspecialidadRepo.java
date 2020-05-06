package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Especialidad;
import com.mitocode.model.Medico;
import com.mitocode.model.Paciente;

@Repository
public interface IEspecialidadRepo extends JpaRepository<Especialidad,Integer>{

	
	
	
}
