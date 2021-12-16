package gft.cryptoWallet.controllers;

import gft.cryptoWallet.dto.cliente.ClienteMapper;
import gft.cryptoWallet.dto.cliente.ConsultaClienteDTO;
import gft.cryptoWallet.dto.cliente.RegistroClienteDTO;
import gft.cryptoWallet.entities.Cliente;
import gft.cryptoWallet.service.CarteiraService;
import gft.cryptoWallet.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final CarteiraService carteiraService;

    public ClienteController(ClienteService clienteService, CarteiraService carteiraService) {
        this.clienteService = clienteService;
        this.carteiraService = carteiraService;
    }

    @ApiOperation(value = "Lista todos os clientes no sistema.")
    @GetMapping
    public ResponseEntity<Page<ConsultaClienteDTO>> buscarTodosOsClientes(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(clienteService.listarTodosOsClientes(pageable).map(ClienteMapper::fromEntity));
    }

    @ApiOperation(value = "Registra um novo cliente.")
    @PostMapping
    public ResponseEntity<ConsultaClienteDTO> salvarClientes(@RequestBody RegistroClienteDTO dto) {
        Cliente cliente = clienteService.salvarCliente(ClienteMapper.fromDTO(dto));

        return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
    }

    @ApiOperation(value = "Devolve um cliente com um id especifico.")
    @GetMapping("{id}")
    public ResponseEntity<ConsultaClienteDTO> buscarCliente(@PathVariable Long id){
        Cliente cliente = clienteService.buscarCliente(id);
        return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
    }

    @ApiOperation(value = "Altera os dados do cliente com um id especifico.")
    @PutMapping("{id}")
    public ResponseEntity<ConsultaClienteDTO> alterarCliente(@RequestBody RegistroClienteDTO dto, @PathVariable Long id){
        Cliente cliente = clienteService.atualizarCliente(ClienteMapper.fromDTO(dto), id);
        return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
    }

    @ApiOperation(value = "Elimina um cliente do sistema com um id especifico " +
            "junto com todas as carteiras ligadas ao mesmo.")
    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaClienteDTO> excluirCliente(@PathVariable Long id) {
        //Delete any client's wallet
        carteiraService.excluirCarteira(null, id);
        clienteService.excluirCliente(id);
        return ResponseEntity.ok().build();
    }
}
