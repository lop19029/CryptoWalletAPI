package gft.cryptoWallet.repositories;

import gft.cryptoWallet.entities.Carteira;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    Optional<Carteira> findByClienteId(Long clienteId);

    Page<Carteira> findAll(Pageable pageable);
}
