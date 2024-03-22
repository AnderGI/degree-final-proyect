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
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.coches.cars.domain.car.CarRepository;
import com.example.coches.cars.infraestructure.adapters.cars.InMemoryCarRepository;
import com.example.coches.cars.infraestructure.adapters.cars.MongoDBCarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
@EnableMongoRepositories
@EnableDiscoveryClient
public class CochesmsApplication {
    @Bean
    @Profile("mongodbrepository")
    public CarRepository mongoDBCarRepositoy() {
    	return new MongoDBCarRepository();
    }
  
    @Bean
    @Profile("inmemoryrepository")
    public CarRepository inMemoryCarRepositoy() {
    	return new InMemoryCarRepository();
    }
    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://user:p4ssw0rd@carsdevelop.h6ug8o8.mongodb.net/carsdevelop?retryWrites=true&w=majority&appName=carsdevelop");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
          .applyConnectionString(connectionString)
          .build();
        
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "carsdevelop");
    }
	public static void main(String[] args) {
		SpringApplication.run(CochesmsApplication.class, args);
	}

}
