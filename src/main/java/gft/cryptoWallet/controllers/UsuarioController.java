package gft.cryptoWallet.controllers;

import gft.cryptoWallet.dto.usuario.ConsultaUsuarioDTO;
import gft.cryptoWallet.dto.usuario.RegistroUsuarioDTO;
import gft.cryptoWallet.dto.usuario.UsuarioMapper;
import gft.cryptoWallet.entities.Usuario;
import gft.cryptoWallet.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ConsultaUsuarioDTO> salvarUsuario(@RequestBody RegistroUsuarioDTO dto) {
        Usuario usuario = usuarioService.salvarUsuario(UsuarioMapper.fromDTO(dto));

        return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));
    }
}
