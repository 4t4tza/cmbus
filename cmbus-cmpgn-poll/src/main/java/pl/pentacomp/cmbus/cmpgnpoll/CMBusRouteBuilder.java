package pl.pentacomp.cmbus.cmpgnpoll;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

public class CMBusRouteBuilder extends RouteBuilder {

    public static void main(String... args) throws Exception {
        Main.main(args);
    }

    public void configure() {

        from("file:src/data?noop=true")
            .to("log:pl.pentacomp.cmbus.cmpgnpoll?level=INFO")
            .choice()
                .when(xpath("/person/city = 'London'"))
                  .to("file:target/messages/uk")
                .otherwise()
                  .to("file:target/messages/others");
    }
}
