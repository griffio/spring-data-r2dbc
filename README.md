Example project with Kotlin, Spring Boot, R2DBC and PostgreSql

Uses `org.springframework.data.repository.kotlin.CoroutineCrudRepository`

```shell
createdb salesfarce &&
./gradlew build &&
./gradlew bootRun
```

Note:

Nested Entities for use in aggregates (e.g. Order/OrderItem) are not supported with r2dbc - "spring-data-r2dbc/issues/288"

