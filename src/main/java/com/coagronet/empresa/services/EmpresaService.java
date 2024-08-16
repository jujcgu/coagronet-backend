package com.coagronet.empresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coagronet.empresa.Empresa;
import com.coagronet.empresa.repositories.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    // Create
    public Empresa addEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    // Read all
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    // Read by ID
    public Empresa getEmpresaById(Integer id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.orElse(null);
    }

    // Update
    public Empresa updateEmpresa(Integer id, Empresa empresaDetails) {
        Empresa empresa = getEmpresaById(id);
        if (empresa != null) {
            empresa.setNombre(empresaDetails.getNombre());
            empresa.setTipoIdentificacion(empresaDetails.getTipoIdentificacion());
            empresa.setEstado(empresaDetails.getEstado());
            empresa.setCelular(empresaDetails.getCelular());
            empresa.setContacto(empresaDetails.getContacto());
            empresa.setCorreo(empresaDetails.getCorreo());
            empresa.setDescripcion(empresaDetails.getDescripcion());
            empresa.setPersona(empresaDetails.getPersona());
            return empresaRepository.save(empresa);
        }
        return null;
    }

    // Delete
    public void deleteEmpresa(Integer id) {
        empresaRepository.deleteById(id);
    }

}
