package comptoirs.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comptoirs.entity.Produit;
import comptoirs.dto.UnitesParProduit;

// Cette interface sera auto-implémentée par Spring

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit,
	 *		sous la forme d'une liste de DTO UnitesParProduit
	 */
	@Query("SELECT p.nom as nom, SUM(li.quantite) AS unites "
		+ "FROM Categorie c "
		+ "JOIN c.produits p "
		+ "JOIN p.lignes li "
		+ "WHERE c.code = :codeCategorie "
		+ "GROUP BY p.nom ")
	public List<UnitesParProduit> produitsVendusPour(Integer codeCategorie);

	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
	 * pas d'utilisation de DTO
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit,
	 *	   sous la forme d'une liste de tableaux de valeurs non typées
	 */
	@Query("SELECT p.nom, SUM(li.quantite) "
		+ "FROM Categorie c "
		+ "JOIN c.produits p "
		+ "JOIN p.lignes li "
		+ "WHERE c.code = :codeCategorie "
		+ "GROUP BY p.nom ")
	public List<Object> produitsVendusPourV2(Integer codeCategorie);

    List<Produit> findByIndisponibleFalseAndUnitesEnStockGreaterThan(int stock);

}
