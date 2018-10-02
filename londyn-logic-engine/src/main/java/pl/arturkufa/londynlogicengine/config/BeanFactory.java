package pl.arturkufa.londynlogicengine.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.arturkufa.londyncommon.kafka.KafkaProducer;
import pl.arturkufa.londyncommon.kafka.model.TestMessage;

@Configuration
public class BeanFactory {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public KafkaProducer<TestMessage> getTestKafkaProducer(){
        return new KafkaProducer<TestMessage>("test-topic");
    }
}
