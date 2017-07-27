/**
 * 
 */
package com.tikbiz.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 387090
 *
 */
@SpringBootApplication(scanBasePackages = { "com.tikbiz"})
public class TikBizBoot {

	/**
	 * Acts as the launch point for the entire application
	 */
	public static void main(String[] args) {
		SpringApplication.run(TikBizBoot.class, args);

	}

}
