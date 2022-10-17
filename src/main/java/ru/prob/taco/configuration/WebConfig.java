package ru.prob.taco.configuration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.prob.taco.data.IngredientRepository;
import ru.prob.taco.data.TacoRepository;
import ru.prob.taco.data.UserRepository;
import ru.prob.taco.kitchen.kafka.KafkaOrderListener;
import ru.prob.taco.kitchen.rabbit.activelistener.RabbitOrderReceiver;
import ru.prob.taco.kitchen.rabbit.passivelistener.RabbitOrderListenerPassive;
import ru.prob.taco.model.Ingredient;
import ru.prob.taco.model.Taco;
import ru.prob.taco.service.kafkamessageservice.KafkaOrderMessagingService;

import java.util.Arrays;
import java.util.Timer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/design");
        registry.addViewController("/login");
    }

    @Bean
    public CommandLineRunner dataLoader(
            IngredientRepository repo,
            UserRepository userRepo,
            PasswordEncoder encoder,
            TacoRepository tacoRepo) {
        return args -> {
            Ingredient flourTortilla = new Ingredient(
                    "FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            Ingredient cornTortilla = new Ingredient(
                    "COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", Ingredient.Type.PROTEIN);
            Ingredient tomatoes = new Ingredient(
                    "TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            Ingredient lettuce = new Ingredient(
                    "LETC", "Lettuce", Ingredient.Type.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", Ingredient.Type.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            Ingredient salsa = new Ingredient(
                    "SLSA", "Salsa", Ingredient.Type.SAUCE);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", Ingredient.Type.SAUCE);
            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            tacoRepo.save(taco1);
            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            tacoRepo.save(taco2);
            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa));
            tacoRepo.save(taco3);
        };
    }

    @Bean
    public Timer getTimer() {
        return new Timer();
    }
 /*
    Artemis:

    @Bean
    public OrderListener getOrderListener() {
        return new OrderListenerArtemis();
    }
 */
/*
//Rabbit

    @Bean
    public RabbitOrderReceiver getRabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
        return new RabbitOrderReceiver(rabbitTemplate);
    }

    @Bean
    public RabbitOrderListenerPassive getRabbitListenerPassive() {
        return new RabbitOrderListenerPassive();
    }
    */

    //Kafka

    @Bean
    public KafkaOrderListener getKafkaOrderListener() {
        return new KafkaOrderListener();
    }

}
