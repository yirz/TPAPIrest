package comptoirs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comptoirs.dto.CommandeProjection;
import comptoirs.entity.Commande;

// This will be AUTO IMPLEMENTED by Spring into a Bean called CommandeRepository

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    /**
     * Trouve la liste des commandes à partir du nom de la societe du client.
     * Spring trouve tout seul la requête SQL !
     *
     * @param societe le nom de la société du client
     * @return la liste des commandes passées par ce client
     * @see https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
     * @see https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject
     */
    List<Commande> findByClientSociete(String societe);

    @Query("select c from Commande c where c.numero = :numero")
    CommandeProjection findProjectionByNumero(Integer numero);

    /**
     * Trouve la liste des commandes en cours pour un client donné
     * @param codeClient la clé du client
     * @param codeClient
     * @return la liste des commandes en cours pour ce client
     */
    @Query("""
        select c from Commande c where
            c.envoyeele is null and
            c.client.code = :codeClient
            order by c.numero desc
        """)
    List<Commande> commandesEnCoursPour(String codeClient);

}
