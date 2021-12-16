package gft.cryptoWallet.service;

import gft.cryptoWallet.entities.Cripto;
import gft.cryptoWallet.exception.EntityNotFoundException;
import gft.cryptoWallet.repositories.CriptoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CriptoService {

    private final CriptoRepository criptoRepository;

    public CriptoService(CriptoRepository criptoRepository) {
        this.criptoRepository = criptoRepository;
    }

    public Cripto salvarCripto(Cripto cripto) { return criptoRepository.save(cripto);}

    public Page<Cripto> listarTodasAsCriptos(Pageable pageable) {return criptoRepository.findAll(pageable);}

    public Cripto buscarCripto(String simbolo){
        Optional<Cripto> optionalCripto = criptoRepository.findBySimbolo(simbolo);
        return optionalCripto.orElseThrow(() -> new EntityNotFoundException("Cripto não encontrada."));
    }

    public Cripto atualizarCripto(Cripto cripto, String simbolo){

        Cripto criptoOriginal = this.buscarCripto(simbolo);

        cripto.setId(criptoOriginal.getId());

        return criptoRepository.save(cripto);
    }

    public void excluirCripto(String simbolo){
        Cripto cripto = buscarCripto(simbolo);
        criptoRepository.delete(cripto);
    }
}
