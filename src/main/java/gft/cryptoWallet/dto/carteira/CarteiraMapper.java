package gft.cryptoWallet.dto.carteira;

import gft.cryptoWallet.dto.carteiraTemCripto.CarteiraTemCriptoMapper;
import gft.cryptoWallet.entities.Carteira;

import java.util.stream.Collectors;

public class CarteiraMapper {

    public static ConsultaCarteiraDTO fromEntity(Carteira carteira){
        return new ConsultaCarteiraDTO(carteira.getId(), carteira.getCliente().getId(),
                carteira.getCriptos().stream().map(CarteiraTemCriptoMapper::fromEntity).collect(Collectors.toList()));
    }
}
