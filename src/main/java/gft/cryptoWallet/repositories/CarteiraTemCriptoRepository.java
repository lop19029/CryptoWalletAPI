package gft.cryptoWallet.repositories;

import gft.cryptoWallet.entities.CarteiraTemCripto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarteiraTemCriptoRepository extends JpaRepository<CarteiraTemCripto, Long> {

    List<CarteiraTemCripto> findByCriptoId(Long criptoId);
}
