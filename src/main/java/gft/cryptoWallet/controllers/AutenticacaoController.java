package gft.cryptoWallet.controllers;

import gft.cryptoWallet.dto.auth.AutenticacaoDTO;
import gft.cryptoWallet.dto.auth.TokenDTO;
import gft.cryptoWallet.service.AutenticacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("v1/auth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }


    @PostMapping
    @ApiOperation(value = "Valida se um usuario esta registrado no sistema e devolve um Token de acesso. O tem um periodo de validade de 10 minutos.")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO){

        try {

            return ResponseEntity.ok(autenticacaoService.autenticar(autenticacaoDTO));

        }catch (AuthenticationException ae) {
            ae.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
