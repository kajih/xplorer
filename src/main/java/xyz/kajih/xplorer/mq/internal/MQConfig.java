package xyz.kajih.xplorer.mq.internal;

import jakarta.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.jms.AcknowledgeMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@Profile("!dev")
public class MQConfig {
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("10"); // Process up to 10 messages concurrently
        factory.setSessionAcknowledgeMode(AcknowledgeMode.CLIENT.getMode()); // CLIENT_ACKNOWLEDGE mode
        return factory;
    }
}
