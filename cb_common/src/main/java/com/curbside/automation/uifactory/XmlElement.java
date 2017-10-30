package com.curbside.automation.uifactory;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.Point;

/**
 * @author kumar.anil
 *
 */

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class XmlElement {

	File xmlFile= null;
	String xPath= null;
	NodeList nodes= null;
	
	public Node getElement() throws Throwable  {
		return getElements().item(0);
	}
	
	public XmlElement(File xmlFile, String xPath)
	{
		this.xmlFile= xmlFile;
		this.xPath= xPath;
	}
	
	public XmlElement setPageSource(File xmlFile)
	{
		this.xmlFile= xmlFile;
		return this;
	}
	
	private NodeList getElements() throws Throwable  {
		if(nodes != null) return nodes;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile.getAbsolutePath());
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile(xPath);
		
		nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return nodes;
	}
	
	public String getText() throws Throwable {
		return getElement().getTextContent();
	}

	public boolean isDisplayed() throws Throwable {
		if(getCount() == 0)
			return false;
		else
			return Boolean.parseBoolean(getAttribute("visible"));
	}

	public static XmlElement byXpath(File xmlFile, String xPath) {
		return new XmlElement(xmlFile, xPath);
	}
	
	public static XmlElement byId(File xmlFile, String id) {
		return new XmlElement(xmlFile, "//*[@id='" + id + "']");
	}
	
	public static XmlElement byName(File xmlFile, String name) {
		return new XmlElement(xmlFile, "//*[@name='" + name + "']");
	}
	
	public static XmlElement byLabel(File xmlFile, String name) {
		return new XmlElement(xmlFile, "//*[@label='" + name + "']");
	}
	
	public String getAttribute(String attrName) throws Throwable {
		return getElement().getAttributes().getNamedItem(attrName).getTextContent();
	}

	public int getCount() throws Throwable {
		return getElements().getLength();
	}

	public int getHeight() throws Throwable {
		return Integer.parseInt(getAttribute("height"));
	}

	public int getWidth() throws Throwable {
		return Integer.parseInt(getAttribute("width"));
	}
	
	public int getX() throws Throwable {
		return Integer.parseInt(getAttribute("x"));
	}
	
	public int getY() throws Throwable {
		return Integer.parseInt(getAttribute("y"));
	}
	
	public Point getCenter() throws Throwable
	{
		int loc_x= getX() + getWidth()/2;
		int loc_y= getY() + getHeight()/2;
		return new Point(loc_x, loc_y);
	}
	
	public void tap() throws Throwable
	{
		Point loc= getCenter();
		new TouchAction((AppiumDriver) (DriverFactory.getDriver())).tap(loc.x, loc.y).perform();
	}
}