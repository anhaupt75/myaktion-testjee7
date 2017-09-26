package de.dpunkt.myaktion.test.pages;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("organizer/listCampaigns.jsf")
public class ListCampaignsPage extends AbstractPage{
	
	@FindBy(xpath="//tbody/tr[last()]/td[2]")
	private WebElement lastCampainName;
	
	@FindBy(xpath="//tbody/tr[last()]/td[7]/a")
	private WebElement lastEditFormLink;
	
	public void assertOnPage() {
		assertTitle("listCampaigns.my_campaigns");
	}
	
	public void assertCampaignName(String campaignName) {
		assertEquals(campaignName,lastCampainName.getText());
	}
	
	public void doAddCampaign() {
		WebElement addCampainButton = 
					getButtonByLabel("listCampaigns.add_campaign");
		guardHttp(addCampainButton).click();
	}
	
	public void clickCampainUrl() {
		guardHttp(lastEditFormLink).click();
	}
}
