package io.codementor.gtommee.rest_tutorial;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "io.codementor.gtommee.rest_tutorial" })
@EntityScan(basePackages = { "io.codementor.gtommee.rest_tutorial" })
@ComponentScan(basePackages = { "io.codementor.gtommee.rest_tutorial" })
@EnableMongoRepositories("io.codementor.gtommee.rest_tutorial.repositories")
public class RestTutorialApplication implements AsyncConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(RestTutorialApplication.class, args);
	}
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(2);
		threadPoolTaskExecutor.setMaxPoolSize(100);
		threadPoolTaskExecutor.setThreadNamePrefix("Async-");
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

}
