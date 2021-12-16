package gft.cryptoWallet.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_carteira_tem_cripto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraTemCripto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carteira_id")
    private Long carteiraId;

    @Column(name = "cripto_id")
    private Long criptoId;

    private BigDecimal quantidade;
}
