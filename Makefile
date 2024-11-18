SHELL := /bin/zsh
SERVER_SERVLET_CONTEXT_PATH="/nowprj-be"

init: ## starts docker-compose for app and tests
	@./docker compose up -d

test: ## Execute all test
	@./gradlew test

pitest: ## Run pi tests
	@./gradlew pitest

compile: ##compiles application
	@./gradlew spotlessApply
	@./gradlew build -x test

startApp: #start the app
	@export SERVER_SERVLET_CONTEXT_PATH=${SERVER_SERVLET_CONTEXT_PATH} && ./gradlew bootrun --args='--spring.profiles.active=dev'

