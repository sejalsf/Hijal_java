package Helthcareproject;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MMPLibrary {
	WebDriver driver1;
	public MMPLibrary(WebDriver driver1) 
	{
		
		this.driver1 = driver1;
	}
	public void login(String string, String string2) {
		driver1.findElement(By.id("username")).sendKeys("ria1");
		driver1.findElement(By.id("password")).sendKeys("Ria12345");
		driver1.findElement(By.xpath("//input[@type = 'submit']")).click();
	}
	
	
	public void geturl(String url)
	{
		driver1.get(url);
	}
	
	
public void navigatetomodule(String Option) {
	driver1.findElement(By.xpath("//span[contains(text(),'"+Option+"')]")).click();
}

public HashMap<String,String> Bookappoinment() {
	HashMap<String,String> expectedHmap=new HashMap<String, String>();

	String doctorname = "Charlie";
	expectedHmap.put("doctor", doctorname);
	
	driver1.findElement(By.xpath("//input[@value = 'Create new appointment']")).click();
	driver1.findElement(By.xpath("//h4[contains(.,'"+doctorname+"')]/ancestor::ul/following-sibling::button")).click();


	
	driver1.switchTo().frame("myframe");
	driver1.findElement(By.id("datepicker")).click();
	String expecteddate =AppLibrary.getfutureDate(10) ;//08/12/2023
	String actualyear = driver1.findElement(By.className("ui-datepicker-year")).getText();
	while(!(actualyear.equals(expecteddate.split("/")[2])))
	{
		driver1.findElement(By.className("ui-icon ui-icon-circle-triangle-e")).click();
		actualyear=driver1.findElement(By.className("ui-datepicker-year")).getText();
	}
	String actualmonth = driver1.findElement(By.className("ui-datepicker-month")).getText();
	while(!(actualmonth.equals(expecteddate.split("/")[1])))
	{
		driver1.findElement(By.className("ui-icon ui-icon-circle-triangle-e")).click();
		actualmonth=driver1.findElement(By.className("ui-datepicker-month")).getText();
	}
	driver1.findElement(By.linkText(expecteddate.split("/")[0])).click();
	expecteddate =AppLibrary.getfutureDate(10,"MM/dd/YYYY");
	expectedHmap.put("date", expecteddate);
	
	WebElement time = driver1.findElement(By.name("time"));
	Select sel = new Select(time);
	expectedHmap.put("time","11Am");
	sel.selectByVisibleText("11Am");
	
	
	
	driver1.findElement(By.xpath("//button[@id = 'ChangeHeatName']")).click();
	driver1.switchTo().defaultContent();
	driver1.findElement(By.xpath("//textarea[@class='list-group list-statistics']")).sendKeys("Sore throat and fever");
	expectedHmap.put("symptoms","Sore throat and fever");
	
	driver1.findElement(By.xpath("//input[@type = 'submit']")).click();
	return expectedHmap;
}

public HashMap<String,String> fetchvalues() {
HashMap<String,String> actualHmap = new HashMap<String,String>();
actualHmap.put("date", driver1.findElement(By.xpath("//table[@class = 'table']/tbody/tr[1]/td[1]")).getText());
actualHmap.put("time",driver1.findElement(By.xpath("//table[@class = 'table']/tbody/tr[1]/td[2]")).getText());
actualHmap.put("symptoms",driver1.findElement(By.xpath("//table[@class = 'table']/tbody/tr[1]/td[3]")).getText());
actualHmap.put("doctor", driver1.findElement(By.xpath("//table[@class = 'table']/tbody/tr[1]/td[4]")).getText());
return actualHmap;
	
	
}

public HashMap<String, String> editprofile() {
	HashMap<String,String> expectedmap1=new HashMap<String, String>();
	driver1.findElement(By.id("Ebtn")).click();
	driver1.findElement(By.xpath("//input[@id='fname']")).clear();
	
	
	String expectedFname= AppLibrary1.randomstring1("Sejal");
	expectedmap1.put("Fname", expectedFname);
	driver1.findElement(By.xpath("//input[@id='fname']")).sendKeys(expectedFname);
	
	String expectedLname = AppLibrary1.randomstring1("Panchal");
	expectedmap1.put("Lname", expectedLname);
	driver1.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedLname);
	
	driver1.findElement(By.xpath("//input[@value='Save']")).click();
	Alert alert = driver1.switchTo().alert();
	alert.accept();
	return expectedmap1;
	
}



public HashMap<String,String> infosave() {
	HashMap<String,String> actualmap = new HashMap<String,String>();
	String actual = driver1.findElement(By.xpath("//input[@id='fname']")).getAttribute("value");
	actualmap.put("Fname", actual);

	String actualL = driver1.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value");
	actualmap.put("Lname", actualL);
	return actualmap;
	
}
}