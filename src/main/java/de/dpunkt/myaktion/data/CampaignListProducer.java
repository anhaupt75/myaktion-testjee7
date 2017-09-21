package de.dpunkt.myaktion.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Account;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;
import de.dpunkt.myaktion.services.CampaignService;
import de.dpunkt.myaktion.util.Events.Added;
import de.dpunkt.myaktion.util.Events.Deleted;

@SessionScoped
public class CampaignListProducer implements Serializable{

	private static final long serialVersionUID = 1335819936144510848L;
	
	private List<Campaign> campaigns;
	
	@Inject
	private CampaignService campaignService;
	
	@PostConstruct
	public void init() {
		campaigns = campaignService.getAllCampaigns();
	}

	@Produces
	@Named
	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	
	public void onCampaignAdded(@Observes @Added Campaign campaign) {
		getCampaigns().add(campaign);
	}
	public void onCampaignDeleted(@Observes @Deleted Campaign campaign) {
		getCampaigns().remove(campaign);
	}
}
