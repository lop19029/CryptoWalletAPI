package gft.cryptoWallet.dto.cripto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCriptoDTO {
    private String nome;
    private String simbolo;
    private BigDecimal valor;
}
