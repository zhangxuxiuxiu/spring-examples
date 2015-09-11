package cn.edu.pku.zx.spring.asynchronous;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 
 The main() method uses Spring Boot’s SpringApplication.run() method to launch
 * an application. Did you notice that there wasn’t a single line of XML? No
 * web.xml file either. This web application is 100% pure Java and you didn’t
 * have to deal with configuring any plumbing or infrastructure.
 * 
 * The @EnableAsync annotation switches on Spring’s ability to run @Async
 * methods in a background thread pool.
 * 
 */
@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

	@Autowired
	FacebookLookupService facebookLookupService;

	// @Override
	public void run(String... args) throws Exception {
		// Start the clock
		long start = System.currentTimeMillis();

		// Kick of multiple, asynchronous lookups
		Future<Page> page1 = facebookLookupService.findPage("PivotalSoftware");
		Future<Page> page2 = facebookLookupService.findPage("CloudFoundry");
		Future<Page> page3 = facebookLookupService.findPage("SpringFramework");

		// Wait until they are all done
		while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
			Thread.sleep(10); // 10-millisecond pause between each check
		}

		// Print results, including elapsed time
		System.out.println("Elapsed time: "
				+ (System.currentTimeMillis() - start));
		System.out.println(page1.get());
		System.out.println(page2.get());
		System.out.println(page3.get());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
