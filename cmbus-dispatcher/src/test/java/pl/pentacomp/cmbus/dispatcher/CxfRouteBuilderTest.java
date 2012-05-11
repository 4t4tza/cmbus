package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultMessage;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.pentacomp.cmbus.commons.BaseSpringCMBusTest;

import java.util.ArrayList;
import java.util.List;

public class CxfRouteBuilderTest extends BaseSpringCMBusTest {

  @EndpointInject(uri = "mock:cxf:bean:mmscEndpoint")
  protected CmBusMockEP mmscEPM;

  @Test
  public void testRoute() throws Exception {

    String submitReq = getTestResource("SubmitReq.xml");

    //mmscEPM.expectedBodiesReceived(submitReq);
    mmscEPM.whenAnyExchangeReceived(new Processor() {

      @Override
      public void process(Exchange exchange) throws Exception {

        Message msg = new DefaultMessage();
        msg.setBody(getTestResource("SubmitRsp.xml"));
        exchange.setOut(msg);
      }
    });

    assertMockEndpointsSatisfied();
  }

  private static final class CmBusMockEP extends MockEndpoint {

    @Override
    public void expectedBodiesReceived(final List bodies) {

      expectedMessageCount(bodies.size());
      final List <Object> actualBodyValues = new ArrayList<Object>();

      expects(new Runnable() {

        public void run() {

          for (int i = 0; i < bodies.size(); i++) {
            Exchange exchange = getReceivedExchanges().get(i);
            assertTrue("No exchange received for counter: " + i, exchange != null);

            Object expectedBody = bodies.get(i);
            Object actualBody = null;
            if (i < actualBodyValues.size()) {
              actualBody = actualBodyValues.get(i);
            }
            actualBody = extractActualValue(exchange, actualBody, expectedBody);

            assertEquals("Body of message: " + i, expectedBody, actualBody);
          }
        }
      });
    }

    private Object extractActualValue(Exchange exchange, Object actualValue, Object expectedValue) {
      if (actualValue == null) {
        return null;
      }

      if (actualValue instanceof Expression) {
        actualValue = ((Expression)actualValue).evaluate(exchange, expectedValue != null ? expectedValue.getClass() : Object.class);
      } else if (actualValue instanceof Predicate) {
        actualValue = ((Predicate)actualValue).matches(exchange);
      } else if (expectedValue != null) {
        String from = actualValue.getClass().getName();
        String to = expectedValue.getClass().getName();
        actualValue = getCamelContext().getTypeConverter().convertTo(expectedValue.getClass(), actualValue);
        assertTrue("There is no type conversion possible from " + from + " to " + to, actualValue != null);
      }
      return actualValue;
    }
  }

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {

    RouteBuilder rb = new CxfRouteBuilder();

    rb.interceptSendToEndpoint("cxf:bean:mmscEndpoint")
      .skipSendToOriginalEndpoint()
      .to("mock:cxf:bean:mmscEndpoint");

    return rb;
  }

  @Override
  protected AbstractApplicationContext createApplicationContext() {

    return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
  }
}
