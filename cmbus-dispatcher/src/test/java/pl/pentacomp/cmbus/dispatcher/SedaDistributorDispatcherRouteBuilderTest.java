package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SedaDistributorDispatcherRouteBuilderTest extends CamelSpringTestSupport {

  @Produce(uri = "direct:seda-distributor")
  private ProducerTemplate distributorPEP;

  @EndpointInject(uri = "mock:direct:receiver")
  private MockEndpoint receiverMEP;

  @Override
  protected AbstractApplicationContext createApplicationContext() {

    return new ClassPathXmlApplicationContext("classpath:META-INF/spring/camel-context.xml");
  }

  @Test
  public void testAdvised() throws Exception {
    // advice the first route using the inlined route builder
    context.getRouteDefinition("routes/seda-dispatcher").adviceWith(context, new RouteBuilder() {

      @Override
      public void configure() throws Exception {
        // intercept sending to mock:foo and do something else
        interceptSendToEndpoint("direct:receiver")
            .skipSendToOriginalEndpoint()
            .to("mock:direct:receiver");
      }
    });

    for (int i = 0; i < 32000; i++)
      distributorPEP.sendBody("asa");

    receiverMEP.expectedMinimumMessageCount(1000);
    receiverMEP.setMinimumResultWaitTime(500000);
    receiverMEP.setResultWaitTime(1000000);
    receiverMEP.assertIsSatisfied();
  }
}
