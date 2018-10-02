# londyn
Fun project of card game mostly known as 'shithead'


## kafka
Commands used to run kafka zookeeper, one broker and to create topic if it's not created yet.

cd C:\kafka_2.11-1.1.0
start bin\windows\zookeeper-server-start.bat config\zookeeper.properties
start bin\windows\kafka-server-start.bat config\serverTestMessage1.properties
call start bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-topic

This one is optional, just to look into topics messages as console consumer:
start bin\windows\kafka-console-consumer.bat --topic test-topic --zookeeper localhost:2181
