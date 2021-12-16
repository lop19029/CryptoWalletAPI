package gft.cryptoWallet.dto.carteiraTemCripto;

import gft.cryptoWallet.entities.CarteiraTemCripto;

public class CarteiraTemCriptoMapper {
    public static CarteiraTemCripto fromDTO(RegistroCarteiraTemCriptoDTO dto){
        return new CarteiraTemCripto(null, null, dto.getCripto_id(), dto.getQuantidade());
    }

    public static CarteiraTemCripto fromDTOTransacao(RegistroCarteiraTemCriptoDTO dto){
        return new CarteiraTemCripto(null, dto.getCarteira_id(), dto.getCripto_id(), dto.getQuantidade());
    }

    public static ConsultaCarteiraTemCriptoDTO fromEntity(CarteiraTemCripto en){
        return new ConsultaCarteiraTemCriptoDTO(en.getId(),en.getCarteiraId(), en.getCriptoId(),en.getQuantidade());
    }
}
