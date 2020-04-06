# Presto events kafka logging plugin

## Build

```
$ mvn package "-Dpresto.version=0.229 -Dkafka.version=0.11.0.3 -Djackson.version=2.9.8"
```
This will produce uber jar `/target/presto-kafka-emitter-1.0.jar` 

## Deployment

On each presto node:

1. Create directory `kafka-emitter` in `$PRESTO_HOME/plugin` and put assembled jar in
2. Create file `$PRESTO_HOME/etc/event-listener.properties` and fill it as in the example
```
event-listener.name=kafka-emitter
kafka-emitter.kafka-topics.query-completed=presto.query-completed
kafka-emitter.kafka.bootstrap.servers=kafka-host1:6667,kafka-host2:6667,kafka-host3:6667
```

*Restart Presto*