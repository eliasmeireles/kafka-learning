update:
	git submodule update --recursive --init

up-cluster:
	docker-compose -f docker/docker-compose.yml up --build -d

test:
	@./gradlew clean test

build:
	@./gradlew clean build

up-producer:
	@./gradlew producer:bootRun

up-consumer:
	@./gradlew consumer:bootRun

