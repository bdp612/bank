package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import banking.userInterface;

public class main{

	public static void main(String[] args) throws Exception {
				
		ApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
		
		userInterface UI = (userInterface) ctx.getBean("UI");
		UI.init();
	}
}
