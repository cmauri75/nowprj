SHELL := /bin/zsh

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
	@./gradlew bootrun --args='--spring.profiles.active=dev'

