package pl.pentacomp.cmbus.commons.model;

public class CampaignStep {

  private CampaignDefinition campaignDefinition;

  private RecipientData [] recipients;

  public CampaignDefinition getCampaignDefinition() {

    return campaignDefinition;
  }

  public void setCampaignDefinition(CampaignDefinition campaignDefinition) {

    this.campaignDefinition = campaignDefinition;
  }

  public RecipientData[] getRecipients() {

    return recipients;
  }

  public void setRecipients(RecipientData[] recipients) {

    this.recipients = recipients;
  }
}
