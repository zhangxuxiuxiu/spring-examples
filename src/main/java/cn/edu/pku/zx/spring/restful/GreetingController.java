package cn.edu.pku.zx.spring.restful;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The @RequestMapping annotation ensures that HTTP requests to /greeting are
 * mapped to the greeting() method.
 * 
 * The above example does not specify GET vs. PUT, POST, and so forth, because @RequestMapping
 * maps all HTTP operations by default. Use @RequestMapping(method=GET) to
 * narrow this mapping.
 * 
 * @RequestParam binds the value of the query string parameter name into the
 *               name parameter of the greeting() method. This query string
 *               parameter is not required; if it is absent in the request, the
 *               defaultValue of "World" is used.
 * 
 *               The implementation of the method body creates and returns a new
 *               Greeting object with id and content attributes based on the
 *               next value from the counter, and formats the given name by
 *               using the greeting template.
 * 
 *               A key difference between a traditional MVC controller and the
 *               RESTful web service controller above is the way that the HTTP
 *               response body is created. Rather than relying on a view
 *               technology to perform server-side rendering of the greeting
 *               data to HTML, this RESTful web service controller simply
 *               populates and returns a Greeting object. The object data will
 *               be written directly to the HTTP response as JSON.
 * 
 *               This code uses Spring 4’s new @RestController annotation, which
 *               marks the class as a controller where every method returns a
 *               domain object instead of a view. It’s shorthand for @Controller
 *               and @ResponseBody rolled together.
 * 
 *               The Greeting object must be converted to JSON. Thanks to
 *               Spring’s HTTP message converter support, you don’t need to do
 *               this conversion manually. Because Jackson 2 is on the
 *               classpath, Spring’s MappingJackson2HttpMessageConverter is
 *               automatically chosen to convert the Greeting instance to JSON.
 * 
 */
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template,
				name));
	}
}
