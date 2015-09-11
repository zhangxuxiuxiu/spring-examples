package cn.edu.pku.zx.spring.reactor;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import reactor.event.Event;
import reactor.function.Consumer;

/**
 * The Receiver implements the Consumer interface by implementing the accept()
 * method. It is geared to receive Event<Integer>.
 * 
 * For this example, every time the Receiver receives an integer, it fetches
 * another Spring Boot quote using Spring’s RestTemplate. Then it signals its
 * completion to the CountDownLatch to coordinate when all events have been
 * processed.
 * 
 * Receiver has the @Service annotation so it will be automatically registered
 * with the application context.
 * 
 * @author zhangxu
 * 
 */

@Service
class Receiver implements Consumer<Event<Integer>> {

	@Autowired
	CountDownLatch latch;

	RestTemplate restTemplate = new RestTemplate();

	public void accept(Event<Integer> ev) {
		QuoteResource quoteResource = restTemplate.getForObject(
				"http://gturnquist-quoters.cfapps.io/api/random",
				QuoteResource.class);
		System.out.println("Quote " + ev.getData() + ": "
				+ quoteResource.getValue().getQuote());
		latch.countDown();
	}

}
