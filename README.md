# http_to_kafka

## Prerequisities
- gradle build tool >= 2.6 http://gradle.org/
- access to a kafka cluster

## Clone, build and start the API
In a work directory do the steps below

```bash
git clone https://github.com/droptableusers/http_to_kafka.git
cd http_to_kafka/
gradle clean build shadowJar
```

The config file comes with sane defaults, but you may need to change it to reflect local configuration.
(e.g. change the address of the kafka server(s) and topic name to be used, ports, etc.)
```
vim src/main/resources/config.yaml
```

Finally, start the API
```bash
java -jar build/libs/http_to_kafka-1.0-all.jar server src/main/resources/config.yaml
```

## A quick test
Start a kafka console consumer in a terminal:
```bash
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic testTopic
```

Send some json to the app in another terminal
```bash
curl -XPOST 'http://localhost:9966/message' -H 'Content-Type: application/json' -d '{"apple": 6}'
``` 
And you should see the POST payload in the console consumer:
```json
{"apple": 6}
```
