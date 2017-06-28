package com.curbside.automation.customerApp.android.pages.signInSignUp;

import com.curbside.automation.common.pages.Page;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by hitesh.grover on 27/06/17.
 */
public class SignInSignUp extends Page {

    @FindBy(id = "com.curbside.nCurbside:id/button_sign_up_email")
    public WebElement signUpWithEmail;
    @FindBy(id = "com.curbside.nCurbside:id/edit_email_sign_up")
    public WebElement email;
    @FindBy(id = "com.curbside.nCurbside:id/edit_phone_number_sign_up")
    public WebElement phoneNumber;
    @FindBy(id = "com.curbside.nCurbside:id/edit_password_sign_up")
    public WebElement password;
    @FindBy(id = "com.curbside.nCurbside:id/button_create_account_sign_up")
    public WebElement createAccountButton;



    public SignInSignUp(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public WebElement getSignUpWithEmailButton(){
        return signUpWithEmail;
    }

    public WebElement getEmailTextField(){
        return email;
    }

    public WebElement getPhoneNumberTextField(){
        return phoneNumber;
    }

    public WebElement getPasswordTextField(){
        return password;
    }

    public WebElement getCreateAccountButton(){
        return createAccountButton;
    }


}
