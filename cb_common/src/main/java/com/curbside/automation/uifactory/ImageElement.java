package com.curbside.automation.uifactory;

import java.io.File;

/**
 * @author kumar.anil
 *
 */

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.api.DefaultScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;

public class ImageElement {

	File searchImage = null;
	Double matchScore= 0.90;
	

	public ImageElement(File partialSearchImage) {
		this.searchImage = partialSearchImage;
	}
	
	public ImageElement(File partialSearchImage, Double matchScore) {
		this.searchImage = partialSearchImage;
		this.matchScore= matchScore;
	}

	public Point getCenter() throws Throwable {
		ScreenRegion screenMatchRegion= new DefaultScreenRegion(new SikuliScreen(DriverFactory.getDriver()));
		screenMatchRegion.setScore(matchScore);
		
		ImageTarget target = new ImageTarget(searchImage);
		ScreenRegion matchedRegion= screenMatchRegion.find(target);
		
		return new Point(matchedRegion.getCenter().getX(), matchedRegion.getCenter().getY());
	}

	public void tap() throws Throwable {
		Point c = getCenter();
		MobileDevice.tap(c.x, c.y);
	}
}