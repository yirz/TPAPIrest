package comptoirs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


import comptoirs.dao.ProduitRepository;
import comptoirs.dto.UnitesParProduit;

@RestController
@RequestMapping(path = "/services/stats")
public class StatisticsRestController {
	@Autowired
	private ProduitRepository dao;

	/**
	 * Unites vendues pour chaque produit d'une catégorie donnée.
	 *
	 * @param code le code de la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit en format JSON
	 */
	@GetMapping(path = "unitesVenduesPourCategorie/{code}",
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<UnitesParProduit> unitesVenduesPourCategorie(@PathVariable final Integer code) {
		return dao.produitsVendusPour(code);
	}

	/**
	 * Unites vendues pour chaque produit d'une catégorie donnée. Pas d'utilisation
	 * de DTO, renvoie simplement une liste de tableaux de valeurs
	 *
	 * @param code le code de la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit en format JSON
	 */
	@GetMapping(path = "unitesVenduesPourCategorieV2/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Object> unitesVenduesPourCategorieV2(@PathVariable final Integer code) {
		return dao.produitsVendusPourV2(code);
	}

	@PostMapping(path = "unitesVenduesPourCategorie/{code}",
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<UnitesParProduit> unitesVenduesPourCategorieV3(@PathVariable final Integer code) {
		return dao.produitsVendusPour(code);
	}

}
