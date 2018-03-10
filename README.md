spring-jenkins-actuator
=======================

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.itelg.spring/spring-jenkins-actuator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.itelg.spring/spring-jenkins-actuator)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/2a49d5565df7457697072dc5b1cdc5be)](https://www.codacy.com/app/eggers-julian/spring-jenkins-actuator)
[![Coverage Status](https://coveralls.io/repos/julian-eggers/spring-jenkins-actuator/badge.svg?branch=master&service=github)](https://coveralls.io/github/julian-eggers/spring-jenkins-actuator?branch=master)
[![Build Status](https://travis-ci.org/julian-eggers/spring-jenkins-actuator.svg?branch=master)](https://travis-ci.org/julian-eggers/spring-jenkins-actuator)

SpringBoot Jenkins Actuator

#### Maven
```xml
<dependency>
	<groupId>com.itelg.spring</groupId>
	<artifactId>spring-jenkins-actuator</artifactId>
	<version>1.0.0-RELEASE</version>
</dependency>
```

#### Example
```java
@Bean
public HealthIndicator jenkinsHealthIndicator()
{
	return new JenkinsHealthIndicator("http://jenkins.com");
}

@Bean
public HealthIndicator jenkinsHealthIndicator()
{
	return new JenkinsHealthIndicator("http://jenkins.com", "admin", "secretPassword");
}
```

#### Response ([health.json](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-health))
```json
{
	"status" : "UP",
	"jenkins" : 
	{
		"status" : "UP",
		"version" : "2.42"
	}
}
```
