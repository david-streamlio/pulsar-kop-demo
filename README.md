# Kafka on Pulsar Demo

This project allows you to quickly create a Pulsar cluster that have the AoP protocol handler preconfigured to run inside a Docker container. It also includes a simple Java code example that interacts with Apache Pulsar using the Kafka binary protocol.

## Prerequisites 

These scripts assume that you have the following development tools available in your $PATH. If this is NOT the case, please install them before attempting to use the provided shell scripts.

- Apache Maven
- A JDK version 11 or greater
- Docker Desktop

## Getting Started
The project includes two bash scripts that automate the demonstration process. Therefore, the first step is to make them executable by changing the permissions using the following command: `chmod a+x *.sh`

- The `setup.sh` script must be run first, to complete the following steps:
  1. Creates the Docker image and tags it as `apachepulsar/pulsar-kop`
  2. Starts a Docker container based on the image that runs a Pulsar cluster in standalone mode with a Kafka-protocol port opened on 9092

    
- The `test.sh` script can then be run to confirm the Kafka-on-Pulsar functionality of Pulsar
  1. Builds an executable JAR file from the projects java code
  2. Runs the executable JAR file to publish and consumer data over the KoP port of 9092

## References

- Details on the KoP connector. https://hub.streamnative.io/protocol-handlers/kop/2.8.1.2
- Configuration options for the Kafka-on-Pulsar protocol handler can be found here: https://github.com/streamnative/kop/blob/v2.8.1.2/docs/configuration.md
