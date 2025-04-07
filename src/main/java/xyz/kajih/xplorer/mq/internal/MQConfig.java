package xyz.kajih.xplorer.mq.internal;

import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.mq.spring.boot.MQConfigurationProperties;
import com.ibm.msg.client.jakarta.wmq.WMQConstants;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.AcknowledgeMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@Profile("MQ")
public class MQConfig {

    @Bean
    @ConfigurationProperties(prefix = "ibm.mq")
    public MQConfigurationProperties mqConfigProperties() {
        return new MQConfigurationProperties();
    }

    @Bean(name = "ibm_connection_factory")
    public ConnectionFactory connectionFactory(MQConfigurationProperties props) throws JMSException {
        MQConnectionFactory factory = new MQConnectionFactory();
        factory.setQueueManager(props.getQueueManager());
        factory.setChannel(props.getChannel());
        factory.setConnectionNameList(props.getConnName());
        factory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
        factory.setStringProperty(WMQConstants.USERID, props.getUser());
        factory.setStringProperty(WMQConstants.PASSWORD, props.getPassword());
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(@Qualifier("ibm_connection_factory") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("10"); // Process up to 10 messages concurrently
        factory.setSessionAcknowledgeMode(AcknowledgeMode.CLIENT.getMode()); // CLIENT_ACKNOWLEDGE mode
        return factory;
    }
}
