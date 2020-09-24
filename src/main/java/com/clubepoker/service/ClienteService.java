package com.clubepoker.service;

import com.clubepoker.model.Cliente;
import com.clubepoker.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente>listarTodosClientes(){
        return (List<Cliente>) clienteRepository.findAll();
    }

    public void salvarCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }


}
