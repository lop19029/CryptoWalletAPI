package gft.cryptoWallet.service;

import gft.cryptoWallet.entities.Usuario;
import gft.cryptoWallet.exception.EntityNotFoundException;
import gft.cryptoWallet.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarUsuarioPorEmail(String email) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return optionalUsuario.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarUsuarioPorEmail(username);
    }

    public Usuario buscarUsuarioPorId(Long idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
        if (optional.isEmpty()){
            throw new EntityNotFoundException("Usuario não encontrado.");
        }
        return optional.get();
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
