package comptoirs.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Le "Hello World" des contrôleurs REST
 */
@RestController
@RequestMapping("/services") // chemin d'accès

public class HelloWorldController {
    // Le chemin d'accès est http://ADRESSE_DU_BACKEND/services/sayHello
    // Le verbe HTTP est GET
    @GetMapping("/sayHello")
    public String helloWorld() {
        // Le résultat est renvoyé dans le corps de la réponse HTTP
        return "Hello World";
    }
}
