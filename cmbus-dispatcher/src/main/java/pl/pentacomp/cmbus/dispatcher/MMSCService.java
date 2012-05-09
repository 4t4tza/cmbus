package pl.pentacomp.cmbus.dispatcher;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.*;

@WebServiceProvider(serviceName = "MMSCService", portName = "MMSCPort",
                    targetNamespace = "http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-6-MM7-1-4")
@ServiceMode(Service.Mode.PAYLOAD)
public class MMSCService implements Provider<SOAPMessage> {

  public MMSCService() {

    System.out.println(":!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:");
    System.out.println(":!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:");
    System.out.println(":!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:");
    System.out.println(":!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:");
  }

  @Override
  public SOAPMessage invoke(SOAPMessage request) {

    SOAPMessage soapMessage;

    try {
      soapMessage = MessageFactory.newInstance().createMessage();
    } catch (SOAPException e) {
      throw new WebServiceException("dummy!");
    }

    return soapMessage;
  }
}
