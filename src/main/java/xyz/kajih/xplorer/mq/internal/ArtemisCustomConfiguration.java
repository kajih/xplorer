package xyz.kajih.xplorer.mq.internal;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.remoting.impl.invm.InVMConnectorFactory;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.AcknowledgeMode;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@Profile("ARTEMIS")
public class ArtemisCustomConfiguration {

        @Bean
        public EmbeddedActiveMQ embeddedActiveMQ () throws Exception {
            var config = new ConfigurationImpl()
                    .setPersistenceEnabled(false)
                    .setSecurityEnabled(false)
                    .addAcceptorConfiguration("in-vm", "vm://0");

            EmbeddedActiveMQ embeddedActiveMQ = new EmbeddedActiveMQ();
            embeddedActiveMQ.setConfiguration(config);
            embeddedActiveMQ.start();

            return embeddedActiveMQ;
        }

        @Bean(name = "artemis_connection_factory")
        public ConnectionFactory connectionFactory () throws JMSException {

            InVMConnectorFactory foo = new InVMConnectorFactory();

            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            factory.setBrokerURL("vm://0");
            factory.setUser("admin");
            factory.setPassword("admin");
            return new CachingConnectionFactory(factory);
        }


        @Bean(name = "artemis_jms_container_factory")
        public DefaultJmsListenerContainerFactory jmsListenerContainerFactory
        (DefaultJmsListenerContainerFactoryConfigurer configurer,
                @Qualifier("artemis_connection_factory") ConnectionFactory connectionFactory){

            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

            configurer.configure(factory, connectionFactory);
            factory.setConnectionFactory(connectionFactory);
            factory.setPubSubDomain(false); // false for queues, true for topics
            factory.setConcurrency("3-10");
            factory.setSessionAcknowledgeMode(AcknowledgeMode.CLIENT.getMode()); // CLIENT_ACKNOWLEDGE mode
            return factory;
        }

        @Bean(name = "artemis_jms_template")
        public JmsTemplate jmsTemplate (
                @Qualifier("artemis_connection_factory")
                        ConnectionFactory connectionFactory){
            JmsTemplate template = new JmsTemplate(connectionFactory);
            template.setPubSubDomain(false);
            return template;
        }
    }

