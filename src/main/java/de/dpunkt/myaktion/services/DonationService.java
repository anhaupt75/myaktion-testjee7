package de.dpunkt.myaktion.services;

import java.util.List;

import de.dpunkt.myaktion.model.Donation;

public interface DonationService {
	List<Donation> getDonations(Long campaignId);
	void addDonation(Long campaignId, Donation donation);
}
