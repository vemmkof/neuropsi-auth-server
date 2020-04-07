#!/bin/bash
mvn clean && mvn spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=dev
