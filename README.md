# Kafka Health Check

> **Note.** _forked from [deviceinsight/kafka-health-check](https://github.com/deviceinsight/kafka-health-check) due to long period of inactivity_

This library provides a kafka health check for spring boot actuator.

## Usage

Add the following dependency to your `pom.xml`

```xml
<dependency>
    <groupId>me.bvn13.kafka.health</groupId>
    <artifactId>kafka-health-check</artifactId>
    <version>1.5.4</version>
</dependency>
```

In the same maven module you can configure the topic, poll timeouts and the reception timeouts
in the `application.yaml`

An example for an `application.yaml` is:

```yaml
kafka:
  health:
    topic: health-checks
    sendReceiveTimeout: 2.5s
    pollTimeout: 200ms
```

The values shown are the defaults.

IMPORTANT: Make sure the configured health check topic exists!

```java
@Bean
@ConfigurationProperties("kafka.health")
public KafkaHealthProperties kafkaHealthProperties() {
    return new KafkaHealthProperties();
}
```

```java
@Bean
public KafkaConsumingHealthIndicator kafkaConsumingHealthIndicator(KafkaHealthProperties kafkaProperties,
        KafkaProperties processingProperties) {
    return new KafkaConsumingHealthIndicator(kafkaHealthProperties, processingProperties.buildConsumerProperties(),
            processingProperties.buildProducerProperties());
}
```

Now if you call the actuator endpoint `actuator/health` you should see the following output:

```json
{
   "status" : "UP",
   "details" : {
      "kafkaConsuming" : {
         "status" : "UP"
      }
   }
}
```

## Configuration


| Property                        | Default         | Description                                                                                                                                                                                                                                     |
|---------------------------------|-----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| kafka.health.enabled            | false           | Enabling kafka health check                                                                                                                                                                                                                     |
| kafka.health.topic              | `health-checks` | Topic to subscribe to                                                                                                                                                                                                                           |
| kafka.health.sendReceiveTimeout | 2.5s            | The maximum time, given as [Duration](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-conversion-duration), to wait for sending and receiving the message.|
| kafka.health.pollTimeout        | 200ms           | The time, given as [Duration](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-conversion-duration), spent fetching the data from the topic                |
| kafka.health.cache.maximumSize  | 200             | Specifies the maximum number of entries the cache may contain.                                                                                                                                                                                  |



## Releasing

Creating a new release involves the following steps:

1. `./mvnw gitflow:release-start gitflow:release-finish`
2. `git push origin master`
3. `git push --tags`
4. `git push origin develop`

In order to deploy the release to Maven Central, you need to create an account at https://issues.sonatype.org and
configure your account in `~/.m2/settings.xml`:

```xml
<settings>
  <servers>
    <server>
      <id>ossrh</id>
      <username>your-jira-id</username>
      <password>your-jira-pwd</password>
    </server>
  </servers>
</settings>
```

The account also needs access to the project on Maven Central. This can be requested by another project member.

Then check out the release you want to deploy (`git checkout x.y.z`) and run `./mvnw deploy -Prelease`.
