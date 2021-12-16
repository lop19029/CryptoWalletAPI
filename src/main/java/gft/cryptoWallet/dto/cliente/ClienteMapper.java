package gft.cryptoWallet.dto.cliente;

import gft.cryptoWallet.dto.carteira.CarteiraMapper;
import gft.cryptoWallet.dto.endereco.EnderecoMapper;
import gft.cryptoWallet.entities.Cliente;

public class ClienteMapper {

    public static Cliente fromDTO(RegistroClienteDTO dto){
        return new Cliente(null, dto.getCpf(), dto.getNome(), dto.getEmail(),
                EnderecoMapper.fromDTO(dto.getEndereco()));
    }

    public static ConsultaClienteDTO fromEntity(Cliente cliente){
        return new ConsultaClienteDTO(cliente.getId(), cliente.getCpf(), cliente.getNome(), cliente.getEmail(),
                EnderecoMapper.fromEntity(cliente.getEndereco()));
    }
}
