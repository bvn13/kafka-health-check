package me.bvn13.kafka.health;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class KafkaHealthPropertiesTest {

    // @formatter:off
	private static final ConfigurationPropertySource DURATION_PROPERTY_SOURCE = new MapConfigurationPropertySource(ImmutableMap.of(
			"kafka.health.topic", "custom-topic",
			"kafka.health.send-receive-timeout", "1m",
			"kafka.health.poll-timeout", "2s",
			"kafka.health.cache.maximum-size", "42"
	));
	// @formatter:on

    @SuppressWarnings("unused")
    @Test
    public void test_that_properties_bind_to_KafkaHealthProperties() {

        KafkaHealthProperties kafkaHealthProperties =
                new Binder(DURATION_PROPERTY_SOURCE).bind("kafka.health", KafkaHealthProperties.class).get();

        assertThat(kafkaHealthProperties.getTopic()).isEqualTo("custom-topic");
        assertThat(kafkaHealthProperties.getSendReceiveTimeout()).isEqualTo(Duration.ofMinutes(1));
        assertThat(kafkaHealthProperties.getPollTimeout()).isEqualTo(Duration.ofSeconds(2));
        assertThat(kafkaHealthProperties.getCache().getMaximumSize()).isEqualTo(42);
    }
}
