package com.pruebasUnitariasApiR.service.impl;

import com.pruebasUnitariasApiR.exception.ResourceNotFoundException;
import com.pruebasUnitariasApiR.model.Empleado;
import com.pruebasUnitariasApiR.repository.EmpleadoRepo;
import com.pruebasUnitariasApiR.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepo repository;

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        Optional<Empleado> empleadoGuardado = repository.findByEmail(empleado.getEmail());
        if (empleadoGuardado.isPresent()){
            throw new ResourceNotFoundException("El empleado con ese Email ya existe: "+ empleado.getEmail());
        }
        return repository.save(empleado);
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return repository.findAll();
    }

    @Override
    public Optional<Empleado> getEmpleadoById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empleado updateEmpleado(Empleado empleadoActualizado) {
        return repository.save(empleadoActualizado);
    }

    @Override
    public void deleteEmpleado(Long id) {
        repository.deleteById(id);

    }
}
