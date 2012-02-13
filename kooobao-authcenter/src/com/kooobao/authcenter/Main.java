package com.kooobao.authcenter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("application-context.xml");
		while (true) {
			// Wait to be killed
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				break;
			}
		}
	}

}
