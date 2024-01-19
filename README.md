# Kakfa Learning Project

## References

- [Protocol Buffer Compiler Installation](https://grpc.io/docs/protoc-installation/)
- [Confluent Developer Spring Boot](https://developer.confluent.io/get-started/spring-boot/#create-project)
- [Quick Start for Confluent Platform](https://docs.confluent.io/platform/current/platform-quickstart.html#ce-docker-quickstart)

## Utils

- [confluentinc/schema-registry](https://github.com/confluentinc/schema-registry)
- [protobuf-gradle-plugin](https://github.com/google/protobuf-gradle-plugin)

## Requirements

- Java 19
- proto
- Docker

## Before Startup

> Update submodules
> ```shell
>  make update
> ```

> Project build
> ```shell
>  make build
> ```

## Startup

> Kafaka Cluster
> ```shell
>  make up-cluster
> ```

> Producer
> ```shell
>  make up-producer
> ```

> Consumer
> ```shell
>  make up-consumer
> ```
