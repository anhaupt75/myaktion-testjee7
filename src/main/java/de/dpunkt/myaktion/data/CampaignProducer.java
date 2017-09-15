package de.dpunkt.myaktion.data;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Campaign;

@SessionScoped
@Named
public class CampaignProducer implements Serializable{

	private static final long serialVersionUID = -109280501418370159L;
	
	private enum Mode {
		EDIT, ADD
	}

	private Campaign selectedCampaign;
	private Mode mode;

	public Campaign getSelectedCampaign() {
		return selectedCampaign;
	}
	
	public void setSelectedCampaign(Campaign selectedCampaign) {
		this.selectedCampaign = selectedCampaign;
	}
	
	public boolean isAddMode() {
		return mode.equals(Mode.ADD);
	}
	
	public void prepareAddCampaign() {
		this.selectedCampaign = new Campaign();
		mode=Mode.ADD;
	}

	public void prepareEditCampaign(Campaign campaign) {
		this.selectedCampaign = campaign;
		mode=Mode.EDIT;
	}
}
