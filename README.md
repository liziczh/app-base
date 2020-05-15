# base-parent
#### Module

- base-common：BaseBean
- base-jackson：JacksonUtils
- base-fastjson：FastJsonUtils
- base-swagger：Swagger2Config
- base-redis：Redis
- base-kafka：Kafka
- base-aop：AOP
- base-mybatis：DAO
- base-service：Service
- base-mvc：Web

##### base-common

基类定义，通用常量，自定义异常，工具类

```xml
<!--lombok-->
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
</dependency>
<!--utils-->
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-lang3</artifactId>
</dependency>
<!--log-->
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
</dependency>
```

##### base-jackson

```xml
<!-- jackson -->
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-core</artifactId>
</dependency>
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-annotations</artifactId>
</dependency>
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
</dependency>
<!--log-->
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
</dependency>
```

##### base-fastjson

```xml
<!--fastjson-->
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>fastjson</artifactId>
</dependency>
<!--log-->
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
</dependency>
```

##### base-swagger

```xml
<!--swagger-->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
</dependency>
```

##### base-redis

```xml
<!--redis-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

##### base-kafka

```xml
<!--spring-kafka-->
<dependency>
	<groupId>org.springframework.kafka</groupId>
	<artifactId>spring-kafka</artifactId>
</dependency>
<!--utils-->
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-lang3</artifactId>
</dependency>
<!--log-->
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
</dependency>
```

##### base-aop

```xml
<!--aop-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<!--base-jackson-->
<dependency>
	<groupId>com.liziczh</groupId>
	<artifactId>base-jackson</artifactId>
</dependency>
<!--log-->
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
</dependency>
```

##### base-mybatis

```xml
<!--mysql-->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>
<!-- database connection pool -->
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid-spring-boot-starter</artifactId>
</dependency>
<!-- jdbc -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<!-- mybatis -->
<dependency>
	<groupId>org.mybatis.spring.boot</groupId>
	<artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
<!-- redis-->
<dependency>
	<groupId>com.liziczh</groupId>
	<artifactId>base-redis</artifactId>
</dependency>
```

##### base-service

```xml
<!--aop-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<!--base-jackson-->
<dependency>
	<groupId>com.liziczh</groupId>
	<artifactId>base-jackson</artifactId>
</dependency>
<!--log-->
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
</dependency>
```

##### base-mvc

```xml
<!--web-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!--aop-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<!--base-jackson-->
<dependency>
	<groupId>com.liziczh</groupId>
	<artifactId>base-jackson</artifactId>
</dependency>
<!--base-common-->
<dependency>
	<groupId>com.liziczh</groupId>
	<artifactId>base-common</artifactId>
</dependency>
<!--log-->
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
</dependency>
```