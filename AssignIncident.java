package week5day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AssignIncident extends ServiceNowBaseClass {

	@Test
	public void runAssignIncident() throws InterruptedException {
		WebElement frame2 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame2);
		WebElement drop1 = driver.findElement(By.xpath("//select[@role='listbox']"));
		Select dropdown = new Select(drop1);
		dropdown.selectByValue("caller_id");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role='search']//input")).sendKeys("Harithha", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='vt']//a)[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		Set<String> handles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(handles);
		driver.switchTo().window(list.get(1));
		driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).sendKeys("software", Keys.ENTER);
		driver.findElement(By.xpath("//tr//td//a[text()='Software']")).click();
		driver.switchTo().window(list.get(0));
		Thread.sleep(5000);
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("(//div//textarea[@aria-label='Work notes'])[3]")).sendKeys("Test");
		driver.findElement(By.xpath("//div//button[@type='submit']")).click();
		String assGroup = driver.findElement(By.xpath("//tr[@record_class='incident']//td[10]")).getText();
		if (assGroup.contains("Software")) {
			System.out.println("Assignment group is updated correctly:" + assGroup);
		} else
			System.out.println("Assignment group is incorrect:" + assGroup);
	}

}
