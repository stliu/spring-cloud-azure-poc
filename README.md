[![Java CI with Maven](https://github.com/stliu/spring-cloud-azure/actions/workflows/maven.yml/badge.svg)](https://github.com/stliu/spring-cloud-azure/actions/workflows/maven.yml)
# spring-cloud-azure


```mermaid
graph TD
	spring-cloud-azure-service["spring-cloud-azure-{service}"]
	spring-cloud-azure-service-starter["spring-cloud-azure-{service}-starter"]
	azure-service["azure-{service}"]
	spring-cloud-azure-core --> azure-core
	spring-cloud-azure-core --> azure-core-http-netty
	spring-cloud-azure-core --> azure-identity
	spring-cloud-azure-core --> spring-core
	spring-cloud-azure-core --> spring-context
	spring-cloud-azure-messaging --> spring-cloud-azure-core
	spring-cloud-azure-messaging --> azure-core-amqp
	spring-cloud-azure-autoconfigure --> spring-cloud-azure-core
	spring-cloud-azure-starter --> spring-cloud-azure-autoconfigure
	spring-cloud-azure-service-starter --> spring-cloud-azure-starter
	spring-cloud-azure-service-starter --> spring-cloud-azure-service
	spring-cloud-azure-service --> spring-cloud-azure-core
	spring-cloud-azure-service --> azure-service
	spring-cloud-azure-starter --> spring-cloud-azure-core
	spring-cloud-azure-starter --> spring-boot-starter
	azure-service --> azure-core
	azure-core-amqp --> azure-core
	classDef green fill:#9f6,stroke:#333,stroke-width:2px;
	classDef orange fill:#f96,stroke:#333,stroke-width:2px;
	

	class spring-core,spring-context,spring-boot-starter green
    class azure-core,azure-core-http-netty,azure-identity,azure-core-amqp,azure-service orange

```


## L1 support features

* auto configuration sdk clients


## L2 support features

* spring data
* spring security
* spring integration
* spring cloud stream


## Service Builder Design

There are two kinds of sdk client libraries from transport perspective, and they share some common infrastructure from the core, for example like

* azure environment configuration
* proxy config

```mermaid
graph TD

core --> http
core --> amqp

```

Spring design
```mermaid
graph TD
HttpBuilderFactory --> BuilderFactory
AMQPBuilderFactory --> BuilderFactory
```