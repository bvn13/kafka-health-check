# KafkaHealthCheck

## Version 1.5.3

* Added SpringBootAutoConfiguration

## Version 1.5.2

* Changed maven group publication: switched into `me.bvn13.kafka.health`

## Version 1.4.0

* Got rid of `subscriptionTimeout` as a redundant timeout

## Version 1.3.0

* Health check timeouts can now be configured in `java.time.Duration` format. The timeouts can still be configured using
  millisecond values (`long`) as well to stay compatible with old configurations.
* Dependency versions are now managed by `spring-boot-dependencies`. [ISSUE-17](https://github.com/deviceinsight/kafka-health-check/issues/17)
* As of now, cache metrics can be exposed. For this purpose, a corresponding MeterRegistry instance must be passed
  when instantiating the Kafka Health Check. [ISSUE-20](https://github.com/deviceinsight/kafka-health-check/issues/20)
* The cache size can now be configured via the property `kafka.health.cache.maximum-size`. The default value for the cache size is 200. [ISSUE-22](https://github.com/deviceinsight/kafka-health-check/issues/22)
* Filtering messages that do not come from the same instance. [ISSUE-24](https://github.com/deviceinsight/kafka-health-check/issues/24)

## Version 1.2.0

* Reduce logging level of health check calls to `TRACE`.

## Version 1.1.0

* Make consumer groups unique by appending a random UUID when no group ID is configured explicitly.
* Refactor health check strategy: Kafka polled continuously.

## Version 1.0.0

* Develop kafka health check
