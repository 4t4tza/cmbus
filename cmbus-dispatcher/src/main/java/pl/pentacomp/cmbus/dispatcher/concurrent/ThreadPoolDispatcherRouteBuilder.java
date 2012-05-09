package pl.pentacomp.cmbus.dispatcher.concurrent;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ThreadPoolDispatcherRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("direct:dispatcher")
        .threads(4, 8)
        .process(new SequentialDispatcherProcessor())
        .log("pl.pentacomp.cmbus.dispatcher.ThreadPool?level=DEBUG");
  }
}
