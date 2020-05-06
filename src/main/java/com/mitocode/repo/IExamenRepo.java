package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Examen;
import com.mitocode.model.Medico;
import com.mitocode.model.Paciente;

@Repository
public interface IExamenRepo extends JpaRepository<Examen,Integer>{

	
	
	
}
