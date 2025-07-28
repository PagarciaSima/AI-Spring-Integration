package com.pgs.ai.spring.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes/sea-food")
public class SeaFoodRecipeController {
	
	private static final String SYSTEM_PROMPT_SEA_FOOD = 
			  "You are a helpful assistant who only suggests sea food recipes. If someone asks about any other type of food, respond that you only provide sea food recipes and cannot help with other topics.";
	private final ChatClient chatClient;
	private static final String MESSAGE = "message";
	private static final String SUGGEST_A_RECIPE_FOR_DINNER = "Suggest a recipe for dinner";
    private static final Logger logger = LoggerFactory.getLogger(SeaFoodRecipeController.class);
    
	public SeaFoodRecipeController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}
	
	@GetMapping("/suggest-recipe")
	public String suggestRecipe(
		@RequestParam(
				name = MESSAGE,
				defaultValue = SUGGEST_A_RECIPE_FOR_DINNER
		) 		String message
	) {
        logger.info("Recibida solicitud de sugerencia de receta con mensaje: '{}'", message);
        
        final String systemMessage = SYSTEM_PROMPT_SEA_FOOD;
        
		String response = chatClient.prompt()
				.system(c -> c.text(systemMessage))
			    .user("Quiero una receta italiana") // lo que pidió el usuario
			    .call()                             // llama al modelo
			    .content();                         // devuelve solo la respuesta textual
		
        logger.info("Respuesta generada por el modelo: '{}'", response);

        return response;
	}
    
}
