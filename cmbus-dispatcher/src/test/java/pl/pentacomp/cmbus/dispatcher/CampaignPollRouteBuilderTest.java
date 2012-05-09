package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import pl.pentacomp.cmbus.commons.*;

public class CampaignPollRouteBuilderTest extends BaseCMBusTest {

  @EndpointInject(uri = "mock:testlocal")
  protected MockEndpoint testlocalEndpoint;
//
//  @Produce(uri = "")
//  protected ProducerTemplate template;

  @Test
  public void testPollWithPendingCampaign() throws Exception {

    testlocalEndpoint.expectedMinimumMessageCount(2);
    testlocalEndpoint.setMinimumResultWaitTime(500);
    assertMockEndpointsSatisfied();
  }

  @Override
  protected RouteBuilder createRouteBuilder() throws Exception {

    RouteBuilder rb = new CampaignPollRouteBuilder();
    rb.interceptSendToEndpoint("direct:aggregator")
      .skipSendToOriginalEndpoint()
      .to("mock:testlocal");

    return rb;
  }
}
