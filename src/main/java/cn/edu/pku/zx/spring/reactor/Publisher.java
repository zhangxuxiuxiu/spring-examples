package cn.edu.pku.zx.spring.reactor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.Reactor;
import reactor.event.Event;

/**
 * 
 The code uses a for loop to publish a fixed number of events. An
 * AtomicInteger is used to fashion a unique number, which gets turned into a
 * Reactor event with Event.wrap(). The event is published to the quotes channel
 * using reactor.notify().
 * 
 */

@Service
public class Publisher {

	@Autowired
	Reactor reactor;

	@Autowired
	CountDownLatch latch;

	public void publishQuotes(int numberOfQuotes) throws InterruptedException {
		long start = System.currentTimeMillis();

		AtomicInteger counter = new AtomicInteger(1);

		for (int i = 0; i < numberOfQuotes; i++) {
			reactor.notify("quotes", Event.wrap(counter.getAndIncrement()));
		}

		latch.await();

		long elapsed = System.currentTimeMillis() - start;

		System.out.println("Elapsed time: " + elapsed + "ms");
		System.out.println("Average time per quote: " + elapsed
				/ numberOfQuotes + "ms");
	}

}
