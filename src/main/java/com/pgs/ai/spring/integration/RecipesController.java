package com.pgs.ai.spring.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
	
	private static final String MESSAGE = "message";
	private static final String SUGGEST_A_RECIPE_FOR_DINNER = "Suggest a recipe for dinner";
	private final ChatClient chatClient;
    private static final Logger logger = LoggerFactory.getLogger(RecipesController.class);

	public RecipesController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}
	
    /**
     * Endpoint que sugiere una receta utilizando un modelo de lenguaje (IA) como OpenAI.
     *
     * @param message Mensaje o solicitud enviada por el usuario. Si no se especifica, se usará una sugerencia por defecto.
     * @return Una sugerencia de receta generada por el modelo de IA.
     */
	@GetMapping("/suggest-recipe")
	public String suggestRecipe(
		@RequestParam(
				name = MESSAGE,
				defaultValue = SUGGEST_A_RECIPE_FOR_DINNER
		) 		String message
	) {
        logger.info("Recibida solicitud de sugerencia de receta con mensaje: '{}'", message);

		String response = chatClient.prompt()
	    .user("Quiero una receta italiana") // lo que pidió el usuario
	    .call()                             // llama al modelo
	    .content();                         // devuelve solo la respuesta textual
		
        logger.info("Respuesta generada por el modelo: '{}'", response);

        return response;
	}

}
