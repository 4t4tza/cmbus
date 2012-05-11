package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultMessage;
import org.springframework.core.io.ClassPathResource;
import pl.pentacomp.cmbus.mm7.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.*;
import java.util.Collections;

public class CxfRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    from("timer:12s")
        .to("log:pl.pentacomp.cmbus.dispatcher.CxfTest")
        .setExchangePattern(ExchangePattern.InOut)
        .process(new MsgCreatorProcessor())
        .to("log:pl.pentacomp.cmbus.dispatcher.CxfTest")
        .marshal().jaxb(SubmitReq.class.getPackage().getName())
        .to("log:pl.pentacomp.cmbus.dispatcher.CxfTest")
        .setHeader("soap:mustUnderstand", simple("true", Boolean.class))
        .to("cxf:bean:mmscEndpoint")
        .to("log:pl.pentacomp.cmbus.dispatcher.CxfTest")
        .unmarshal().jaxb(SubmitRsp.class.getPackage().getName())
        .to("log:pl.pentacomp.cmbus.dispatcher.CxfTest")
        .bean(new MMSCHandler())
        .to("log:pl.pentacomp.cmbus.dispatcher.CxfTest");
  }

  public static class MsgCreatorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

      SubmitReq sr = new SubmitReq();
      sr.setMM7Version("6.8.0");
      SenderIDType senderId = new SenderIDType();
    senderId.setVASID("cmbus");
      senderId.setVASPID("cmbus-dispatcher");
      AddressType addressType = new AddressType();
      AddressType.Number number = new AddressType.Number();
      number.setValue("48600312312");
      addressType.setNumber(number);
      senderId.setSenderAddress(addressType);
      sr.setSenderIdentification(senderId);


      RecipientsType rt = new RecipientsType();


      String recipNum = "48600000000";
      AddressType.Number numberType = new AddressType.Number();
      numberType.setValue(recipNum);
      MultiAddressType mt = new MultiAddressType();
      mt.getRFC2822AddressesAndNumbersAndShortCodes().add(numberType);

      JAXBElement<MultiAddressType> mat = new JAXBElement<>(
          new QName("http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-6-MM7-1-4", "To"),
          MultiAddressType.class, mt);
      rt.getTosAndCcsAndBccs().add(mat);
      sr.setRecipients(rt);
      Message msg = new DefaultMessage();
      msg.setBody(sr);
      final ClassPathResource cpr = new ClassPathResource("binary/scala_logo1.png");

      DataHandler dh = new DataHandler(new DataSource() {

        @Override
        public InputStream getInputStream() throws IOException {

          return new BufferedInputStream(cpr.getInputStream());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {

          throw new IOException(new UnsupportedOperationException());
        }

        @Override
        public String getContentType() {

          return "image/png";
        }

        @Override
        public String getName() {

          return cpr.getDescription();
        }
      });
      msg.setAttachments(Collections.singletonMap("scala-logo.png", dh));
      exchange.setOut(msg);
    }
  }

  public static class MMSCHandler {

    public String handle(SubmitRsp rsp) {

      return "<X><X><X><X><X><X><X><X>\n" +
             "<X><X><X><X><X><X><X><X>\n" +
             "<X><X><X><X><X><X><X><X>" + rsp.getMessageID() + "\n" +
             "<X><X><X><X><X><X><X><X>\n" +
             "<X><X><X><X><X><X><X><X>";
    }
  }
}
