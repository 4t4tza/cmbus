package pl.pentacomp.cmbus.commons.model;

public class RecipientData {

  private char [] msisdn;

  private String rawParamString;

  public char[] getMsisdn() {

    return msisdn;
  }

  public void setMsisdn(String msisdn) {

    this.msisdn = msisdn.toCharArray();
  }

  public void setMsisdn(char[] msisdn) {

    this.msisdn = msisdn;
  }

  public String getRawParamString() {

    return rawParamString;
  }

  public void setRawParamString(String rawParamString) {

    this.rawParamString = rawParamString;
  }
}
