package cn.edu.pku.zx.spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhangxu
 * 
 * 
 * @SpringBootApplication is a convenience annotation that adds all of the
 *                        following:
 * @Configuration tags the class as a source of bean definitions for the
 *                application context.
 * @EnableAutoConfiguration tells Spring Boot to start adding beans based on
 *                          classpath settings, other beans, and various
 *                          property settings. Technically, Spring Boot doesn’t
 *                          have anything to auto-configure when it comes to
 *                          caching but a future version might.
 * @ComponentScan tells Spring to look for other components, configurations, and
 *                services in the the hello package, allowing it to find the
 *                SimpleBookRepository.
 * 
 * 
 * @EnableCaching annotation triggers a post processor that inspects every
 *                Spring bean for the presence of caching annotations on public
 *                methods. If such an annotation is found, a proxy is
 *                automatically created to intercept the method call and handle
 *                the caching behavior accordingly.
 * 
 *                The annotations that are managed by this post processor are
 *                Cacheable, CachePut and CacheEvict. You can refer to the
 *                javadocs and the documentation for more details.
 * 
 *                In its most basic setup, the annotation requires a
 *                CacheManager to serve as a provider for the relevant cache. In
 *                this example you use an implementation that delegates to a
 *                ConcurrentHashMap. The CachingConfigurer interface provides
 *                more advanced configuration options.
 */

@SpringBootApplication
@EnableCaching
public class Application {

	private static final Logger log = LoggerFactory
			.getLogger(Application.class);

	@Component
	static class Runner implements CommandLineRunner {
		@Autowired
		private BookRepository bookRepository;

		// @Override
		public void run(String... args) throws Exception {
			log.info(".... Fetching books");
			log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
			log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
			log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		}
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("books");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
