package pl.pentacomp.cmbus.dispatcher;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultMessage;
import pl.pentacomp.cmbus.mm7.AddressType;
import pl.pentacomp.cmbus.mm7.MultiAddressType;
import pl.pentacomp.cmbus.mm7.RecipientsType;
import pl.pentacomp.cmbus.mm7.SubmitReq;

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
        .to("cxf:bean:mmscEndpoint")
        .setHeader("soap:mustUnderstand", simple("true", Boolean.class))
        .to("log:pl.pentacomp.cmbus.dispatcher.CxfTest");
  }

  public static class MsgCreatorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

      SubmitReq sr = new SubmitReq();
      RecipientsType rt = new RecipientsType();

      String number = "48600000000";
      AddressType.Number numberType = new AddressType.Number();
      numberType.setValue(number);
      MultiAddressType mt = new MultiAddressType();
      mt.getRFC2822AddressesAndNumbersAndShortCodes().add(numberType);

      JAXBElement<MultiAddressType> mat = new JAXBElement<MultiAddressType>(
          new QName("http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-6-MM7-1-4", "to"),
          MultiAddressType.class, mt);
      rt.getTosAndCcsAndBccs().add(mat);
      sr.setRecipients(rt);
      Message msg = new DefaultMessage();
      msg.setBody(sr);
      final File f = new File("/home/tza/dev/scala/doc/tools/images/scala_logo.png");

      DataHandler dh = new DataHandler(new DataSource() {

        @Override
        public InputStream getInputStream() throws IOException {

          return new BufferedInputStream(new FileInputStream(f));
        }

        @Override
        public OutputStream getOutputStream() throws IOException {

          return new BufferedOutputStream(new FileOutputStream(f));
        }

        @Override
        public String getContentType() {

          return "image/png";
        }

        @Override
        public String getName() {

          return f.getName();
        }
      });
      msg.setAttachments(Collections.singletonMap("scala-logo.png",dh));
      exchange.setOut(msg);
    }
  }
}
