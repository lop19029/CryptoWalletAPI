package gft.cryptoWallet.controllers;

import gft.cryptoWallet.dto.cripto.ConsultaCriptoDTO;
import gft.cryptoWallet.dto.cripto.CriptoMapper;
import gft.cryptoWallet.dto.cripto.RegistroCriptoDTO;
import gft.cryptoWallet.entities.CarteiraTemCripto;
import gft.cryptoWallet.entities.Cripto;
import gft.cryptoWallet.service.CarteiraTemCriptoService;
import gft.cryptoWallet.service.CriptoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/criptos")
public class CriptoController {

    private final CriptoService criptoService;
    private final CarteiraTemCriptoService carteiraTemCriptoService;

    public CriptoController(CriptoService criptoService, CarteiraTemCriptoService carteiraTemCriptoService) {
        this.criptoService = criptoService;
        this.carteiraTemCriptoService = carteiraTemCriptoService;
    }

    @ApiOperation(value = "Lista todas as criptomoedas no sistema.")
    @GetMapping
    public ResponseEntity<Page<ConsultaCriptoDTO>> buscarTodasAsCriptos(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(criptoService.listarTodasAsCriptos(pageable).map(CriptoMapper::fromEntity));
    }

    @ApiOperation(value = "Cria uma nova criptomoeda.")
    @PostMapping
    public ResponseEntity<ConsultaCriptoDTO> salvarCripto(@RequestBody RegistroCriptoDTO dto){
        Cripto cripto = criptoService.salvarCripto(CriptoMapper.fromDTO(dto));
        return ResponseEntity.ok(CriptoMapper.fromEntity(cripto));
    }

    @ApiOperation(value = "Devolve uma criptomoeda usando o simbolo especifico.")
    @GetMapping("{simbolo}")
    public ResponseEntity<ConsultaCriptoDTO> buscarCripto(@PathVariable String simbolo){
        Cripto cripto = criptoService.buscarCripto(simbolo);
        return ResponseEntity.ok(CriptoMapper.fromEntity(cripto));
    }

    @ApiOperation(value = "Altera os dados de uma criptomoeda.")
    @PutMapping("{simbolo}")
    public ResponseEntity<ConsultaCriptoDTO> alterarCripto(@RequestBody RegistroCriptoDTO dto,
                                                           @PathVariable String simbolo) {
        Cripto cripto = criptoService.atualizarCripto(CriptoMapper.fromDTO(dto), simbolo);
        return ResponseEntity.ok(CriptoMapper.fromEntity(cripto));
    }

    @ApiOperation(value = "Elimina uma criptomoeda do sistema. Junto com todas as instancias da mesma " +
            "que estiverem registradas dentro das carteiras.")
    @DeleteMapping("{simbolo}")
    public ResponseEntity<ConsultaCriptoDTO> excluirCripto(@PathVariable String simbolo){

        //Clean crytos from wallets
        Cripto cripto = criptoService.buscarCripto(simbolo);
        List<CarteiraTemCripto> carteiraTemCriptos = carteiraTemCriptoService
                .buscarCarteiraTemCriptoPorCriptoId(cripto.getId());
        carteiraTemCriptoService.excluirListaCarteiraTemCripto(carteiraTemCriptos);

        //Delete cripto
        criptoService.excluirCripto(simbolo);
        return ResponseEntity.ok().build();
    }
}
