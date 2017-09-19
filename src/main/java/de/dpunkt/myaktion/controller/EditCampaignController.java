package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.data.CampaignListProducer;
import de.dpunkt.myaktion.data.CampaignProducer;

@ViewScoped
@Named
public class EditCampaignController  implements Serializable{

	private static final long serialVersionUID = 8057074888567970679L;
	
	@Inject
	private CampaignListProducer campaignListProducer;

	@Inject
	private CampaignProducer campaignProducer;
	
	public String doSave() {
		if (campaignProducer.isAddMode()) {
			campaignListProducer.getCampaigns().add(
					campaignProducer.getSelectedCampaign());
		}
		return Pages.LIST_CAMPAIGNS;
	}
	
	public String doCancel() {
		return Pages.LIST_CAMPAIGNS;
	}

}
