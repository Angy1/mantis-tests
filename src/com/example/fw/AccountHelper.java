package com.example.fw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountHelper extends HelperWithWebDriverBase {


	public AccountHelper(ApplicationManager applicationManager)  {
		super(applicationManager);
	}


	public void signup(User user) {
		openUrl("/");
		click(By.cssSelector("span.bracket-link"));
		type(By.name("username"), user.login);
		type(By.name("email"), user.email);
		click(By.cssSelector("input.button"));
		
		WebElement errorMessage = findElement(By.cssSelector("table.width50 tbody tr td p"));
		if (errorMessage != null) {
			throw new RuntimeException(errorMessage.getText());
		}
        
		pause(3000);
		String msg = manager.getMailHelper().getNewMail(user.login, user.password);
		String confirmationLink = getConfirmationLink(msg);
		openAbsoluteUrl(confirmationLink);
		
		type(By.name("password"), user.password);
		type(By.name("password_confirm"), user.password);
		click(By.cssSelector("input.button"));
	}
		
	
	public String getConfirmationLink(String text) {
		Pattern regex = Pattern.compile("http\\S*");
		//Pattern regex = Pattern.compile("\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			System.out.println(matcher.group());
			return matcher.group();
		} else {
			System.out.println("empty");
			return "";
		}
	}

	
	public void login (User user) {
		pause(3000);
		openUrl("/");
		pause (3000);
		type(By.name("username"), user.login);
		type(By.name("password"), user.password);
		click(By.cssSelector("input.button"));
	}
	
	
	public String loggedUser() {
		WebElement element = findElement(By.cssSelector("td.login-info-left span"));
		return element.getText();
	}
}
