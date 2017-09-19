package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class ListDonationsController implements Serializable{

	private static final long serialVersionUID = -9060245742365679590L;
	
	public String doOK() {
		return Pages.LIST_CAMPAIGNS;
	}

}
