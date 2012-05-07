package pl.pentacomp.cmbus.commons.model;

import java.util.Date;

public class CampaignDefinition {

  private String campaignId;

  private Long step;

  private Long cycle;

  private String smilURI;

  private Date timestamp;

  public String getCampaignId() {

    return campaignId;
  }

  public void setCampaignId(String campaignId) {

    this.campaignId = campaignId;
  }

  public Long getStep() {

    return step;
  }

  public void setStep(Long step) {

    this.step = step;
  }

  public Long getCycle() {

    return cycle;
  }

  public void setCycle(Long cycle) {

    this.cycle = cycle;
  }

  public String getSmilURI() {

    return smilURI;
  }

  public void setSmilURI(String smilURI) {

    this.smilURI = smilURI;
  }

  public Date getTimestamp() {

    return timestamp;
  }

  public void setTimestamp(Date timestamp) {

    this.timestamp = timestamp;
  }

  @Override
  public String toString() {

    return "CampaignDefinition{" +
           "campaignId=" + campaignId +
           ", step=" + step +
           ", cycle=" + cycle +
           ", smilURI='" + smilURI + '\'' +
           ", timestamp=" + timestamp +
           '}';
  }
}
