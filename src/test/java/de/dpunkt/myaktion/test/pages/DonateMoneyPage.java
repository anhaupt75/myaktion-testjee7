package de.dpunkt.myaktion.test.pages;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

import java.text.NumberFormat;
import java.util.Locale;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.dpunkt.myaktion.model.Donation;

@Location("donateMoney.jsf")
public class DonateMoneyPage extends AbstractPage{

	@FindBy(xpath="//input[contains(@id,'name')]")
	private WebElement name;
	@FindBy(xpath="//input[contains(@id,'iban')]")
	private WebElement iban;
	@FindBy(xpath="//input[contains(@id,'name_bank')]")
	private WebElement nameBank;
	@FindBy(xpath="//input[contains(@id,'donationAmount')]")
	private WebElement donationamount;
	
	public void assertOnPage() {
		assertTitle("donateMoney.donate_money");
	}
	
	public void setDonation(Donation donation) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
		name.sendKeys(donation.getDonorName());
		iban.sendKeys(donation.getAccount().getIban());
		nameBank.sendKeys(donation.getAccount().getNameOfBank());
		donationamount.sendKeys(numberFormat.format(donation.getAmount()));
	}
	
	public void doDonation() {
		guardHttp(getButtonByLabel("donateMoney.donate")).click();
	}
	
	public void assertThankYou() {
		assertFacesMessage("donateMoney.thank_you");
	}
}
