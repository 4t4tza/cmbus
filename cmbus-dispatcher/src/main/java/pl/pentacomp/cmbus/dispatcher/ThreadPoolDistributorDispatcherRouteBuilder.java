package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ThreadPoolDistributorDispatcherRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("direct:thread-pool-distributor")
        .setExchangePattern(ExchangePattern.InOnly)
        .log("pl.pentacomp.cmbus.dispatcher.TPD?level=DEBUG")
        .to("direct:dispatcher");
  }
}
