package gft.cryptoWallet.dto.usuario;

import gft.cryptoWallet.entities.Perfil;
import gft.cryptoWallet.entities.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioMapper {
    public static Usuario fromDTO(RegistroUsuarioDTO dto){
        Perfil perfil = new Perfil();
        perfil.setId(dto.getPerfilId());
        return new Usuario(null,dto.getEmail(),new BCryptPasswordEncoder().encode(dto.getSenha()),perfil);
    }

    public static ConsultaUsuarioDTO fromEntity(Usuario usuario){
        return new ConsultaUsuarioDTO(usuario.getId(), usuario.getEmail(), usuario.getPerfil().getNome());

    }
}
