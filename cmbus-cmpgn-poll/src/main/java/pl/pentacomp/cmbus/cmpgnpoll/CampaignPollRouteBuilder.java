package pl.pentacomp.cmbus.cmpgnpoll;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import pl.pentacomp.cmbus.commons.model.CampaignDefinition;

@Component
public class CampaignPollRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("timer:12s")
        .setBody(
            constant(
                "{\"campaignId\":0," +
                " \"step\":2, " +
                "\"cycle\":3," +
                " \"timestamp\": \"2011-07-13T13:55:29.0102\"," +
                "\"smilURI\":\"mms00.smil\"}")
        )
        .to("log:pl.pentacomp.cmbus.cmpgnpoll.preunmarshall?level=DEBUG")
        .unmarshal().json(JsonLibrary.Jackson,CampaignDefinition.class)
        .to("log:pl.pentacomp.cmbus.cmpgnpoll.postunmarshall?level=DEBUG")
        .to("direct:aggregator");

    from("direct:aggregator")
        .to("log:pl.pentacomp.cmbus.cmpgnpoll.aggregator1?level=DEBUG")
        .enrich("direct:smil")
        .to("log:pl.pentacomp.cmbus.cmpgnpoll.aggregator2?level=DEBUG");

    from("direct:smil")
        .to("log:pl.pentacomp.cmbus.cmpgnpoll.smil?level=DEBUG");
  }
}