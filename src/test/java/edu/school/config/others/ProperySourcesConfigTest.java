package edu.school.config.others;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes=ProperySourcesConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProperySourcesConfigTest {

    @Value("${ActiveMQ.DEFAULT_BROKER_URL}")
    String propertyToBeInjected;
	
	@Test
	public void test() {
		Assert.assertEquals(propertyToBeInjected, "tcp://localhost:61616", "tcp://localhost:61616");
	}
}
