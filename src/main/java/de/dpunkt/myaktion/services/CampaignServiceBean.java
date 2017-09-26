package de.dpunkt.myaktion.services;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Organizer;

@RolesAllowed("Organizer")
@Stateless
public class CampaignServiceBean implements CampaignService{
	
	@Inject
	private EntityManager em;
	
	@Resource
	private SessionContext sessionContext;
	
	@Override
	public List<Campaign> getAllCampaigns() {
		
		TypedQuery<Campaign> query =
				em.createNamedQuery(Campaign.findByOrganizer,Campaign.class);
		query.setParameter("organizer", getLoggedInOrganizer());
		List<Campaign> campaigns=query.getResultList();
		campaigns.forEach(campaign->campaign.setAmountDonatedSoFar(getAmountDonatedSoFar(campaign)));
		return campaigns;
	}
	
	private Organizer getLoggedInOrganizer() {
		String organizerEmail=sessionContext.getCallerPrincipal().getName();
		Organizer organizer=em.createNamedQuery(Organizer.findByEmail,Organizer.class).
				setParameter("email", organizerEmail).getSingleResult();
		return organizer;
	}

	@Override
	public void addCampaign(Campaign campaign) {
		Organizer organizer=getLoggedInOrganizer();
		campaign.setOrganizer(organizer);
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
