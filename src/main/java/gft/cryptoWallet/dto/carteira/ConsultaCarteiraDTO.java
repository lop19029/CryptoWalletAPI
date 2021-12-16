package gft.cryptoWallet.dto.carteira;

import gft.cryptoWallet.dto.carteiraTemCripto.ConsultaCarteiraTemCriptoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaCarteiraDTO {
    private Long id;
    private Long clienteId;
    private List<ConsultaCarteiraTemCriptoDTO> criptos;
}
