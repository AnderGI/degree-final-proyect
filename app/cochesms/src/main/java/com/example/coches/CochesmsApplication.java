package com.example.coches;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.domain.messagereceiver.MessageReceiver;
import com.example.coches.cars.infraestructure.adapters.cars.InMemoryCarRepository;
import com.example.coches.cars.infraestructure.adapters.messagereceivers.RabbitMQMessageReceiver;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class CochesmsApplication {
	static final String topicExchangeName = "spring-boot-exchange";
	static final String queueName = "car_scrapping"; // Cambiar el nombre de la cola

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, @Autowired MessageReceiver receiver) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(new MessageListenerAdapter(receiver, "receiveMessage"));
		return container;
	}

	
    @Bean
    @Primary
    public CarRepository carRepository() {
        // Aquí puedes configurar manualmente la implementación del repositorio que deseas utilizar
        // Por ejemplo, podrías devolver una instancia de InMemoryCarRepository o DatabaseCarRepository
        return new InMemoryCarRepository(); // Cambiar esto según la implementación que desees utilizar
    }

    
    @Bean
    @Primary
    public MessageReceiver messageReceiver(@Autowired ObjectMapper objectMapper) {
    	return new RabbitMQMessageReceiver(objectMapper);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(CochesmsApplication.class, args);
	}

}
