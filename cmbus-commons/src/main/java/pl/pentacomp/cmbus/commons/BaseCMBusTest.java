package pl.pentacomp.cmbus.commons;

import org.apache.camel.test.junit4.CamelTestSupport;

import java.io.InputStream;

public abstract class BaseCMBusTest extends CamelTestSupport {

  protected String getTestXml(String resourceName) {

    String path = getClass().getCanonicalName().replaceAll("\\.", "/") + "/" + resourceName + ".xml";

    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

    if (is == null)
      throw new RuntimeException(String.format("Unable to locate file: %s", path));

    return convertTo(String.class, is);
  }

  private <T> T convertTo(Class<T> type, Object value) {

    return context().getTypeConverter().convertTo(type, value);
  }
}
