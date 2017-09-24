package de.dpunkt.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.model.Campaign;

@Stateless
public class CampaignServiceBean implements CampaignService{
	
	@Inject
	private EntityManager em;
	
	@Override
	public List<Campaign> getAllCampaigns() {
		
		TypedQuery<Campaign> query =
				em.createNamedQuery(Campaign.findAll,Campaign.class);
		List<Campaign> campaigns=query.getResultList();
		campaigns.forEach(campaign->campaign.setAmountDonatedSoFar(getAmountDonatedSoFar(campaign)));
		return campaigns;
	}

	@Override
	public void addCampaign(Campaign campaign) {
		em.persist(campaign);
		
	}

	@Override
	public void deleteCampaign(Campaign campaign) {
		Campaign managedCampaign=em.find(Campaign.class, campaign.getId());
		em.remove(managedCampaign);
	}

	@Override
	public void updateCampaign(Campaign campaign) {
		em.merge(campaign);
		
	}
	private Double getAmountDonatedSoFar(Campaign campaign) {
		TypedQuery<Double> query =
				em.createNamedQuery(Campaign.getAmountDonatedSoFar,Double.class);
		query.setParameter("campaign", campaign);
		Double result=query.getSingleResult();
		if (result == null)
			result=0d;
		return result;
	}

}
