package gft.cryptoWallet.dto.carteira;

import gft.cryptoWallet.dto.carteiraTemCripto.RegistroCarteiraTemCriptoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCarteiraDTO {
    private Long clienteId;
    private List<RegistroCarteiraTemCriptoDTO> criptos;
}
