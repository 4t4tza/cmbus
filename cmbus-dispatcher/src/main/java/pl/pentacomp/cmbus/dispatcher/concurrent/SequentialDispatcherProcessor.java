package pl.pentacomp.cmbus.dispatcher.concurrent;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequentialDispatcherProcessor implements Processor {

  private static final Logger LOG = LoggerFactory.getLogger(SequentialDispatcherProcessor.class);

  @Override
  public void process(Exchange exchange) throws Exception {

    LOG.trace("SDP -> thread: {}", Thread.currentThread().getName());

    //TODO: MM7 construction

    LOG.debug("SDP -> Exchange: {}", exchange.getIn().getBody());
  }
}
