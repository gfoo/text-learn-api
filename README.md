# text-learn-api

[![Build Status](https://travis-ci.org/gfoo/text-learn-api.svg?branch=master)](https://travis-ci.org/gfoo/text-learn-api)


REST Text learning service API


curl -v -X POST -H "Content-Type: application/json" -d '{"text":"value1", "topic":"value2", "method":"value2"}' http://localhost:9000/learn 

Check logs of `text-learn-store` to see the form sent.