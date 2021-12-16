package gft.cryptoWallet.dto.cliente;

import gft.cryptoWallet.dto.endereco.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroClienteDTO {
    private String cpf;
    private String nome;
    private String email;
    private EnderecoDTO endereco;
}
