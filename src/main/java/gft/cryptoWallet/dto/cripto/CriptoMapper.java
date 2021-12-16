package gft.cryptoWallet.dto.cripto;

import gft.cryptoWallet.entities.Cripto;

public class CriptoMapper {
    public static Cripto fromDTO(RegistroCriptoDTO dto) {
        return new Cripto(null, dto.getNome(), dto.getSimbolo(), dto.getValor());
    }

    public static ConsultaCriptoDTO fromEntity(Cripto en) {
        return new ConsultaCriptoDTO(en.getId(),en.getNome(),en.getSimbolo(),en.getValor());
    }
}
