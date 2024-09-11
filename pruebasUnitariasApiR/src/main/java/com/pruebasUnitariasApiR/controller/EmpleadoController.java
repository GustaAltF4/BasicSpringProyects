package com.pruebasUnitariasApiR.controller;

import com.pruebasUnitariasApiR.model.Empleado;
import com.pruebasUnitariasApiR.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return service.saveEmpleado(empleado);
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return service.getAllEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable("id") Long empleadoId){
        return service.getEmpleadoById(empleadoId)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("id") Long empleadoId, @RequestBody Empleado empleado){
        return service.getEmpleadoById(empleadoId)
                .map(empleadoGuardado->{
                    empleadoGuardado.setNombre(empleado.getNombre());
                    empleadoGuardado.setApellido(empleado.getApellido());
                    empleadoGuardado.setEmail(empleado.getEmail());

                    Empleado empleadoActualizado = service.updateEmpleado(empleadoGuardado);

                    return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable("id")Long id){
        service.deleteEmpleado(id);
        return new ResponseEntity<>("Empleado Eliminado", HttpStatus.OK);
    }

}
