@echo off

set AWS_ACCESS_KEY_ID=pedroarrudakey
set AWS_SECRET_ACCESS_KEY=pedroarrudakey
set AWS_DEFAULT_REGION=us-east-1

aws --endpoint http://localhost:4566 ssm put-parameter --name "/config/localstack_pedroarruda/helloWorld" --value "Hello World parameter store" --type String