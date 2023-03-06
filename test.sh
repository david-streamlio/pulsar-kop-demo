#!/bin/bash

################################################
# Build the Executable Jar file
################################################
mvn clean package

################################################
# Run the test program
################################################
java -jar ./target/KoP-1.0-jar-with-dependencies.jar