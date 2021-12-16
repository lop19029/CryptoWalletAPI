package gft.cryptoWallet.service;

import gft.cryptoWallet.entities.Carteira;
import gft.cryptoWallet.exception.CryptoWalletException;
import gft.cryptoWallet.exception.DataInsertionException;
import gft.cryptoWallet.exception.EntityNotFoundException;
import gft.cryptoWallet.repositories.CarteiraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;}

    public Carteira salvarCarteira(Carteira carteira){

//        try{
            return carteiraRepository.save(carteira);
//        } catch (Exception e){
//            throw new DataInsertionException("Não conseguimos salvar essa carteira: " + e.getMessage());
//        }
    }

    public Page<Carteira> listarTodasAsCarteiras(Pageable pageable){
        return carteiraRepository.findAll(pageable);
    }

    public Carteira buscarCarteira(Long id, Long clienteId){

        Optional<Carteira> carteira = (clienteId != null)
                ? carteiraRepository.findByClienteId(clienteId)
                : carteiraRepository.findById(id);

        return carteira.orElseThrow(() -> new EntityNotFoundException("Carteira não encontrada"));
    }

    public void excluirCarteira(Long id, Long clienteId){

        Carteira carteira;

        try{
            carteira = buscarCarteira(null, clienteId);
            carteiraRepository.delete(carteira);
        }catch (Exception e){
            carteira = buscarCarteira(id, null);
            carteiraRepository.delete(carteira);
        }
    }
}
