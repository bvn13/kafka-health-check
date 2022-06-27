package me.bvn13.kafka.health;

import java.time.Duration;

public class KafkaHealthProperties {

	private String topic = "health-checks";
	private Duration sendReceiveTimeout = Duration.ofMillis(2500);
	private Duration pollTimeout = Duration.ofMillis(200);
	private KafkaHealthCheckCacheProperties cache = new KafkaHealthCheckCacheProperties();

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Duration getSendReceiveTimeout() {
		return sendReceiveTimeout;
	}

	public void setSendReceiveTimeout(Duration sendReceiveTimeout) {
		this.sendReceiveTimeout = sendReceiveTimeout;
	}

	@Deprecated
	public void setSendReceiveTimeoutMs(long sendReceiveTimeoutMs) {
		setSendReceiveTimeout(Duration.ofMillis(sendReceiveTimeoutMs));
	}

	public Duration getPollTimeout() {
		return pollTimeout;
	}

	public void setPollTimeout(Duration pollTimeout) {
		this.pollTimeout = pollTimeout;
	}

	@Deprecated
	public void setPollTimeoutMs(long pollTimeoutMs) {
		setPollTimeout(Duration.ofMillis(pollTimeoutMs));
	}

	public KafkaHealthCheckCacheProperties getCache() {
		return cache;
	}

	public void setCache(KafkaHealthCheckCacheProperties cache) {
		this.cache = cache;
	}

	@Override
	public String toString() {
		return "KafkaHealthProperties{" + "topic='" + topic + '\'' + ", sendReceiveTimeout=" + sendReceiveTimeout +
				", pollTimeout=" + pollTimeout + ", cacheProperties=" +
				cache + '}';
	}
}
