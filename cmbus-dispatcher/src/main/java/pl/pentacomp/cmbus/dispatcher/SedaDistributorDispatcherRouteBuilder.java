package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SedaDistributorDispatcherRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("direct:seda-distributor")
        .routeId("routes/seda-distributor")
        .setExchangePattern(ExchangePattern.InOnly)
        .log("pl.pentacomp.cmbus.dispatcher.SD?level=DEBUG")
        .to("seda:dispatcher");
  }
}