package pl.pentacomp.cmbus.dispatcher.concurrent;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SedaDispatcherRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("seda:dispatcher?size=32768&concurrentConsumers=8")
        .routeId("routes/seda-dispatcher")
        .process(new SequentialDispatcherProcessor())
        .log("pl.pentacomp.cmbus.dispatcher.Seda?level=DEBUG")
        .to("direct:receiver");
  }
}
