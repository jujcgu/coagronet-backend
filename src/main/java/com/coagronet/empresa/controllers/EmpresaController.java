package com.coagronet.empresa.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.empresa.Empresa;
import com.coagronet.empresa.dtos.EmpresaDTO;
import com.coagronet.empresa.mappers.EmpresaMapper;
import com.coagronet.empresa.services.EmpresaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<?> getAllEmpresas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sortBy) {

        String[] sortParams = sortBy.split(",");
        if (sortParams.length != 2
                || (!sortParams[1].equalsIgnoreCase("asc") && !sortParams[1].equalsIgnoreCase("desc"))) {
            return ResponseEntity.badRequest().body(
                    "El parámetro 'sortBy' debe tener el formato 'campo,dirección', donde dirección es 'asc' o 'desc'.");
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EmpresaDTO> empresaPage = empresaService.getAllEmpresas(pageable)
                .map(EmpresaMapper.INSTANCE::toEmpresaDTO);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("totalElements", empresaPage.getTotalElements());
        header.put("totalPages", empresaPage.getTotalPages());
        header.put("size", empresaPage.getSize());
        header.put("number", empresaPage.getNumber());
        header.put("sort", empresaPage.getSort());
        header.put("first", empresaPage.isFirst());
        header.put("last", empresaPage.isLast());
        header.put("numberOfElements", empresaPage.getNumberOfElements());
        header.put("empty", empresaPage.isEmpty());

        response.put("header", header);
        response.put("data", empresaPage.getContent());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable Long id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        if (empresa != null) {
            return ResponseEntity.ok(EmpresaMapper.INSTANCE.toEmpresaDTO(empresa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = EmpresaMapper.INSTANCE.toEmpresa(empresaDTO);
        Empresa savedEmpresa = empresaService.save(empresa);
        return ResponseEntity.ok(EmpresaMapper.INSTANCE.toEmpresaDTO(savedEmpresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = EmpresaMapper.INSTANCE.toEmpresa(empresaDTO);
        empresa.setId(id);
        Empresa updatedEmpresa = empresaService.update(empresa);
        return ResponseEntity.ok(EmpresaMapper.INSTANCE.toEmpresaDTO(updatedEmpresa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        empresaService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

}
