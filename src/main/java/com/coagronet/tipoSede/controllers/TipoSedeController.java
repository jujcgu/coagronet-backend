package com.coagronet.tipoSede.controllers;

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
import com.coagronet.tipoSede.TipoSede;
import com.coagronet.tipoSede.services.TipoSedeService;

@RestController
@RequestMapping("/api/v1/tipo_sede")
@CrossOrigin(origins = "*")
public class TipoSedeController {
    @Autowired
    private TipoSedeService tipoSedeService;

    @PostMapping
    public ResponseEntity<Object> addTipoSede(@RequestBody TipoSede tipoSede) {
        try {
            tipoSedeService.addTipoSede(tipoSede);
            return ResponseHandler.generateResponse(HttpStatus.CREATED,
                    false,
                    "Tipo de Sede creado exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al crear el tipo de sede.",
                    null);
        }
    }

    @GetMapping
    public List<TipoSede> getAllTipoSedes() {
        return tipoSedeService.getAllTipoSedes();
    }

    @GetMapping("/{id}")
    public TipoSede getTipoSedeById(@PathVariable Integer id) {
        return tipoSedeService.getTipoSedeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTipoSede(@PathVariable Integer id, @RequestBody TipoSede tipoSedeDetails) {
        try {
            TipoSede existingTipoSede = tipoSedeService.getTipoSedeById(id);
            if (existingTipoSede == null) {
                return ResponseHandler.generateResponse(
                        HttpStatus.NOT_FOUND,
                        false,
                        "Tipo de Sede no encontrada.",
                        null);
            }
            tipoSedeService.updateTipoSede(id, tipoSedeDetails);
            return ResponseHandler.generateResponse(
                    HttpStatus.NO_CONTENT,
                    false,
                    "Tipo de Sede actualizada exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al actualizar la Tipo de Sede.",
                    null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleleTipoSede(@PathVariable Integer id) {
        try {
            TipoSede existingTipoSede = tipoSedeService.getTipoSedeById(id);
            if (existingTipoSede == null) {
                return ResponseHandler.generateResponse(
                        HttpStatus.NOT_FOUND,
                        false,
                        "Tipo de Sede no encontrada.",
                        null);
            }
            tipoSedeService.deleteTipoSede(id);
            return ResponseHandler.generateResponse(
                    HttpStatus.NO_CONTENT,
                    false,
                    "Tipo de Sede borrada exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al borrar el Tipo de Sede.",
                    null);
        }
    }
}
