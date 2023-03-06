FROM apachepulsar/pulsar:2.9.3

ARG KOP_PROTOCOL_PLUGIN=https://github.com/streamnative/kop/releases/download/v2.9.3.14/pulsar-protocol-handler-kafka-2.9.3.14.nar

RUN  apt-get update && \
     apt-get install -y wget && \
     wget ${KOP_PROTOCOL_PLUGIN} -P /pulsar/protocols

RUN echo "messagingProtocols=kafka" >> /pulsar/conf/standalone.conf  && \
    echo "protocolHandlerDirectory=./protocols" >> /pulsar/conf/standalone.conf && \
    echo "allowAutoTopicCreationType=partitioned" >> /pulsar/conf/standalone.conf && \
    echo "kafkaListeners=PLAINTEXT://127.0.0.1:9092" >> /pulsar/conf/standalone.conf && \
#    echo "kafkaAdvertisedListeners=PLAINTEXT://127.0.0.1:9092" >> /pulsar/conf/standalone.conf && \
    echo "brokerEntryMetadataInterceptors=org.apache.pulsar.common.intercept.AppendIndexMetadataInterceptor" >> /pulsar/conf/standalone.conf && \
    echo "brokerDeleteInactiveTopicsEnabled=false" >> /pulsar/conf/standalone.conf