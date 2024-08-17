package page;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Substitutor.ForAttachment;


public class ListCustomerPage extends BasePage{
	//declare WebDriver
	WebDriver driver;
	
	//Constructor to hold driver
	public ListCustomerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//identify element using FindBy
	@FindBy(how = How.XPATH, using = "//strong[text()='New Customer']") WebElement ADD_CUSTOMER_HEADER_ELEMENT;
	

	//Intractable methods
	
	
	//tbody/tr[1]/td[2]/a
	//tbody/tr[2]/td[2]/a
	//tbody/tr[3]/td[2]/a
	//tbody/tr[i]/td[2]/a
	//tbody/tr[i]/td[9]/button
	
	
	public void validateInsertedNameAndDelete() {
		String before_Xpath = "//tbody/tr[";
		String after_Xpath = "]/td[2]/a";
		String after_Xpath_delete = "]/td[9]/button";
		
		for (int i = 1; i <= 5; i++) {
//			driver.findElement(By.xpath("//tbody/tr[i]/td[2]/a"));
			String actualText = driver.findElement(By.xpath(before_Xpath + i + after_Xpath)).getText();
			System.out.println(actualText);
			if(actualText.contains(AddCustomerPage.getInsertedName())) {
				System.out.println("Name exist!!");
				driver.findElement(By.xpath(before_Xpath + i + after_Xpath_delete)).click();
			}
			break;
		}
	}
	
	
}
