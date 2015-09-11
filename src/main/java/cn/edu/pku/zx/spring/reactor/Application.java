package cn.edu.pku.zx.spring.reactor;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

/**
 * 
 The Reactor environment is defined with the environment() method. The
 * environment gets fed into the reactor() method to create an asynchronous
 * reactor. In this case, you are using the THREAD_POOL dispatcher.
 * 
 * It also defines the number of events to send at the top with NUMBER_OF_QUOTES
 * and creates a CountDownLatch with the latch() method.
 * 
 * The Application class is tagged with the @Configuration and @ComponentScan
 * annotations. This lets it define the application context while also scanning
 * the hello package for the @Service objects.
 * 
 * The main() method fetches the reactor and the receiver. It then registers the
 * receiver to digest events on the "quotes" selector. With everything
 * registered, it uses the Publisher to send out a series of quote-fetching
 * events.
 * 
 * The CountDownLatch then waits until every thread reports that it’s done
 * before proceeding.
 * 
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

	private static final int NUMBER_OF_QUOTES = 10;

	@Bean
	Environment env() {
		return new Environment();
	}

	@Bean
	Reactor createReactor(Environment env) {
		return Reactors.reactor().env(env).dispatcher(Environment.THREAD_POOL)
				.get();
	}

	@Autowired
	private Reactor reactor;

	@Autowired
	private Receiver receiver;

	@Autowired
	private Publisher publisher;

	@Bean
	public CountDownLatch latch() {
		return new CountDownLatch(NUMBER_OF_QUOTES);
	}

	// @Override
	public void run(String... args) throws Exception {
		//reactor.on($("quotes"), receiver);
		publisher.publishQuotes(NUMBER_OF_QUOTES);
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}

}