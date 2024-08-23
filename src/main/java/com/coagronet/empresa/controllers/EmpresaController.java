package com.coagronet.empresa.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import com.coagronet.empresa.dtos.EmpresaDTO;
import com.coagronet.empresa.mappers.EmpresaMapper;
import com.coagronet.empresa.services.EmpresaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpresaMapper empresaMapper;

    public EmpresaController(EmpresaService empresaService, EmpresaMapper empresaMapper) {
        this.empresaService = empresaService;
        this.empresaMapper = empresaMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable Integer id) {
        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok(empresaMapper.toEmpresaDTO(empresa));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAll();
        List<EmpresaDTO> empresaDTOs = empresas.stream()
                .map(empresaMapper::toEmpresaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(empresaDTOs);
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = empresaMapper.toEmpresa(empresaDTO);
        Empresa createdEmpresa = empresaService.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaMapper.toEmpresaDTO(createdEmpresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable Integer id, @RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = empresaMapper.toEmpresa(empresaDTO);
        empresa.setId(id);
        Empresa updatedEmpresa = empresaService.update(empresa);
        return ResponseEntity.ok(empresaMapper.toEmpresaDTO(updatedEmpresa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Integer id) {
        empresaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
