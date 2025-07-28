package com.pgs.ai.spring.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes/chef")
public class RecipeChefController {

	private static final String MESSAGE = "message";
	private static final String SUGGEST_A_RECIPE_FOR_DINNER = "Suggest a recipe for dinner";
	private final ChatClient chatClient;
    private static final Logger logger = LoggerFactory.getLogger(RecipeChefController.class);
    private final List<Message> conversation;

	public RecipeChefController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
		this.conversation = new ArrayList<>();
		final String SYSTEM_MSG_STRING = 
				  "You are a helpful assistant who only suggests sea food recipes. "
				  + "If someone asks about any other type of food, "
				  + "respond that you only provide sea food recipes and cannot help with other topics.";
		final SystemMessage systemMessage = new SystemMessage(SYSTEM_MSG_STRING);
		this.conversation.add(systemMessage);
	}
	
	/**
	 * Endpoint REST que sugiere una receta a partir de un mensaje de entrada del usuario.
	 * La conversación se mantiene en memoria para proporcionar contexto en cada interacción.
	 *
	 * @param message Mensaje del usuario solicitando una receta (por defecto: "Suggest a recipe for dinner").
	 * @return Respuesta generada por el modelo de IA.
	 */
	@GetMapping("/suggest-recipe")
	public String suggestRecipe(
	    @RequestParam(
	            name = MESSAGE,
	            defaultValue = SUGGEST_A_RECIPE_FOR_DINNER
	    ) String message
	) {
	    logger.info("Mensaje recibido del usuario: '{}'", message);

	    final Message userMessage = new UserMessage(message);
	    this.conversation.add(userMessage);
	    logger.debug("Mensaje de usuario añadido a la conversación: {}", userMessage);

	    String modelResponse = chatClient.prompt()
	        .messages(conversation)
	        .call()
	        .content();

	    logger.info("Respuesta generada por el modelo: '{}'", modelResponse);

	    final Message assistantMessage = new AssistantMessage(modelResponse);
	    this.conversation.add(assistantMessage);
	    logger.debug("Mensaje de asistente añadido a la conversación: {}", assistantMessage);

	    return modelResponse;
	}
}
