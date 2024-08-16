package com.coagronet.empresa.controllers;

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

import com.coagronet.empresa.Empresa;
import com.coagronet.empresa.services.EmpresaService;
import com.coagronet.infrastructure.configuration.ResponseHandler;

@RestController
@RequestMapping("/api/v1/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/add")
    public ResponseEntity<Object> addEmpresa(@RequestBody Empresa empresa) {
        try {
            empresaService.addEmpresa(empresa);
            return ResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Empresa creado exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al crear la empresa.",
                    null);
        }
    }

    @GetMapping("/all")
    public List<Empresa> getAllEmpresas() {
        return empresaService.getAllEmpresas();
    }

    @GetMapping("/{id}")
    public Empresa getEmpresaById(@PathVariable Integer id) {
        return empresaService.getEmpresaById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable Integer id, @RequestBody Empresa empresaDetails) {
        try {
            Empresa existingEmpresa = empresaService.getEmpresaById(id);
            if (existingEmpresa == null) {
                return ResponseHandler.generateResponse(
                        HttpStatus.NOT_FOUND,
                        false,
                        "Empresa no encontrada.",
                        null);
            }
            empresaService.updateEmpresa(id, empresaDetails);
            return ResponseHandler.generateResponse(
                    HttpStatus.OK,
                    false,
                    "Empresa actualizada exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al actualizar la empresa.",
                    null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleleEmpresa(@PathVariable Integer id) {
        try {
            Empresa existingEmpresa = empresaService.getEmpresaById(id);
            if (existingEmpresa == null) {
                return ResponseHandler.generateResponse(
                        HttpStatus.NOT_FOUND,
                        false,
                        "Empresa no encontrada.",
                        null);
            }
            empresaService.deleteEmpresa(id);
            return ResponseHandler.generateResponse(
                    HttpStatus.OK,
                    false,
                    "Empresa borrada exitosamente.",
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    true,
                    "Error al borrar la empresa.",
                    null);
        }
    }
}
