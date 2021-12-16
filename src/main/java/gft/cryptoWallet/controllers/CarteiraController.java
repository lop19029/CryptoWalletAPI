package gft.cryptoWallet.controllers;

import gft.cryptoWallet.dto.carteira.CarteiraMapper;
import gft.cryptoWallet.dto.carteira.ConsultaCarteiraDTO;
import gft.cryptoWallet.dto.carteira.RegistroCarteiraDTO;
import gft.cryptoWallet.dto.carteiraTemCripto.CarteiraTemCriptoMapper;
import gft.cryptoWallet.entities.Carteira;
import gft.cryptoWallet.entities.CarteiraTemCripto;
import gft.cryptoWallet.entities.Cliente;
import gft.cryptoWallet.service.CarteiraService;
import gft.cryptoWallet.service.CarteiraTemCriptoService;
import gft.cryptoWallet.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/carteiras")
public class CarteiraController {

    private final CarteiraService carteiraService;
    private final ClienteService clienteService;
    private final CarteiraTemCriptoService carteiraTemCriptoService;

    public CarteiraController(CarteiraService carteiraService, ClienteService clienteService, CarteiraTemCriptoService carteiraTemCriptoService) {
        this.carteiraService = carteiraService;
        this.clienteService = clienteService;
        this.carteiraTemCriptoService = carteiraTemCriptoService;
    }

    @PostMapping
    public ResponseEntity<ConsultaCarteiraDTO> salvarCarteira(@RequestBody RegistroCarteiraDTO dto) {
        //Get client from clienteId
        Cliente cliente = clienteService.buscarCliente(Long.parseLong(String.valueOf(dto.getClienteId())));
        //Map criptos list
        List<CarteiraTemCripto> criptos = dto.getCriptos().stream().map(CarteiraTemCriptoMapper::fromDTO).collect(Collectors.toList());

        Carteira carteiraComId = carteiraService.salvarCarteira(new Carteira(null, cliente, criptos));

        //Update CarteiraTemCripto objects carteira_id
        carteiraComId.getCriptos().forEach(i -> i.setCarteiraId(carteiraComId.getId()));
        carteiraService.salvarCarteira(carteiraComId);

        return ResponseEntity.ok(CarteiraMapper.fromEntity(carteiraComId));
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaCarteiraDTO>> buscarTodasAsCarteiras(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(carteiraService.listarTodasAsCarteiras(pageable)
                .map(CarteiraMapper::fromEntity));
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsultaCarteiraDTO> buscarCarteira(@PathVariable Long id){
        Carteira carteira = carteiraService.buscarCarteira(id, null);
        return ResponseEntity.ok(CarteiraMapper.fromEntity(carteira));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaCarteiraDTO> excluirCarteira(@PathVariable Long id) {

        carteiraService.excluirCarteira(id, null);
        return ResponseEntity.ok().build();
    }

}
