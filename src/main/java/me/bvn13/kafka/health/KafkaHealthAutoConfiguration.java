package me.bvn13.kafka.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(AbstractHealthIndicator.class)
@ConditionalOnProperty(name = "kafka.health.enabled", havingValue = "true")
public class KafkaHealthAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(KafkaHealthProperties.class)
	@ConfigurationProperties("kafka.health")
	public KafkaHealthProperties kafkaHealthProperties() {
		return new KafkaHealthProperties();
	}

	@Bean
	@ConditionalOnMissingBean(KafkaConsumingHealthIndicator.class)
	public KafkaConsumingHealthIndicator kafkaConsumingHealthIndicator(KafkaHealthProperties kafkaHealthProperties,
			KafkaProperties kafkaProperties) {
		return new KafkaConsumingHealthIndicator(kafkaHealthProperties, kafkaProperties.buildConsumerProperties(null),
				kafkaProperties.buildProducerProperties(null));
	}

}
