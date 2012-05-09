package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.pentacomp.cmbus.commons.*;

public class CxfRouteBuilderTest extends BaseSpringCMBusTest {

  @EndpointInject(uri = "mock:cxf:bean:mmscEndpoint")
  protected MockEndpoint mmscEPM;

  @Test
  public void testRoute() throws Exception {

    mmscEPM.expectedMinimumMessageCount(2);
    mmscEPM.setMinimumResultWaitTime(500);
    assertMockEndpointsSatisfied();
  }

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {

    RouteBuilder rb = new CxfRouteBuilder();

//    rb.interceptSendToEndpoint("cxf:bean:mmscEndpoint")
//      .skipSendToOriginalEndpoint()
//      .to("mock:cxf:bean:mmscEndpoint");

    return rb;
  }

  @Override
  protected AbstractApplicationContext createApplicationContext() {

    return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
  }
}
