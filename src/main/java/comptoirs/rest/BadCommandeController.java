package comptoirs.rest;

import comptoirs.entity.Commande;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import comptoirs.service.CommandeService;

@RestController // Cette classe est un contrôleur REST
@RequestMapping("/services/commandes/bad") // chemin d'accès
public class BadCommandeController {
	private final CommandeService commandeService;
	// @Autowired
	public BadCommandeController(CommandeService commandeService) {
		this.commandeService = commandeService;
	}
	// Le chemin d'accès sera http://.../comptoirs/bad/ajouterPour/CODE_DU_CLIENT
	@GetMapping("ajouterPour/{clientCode}")
	// PAS BON ! on renvoie une entité JPA !
	public Commande ajouter(@PathVariable @NonNull String clientCode) {
        return commandeService.creerCommande(clientCode);
	}
}
