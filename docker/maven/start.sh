#!/bin/bash

if [[ ! -d "/project" ]]; then
    echo "failed to find folder: /project"
    exit 1
fi
cd /project

./mvnw clean package
if [[ $? -ne 0 ]]; then
    echo "maven build failed"
    exit 1
fi

./mvnw -Dtest=Kanboard**IntegrationTest test
if [[ $? -ne 0 ]]; then
    echo "integration test failed"
    exit 1
fi

exit 0
