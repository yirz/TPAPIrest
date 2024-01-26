package comptoirs.rest;

import comptoirs.dao.CommandeRepository;
import comptoirs.dto.CommandeProjection;
import comptoirs.entity.Commande;
import comptoirs.entity.Ligne;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import comptoirs.service.CommandeService;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController // Cette classe est un contrôleur REST
@RequestMapping(path = "/services/commande/other") // chemin d'accès
public class OtherCommandeController {

	private final CommandeService commandeService;

	private final CommandeRepository commandeDao;

    private final RepositoryEntityLinks entityLinks;

    private final DefaultFormattingConversionService conversionService;

	// @Autowired
	public OtherCommandeController(CommandeService commandeService, CommandeRepository commandeDao, RepositoryEntityLinks entityLinks, DefaultFormattingConversionService conversionService) {
		this.commandeService = commandeService;
		this.commandeDao = commandeDao;
        this.entityLinks = entityLinks;
        this.conversionService = conversionService;
    }

	@GetMapping("projection/{commandeNum}")
	public CommandeProjection projection(@PathVariable Integer commandeNum) {
		return commandeDao.findProjectionByNumero(commandeNum);
	}

    @GetMapping("deserialize")
    public ResponseEntity<EntityModel<Commande>> deserialize(@RequestParam String commandeURI) {
        final URI uri = URI.create(commandeURI);
        Commande commande = conversionService.convert(uri, Commande.class);
        return commande != null ? ResponseEntity.ok(EntityModel.of(commande)) : ResponseEntity.notFound().build();
    }

	@GetMapping("ajouterPourClient/{clientCode}")
	public EntityModel<Commande> ajouterEntity(@PathVariable @NonNull String clientCode) {
        var commande = commandeService.creerCommande(clientCode);
        Link selfLink = entityLinks.linkToItemResource(Commande.class, commande.getNumero()).withSelfRel();
		return EntityModel.of(commande, selfLink);
    }

	@GetMapping("ajouterRedirect")
	public RedirectView ajouterLigneRedirect(@RequestParam int commandeNum, @RequestParam int produitRef, @RequestParam int quantite) {
		var ligne = commandeService.ajouterLigne(commandeNum, produitRef, quantite);
        Link selfLink = entityLinks.linkToItemResource(Ligne.class, ligne.getId());
		return new RedirectView(selfLink.getHref());
	}

}
