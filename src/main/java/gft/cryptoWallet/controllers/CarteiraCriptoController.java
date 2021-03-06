package gft.cryptoWallet.controllers;

import gft.cryptoWallet.dto.carteiraTemCripto.CarteiraTemCriptoMapper;
import gft.cryptoWallet.dto.carteiraTemCripto.ConsultaCarteiraTemCriptoDTO;
import gft.cryptoWallet.dto.carteiraTemCripto.RegistroCarteiraTemCriptoDTO;
import gft.cryptoWallet.entities.Carteira;
import gft.cryptoWallet.entities.CarteiraTemCripto;
import gft.cryptoWallet.exception.DataInsertionException;
import gft.cryptoWallet.exception.EntityNotFoundException;
import gft.cryptoWallet.service.CarteiraService;
import gft.cryptoWallet.service.CarteiraTemCriptoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/transacoes")
public class CarteiraCriptoController {

    private final CarteiraTemCriptoService carteiraTemCriptoService;
    private final CarteiraService carteiraService;

    public CarteiraCriptoController(CarteiraTemCriptoService carteiraTemCriptoService, CarteiraService carteiraService) {
        this.carteiraTemCriptoService = carteiraTemCriptoService;
        this.carteiraService = carteiraService;
    }

    @ApiOperation(value = "Adiciona criptomoeda numa carteira, ou soma a quantidade especificada caso essa carteira ja" +
            "possua o criptoativo.")
    @PostMapping(path = "/adicionar-criptos")
    public final ResponseEntity<ConsultaCarteiraTemCriptoDTO> salvarCripto(@RequestBody RegistroCarteiraTemCriptoDTO dto){
        CarteiraTemCripto carteiraTemCripto = CarteiraTemCriptoMapper.fromDTOTransacao(dto);

        //Update wallet
        Carteira carteira = carteiraService.buscarCarteira(carteiraTemCripto.getCarteiraId(), null);

        //Sum or Create?

        //Add cripto quantity if present in the wallet
        for (CarteiraTemCripto c:
                carteira.getCriptos()) {
            if (c.getCriptoId() == carteiraTemCripto.getCriptoId()){
                c.setQuantidade(c.getQuantidade().add(carteiraTemCripto.getQuantidade()));
                carteiraService.salvarCarteira(carteira);
                return ResponseEntity.ok().build();
            }
        }
        //Else add new cripto to the wallet
        CarteiraTemCripto carteiraTemCriptoSalva = carteiraTemCriptoService.salvarCarteiraTemCripto(carteiraTemCripto);

        return ResponseEntity.ok(CarteiraTemCriptoMapper.fromEntity(carteiraTemCriptoSalva));
    }
    @ApiOperation(value = "Reduz a quantidade de uma criptomoeda numa carteira caso o resultado da opera????o seja >= 0.")
    @PostMapping(path = "/diminuir-criptos")
    public final ResponseEntity<ConsultaCarteiraTemCriptoDTO> diminuirCripto(@RequestBody RegistroCarteiraTemCriptoDTO dto){
        CarteiraTemCripto carteiraTemCripto = CarteiraTemCriptoMapper.fromDTOTransacao(dto);

        //Update wallet
        Carteira carteira = carteiraService.buscarCarteira(carteiraTemCripto.getCarteiraId(), null);

        //Sum or Create?

        //Add cripto quantity if present in the wallet
        for (CarteiraTemCripto c:
                carteira.getCriptos()) {
            if (c.getCriptoId() == carteiraTemCripto.getCriptoId()){
                c.setQuantidade(c.getQuantidade().subtract(carteiraTemCripto.getQuantidade()));
                if(c.getQuantidade().compareTo(BigDecimal.ZERO) < 0){
                    throw new DataInsertionException("O resultado da opera????o n??o pode ser menor do que 0.");
                }
                carteiraService.salvarCarteira(carteira);
                return ResponseEntity.ok().build();
            }
        }

        throw new EntityNotFoundException("Essa cripto n??o esta presente na carteira especificada.");
    }

    @ApiOperation(value = "Elimina uma criptomoeda de uma carteira especifica.")
    @DeleteMapping("{id}")
    public ResponseEntity<CarteiraTemCripto> excluirCriptoDeCarteira(@PathVariable Long id){
        carteiraTemCriptoService.excluirCarteiraTemCripto(id);
        return ResponseEntity.ok().build();
    }
}
