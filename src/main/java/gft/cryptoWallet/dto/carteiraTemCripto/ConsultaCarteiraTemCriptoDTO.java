package gft.cryptoWallet.dto.carteiraTemCripto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaCarteiraTemCriptoDTO {
    private Long id;
    private Long carteira_id;
    private Long cripto_id;
    private BigDecimal quantidade;
}
