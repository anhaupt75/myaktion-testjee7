package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class ListDonationsController implements Serializable{

	private static final long serialVersionUID = -9060245742365679590L;
	
	public String doOK() {
		return Pages.LIST_CAMPAIGNS;
	}

}
