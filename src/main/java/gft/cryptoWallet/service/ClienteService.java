package gft.cryptoWallet.service;

import gft.cryptoWallet.entities.Cliente;
import gft.cryptoWallet.exception.EntityNotFoundException;
import gft.cryptoWallet.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Page<Cliente> listarTodosOsClientes(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Cliente buscarCliente(Long id) {

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        return clienteOptional.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Cliente atualizarCliente(Cliente cliente, Long id){

        Cliente clienteOriginal = this.buscarCliente(id);

        cliente.setId(clienteOriginal.getId());

        return clienteRepository.save(cliente);
    }

    public void excluirCliente(Long id){
        Cliente cliente = this.buscarCliente(id);
        clienteRepository.delete(cliente);
    }
}
