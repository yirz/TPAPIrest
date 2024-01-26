package comptoirs.dto;

/**
 * Utilisé pour représenter le résultat des requêtes statistiques
 * @see comptoirs.dao.ProduitRepository
 * Cette interface sera auto-implémentée par Spring
 */
public interface UnitesParProduit {
	String getNom();
	Long getUnites();
}
