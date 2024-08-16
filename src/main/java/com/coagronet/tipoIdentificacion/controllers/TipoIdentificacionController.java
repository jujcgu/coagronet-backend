package com.coagronet.tipoIdentificacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.infrastructure.configuration.ResponseHandler;
import com.coagronet.tipoIdentificacion.TipoIdentificacion;
import com.coagronet.tipoIdentificacion.services.TipoIdentificacionService;

@RestController
@RequestMapping("/api/v1/tipo_identificacion")
@CrossOrigin(origins = "*")
public class TipoIdentificacionController {
    @Autowired
    private TipoIdentificacionService tipoIdentificacionService;

    @RequestMapping("/test")
    ResponseEntity<Object> getAllCountry() {
        // LOGGER.info("Country list fetched");
        return ResponseHandler.generateResponse(
                HttpStatus.OK,
                false,
                "Success",
                null);
    }

    // Create a new TipoIdentificacion
    @PostMapping("/add")
    public TipoIdentificacion add(@RequestBody TipoIdentificacion tipoIdentificacion) {
        return tipoIdentificacionService.add(tipoIdentificacion);
    }

    // Get all TipoIdentificacion
    @GetMapping("/all")
    public List<TipoIdentificacion> getAll() {
        return tipoIdentificacionService.getAll();
    }

    // Get a TipoIdentificacion by ID
    @GetMapping("/{id}")
    public TipoIdentificacion getById(@PathVariable Integer id) {
        return tipoIdentificacionService.getById(id);
    }

    // Update a TipoIdentificacion
    @PutMapping("/update/{id}")
    public TipoIdentificacion update(@PathVariable Integer id,
            @RequestBody TipoIdentificacion tipoIdentificacionDetails) {
        return tipoIdentificacionService.update(id, tipoIdentificacionDetails);
    }

    // Delete a TipoIdentificacion
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        tipoIdentificacionService.delete(id);
    }
}
