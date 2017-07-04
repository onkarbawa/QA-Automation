package com.curbside.automation.uifactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.api.Screen;

/**
 * @author kumar.anil
 *
 */

public class SikuliScreen implements Screen {
	
	TakesScreenshot driver;
    Dimension size;
    
    public SikuliScreen(WebDriver driver) throws Throwable {
        this.driver = (TakesScreenshot) driver;
        size = driver.manage().window().getSize();
    }

	@Override
	public BufferedImage getScreenshot(int arg0, int arg1, int arg2, int arg3) {
		File screenshot= driver.getScreenshotAs(OutputType.FILE);
		try {
			return ImageIO.read(screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public java.awt.Dimension getSize() {
		return new java.awt.Dimension(size.getWidth(), size.getHeight());
	}

}
