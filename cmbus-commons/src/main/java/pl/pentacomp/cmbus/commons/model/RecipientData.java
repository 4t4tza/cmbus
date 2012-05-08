package pl.pentacomp.cmbus.commons.model;

import java.util.Arrays;

public class RecipientData {

  private static final int PARAMS_LENGTH = 20;

  private static final String [] PARAM_KEYS;

  static {

    PARAM_KEYS = new String[PARAMS_LENGTH];

    for(int i = 0; i < PARAMS_LENGTH; i++)
      PARAM_KEYS[i] = String.format("param%02d",i);
  }

  private char [] msisdn;

  private char [][] params;

  private short currentParamsLength;

  public RecipientData() {

    currentParamsLength = 0;
    params = new char[PARAMS_LENGTH][];
  }

  public char[] getMsisdn() {

    return msisdn;
  }

  public void setMsisdn(String msisdn) {

    this.msisdn = msisdn.toCharArray();
  }

  public void setMsisdn(char[] msisdn) {

    this.msisdn = msisdn;
  }

  public char [] param(int paramIndex) {

    return params[paramIndex];
  }

  public void addParam(char []param) {

    params[currentParamsLength] = param;
    currentParamsLength++;
  }

  public void addParam(String param) {

    addParam(param.toCharArray());
  }

  @Override
  public String toString() {

    return "RecipientData{" +
           "msisdn=" + String.valueOf(msisdn) +
           ", params=" + (params == null ? null : Arrays.asList(params)) +
           ", currentParamsLength=" + currentParamsLength +
           '}';
  }
}
