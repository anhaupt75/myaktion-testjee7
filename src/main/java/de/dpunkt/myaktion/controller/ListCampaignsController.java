package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.data.CampaignProducer;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.util.Events.Deleted;

@ViewScoped
@Named
public class ListCampaignsController implements Serializable {

	private static final long serialVersionUID = -7070812523557511575L;
	
	@Inject
	private CampaignProducer campaignProducer;
	
	@Inject @Deleted
	private Event<Campaign> campaignDeleteEvent;
	
	private Campaign campaignToDelete;
	
	public String doAddCampaign() {
		campaignProducer.prepareAddCampaign();
		return Pages.EDIT_CAMPAIGN;
	}

	public String doEditCampaign(Campaign campaign) {
		campaignProducer.prepareEditCampaign(campaign);
		return Pages.EDIT_CAMPAIGN;
	}

	public String doEditDonationForm(Campaign campaign) {
		campaignProducer.setSelectedCampaign(campaign);
		return Pages.EDIT_DONATION_FORM;
	}

	public String doListDonations(Campaign campaign) {
		campaignProducer.setSelectedCampaign(campaign);
		return Pages.LIST_DONATIONS;
	}

	public void doDeleteCampaign(Campaign campaign) {
		campaignToDelete=campaign;
	}

	public void commitDeleteCampaign() {
		campaignDeleteEvent.fire(campaignToDelete);
	}

}
