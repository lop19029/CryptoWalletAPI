package gft.cryptoWallet.dto.endereco;

import gft.cryptoWallet.entities.Endereco;

public class EnderecoMapper {

    public static Endereco fromDTO(EnderecoDTO dto){
        return  new Endereco(dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getCep());
    }

    public static EnderecoDTO fromEntity(Endereco endereco){
        return new EnderecoDTO(endereco.getLogradouro(), endereco.getNumero(),
                endereco.getComplemento(), endereco.getCep());
    }
}
