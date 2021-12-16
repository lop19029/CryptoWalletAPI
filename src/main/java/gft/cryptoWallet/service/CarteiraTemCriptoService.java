package gft.cryptoWallet.service;

import gft.cryptoWallet.entities.CarteiraTemCripto;
import gft.cryptoWallet.exception.EntityNotFoundException;
import gft.cryptoWallet.repositories.CarteiraTemCriptoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteiraTemCriptoService {

    private final CarteiraTemCriptoRepository carteiraTemCriptoRepository;

    public CarteiraTemCriptoService(CarteiraTemCriptoRepository carteiraTemCriptoRepository) {
        this.carteiraTemCriptoRepository = carteiraTemCriptoRepository;
    }

    public CarteiraTemCripto salvarCarteiraTemCripto(CarteiraTemCripto carteiraTemCripto) {
        return carteiraTemCriptoRepository.save(carteiraTemCripto);
    }

    public CarteiraTemCripto buscarCarteiraTemCripto(Long id) {
        Optional<CarteiraTemCripto> optional = carteiraTemCriptoRepository.findById(id);

        return optional.orElseThrow(() ->
                new EntityNotFoundException("Nenhuma relação Carteira - Cripto encontrada."));
    }

    public List<CarteiraTemCripto> buscarCarteiraTemCriptoPorCriptoId(Long cripto_id) {
        return carteiraTemCriptoRepository.findByCriptoId(cripto_id);
    }

    public CarteiraTemCripto atualizarCarteiraTemCripto(CarteiraTemCripto c, Long id) {
        CarteiraTemCripto carteiraTemCriptoOriginal = this.buscarCarteiraTemCripto(id);

        c.setId(carteiraTemCriptoOriginal.getId());

        return carteiraTemCriptoRepository.save(c);
    }

    public void excluirCarteiraTemCripto(Long id){
        carteiraTemCriptoRepository.deleteById(id);
    }

    public void excluirListaCarteiraTemCripto(List<CarteiraTemCripto> criptos){
        carteiraTemCriptoRepository.deleteAll(criptos);
    }


}
