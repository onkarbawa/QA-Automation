# Mobile Automation Machine Setup

##### Install homebrew

```sh
$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
  ```
##### Install maven if not already done

```sh
$ brew install maven
```
##### Install following appium specific applications

```sh
$ brew install node libimobiledevice ideviceinstaller carthage
$ npm install -g appium grunt-cli wd authorize-ios ios-deploy cucumber-html-reporter
$ sudo authorize-ios
```
> last command here will ask for a password
##### Download and move appium app to applications folder

## iOS Setup

##### Install Xcode with command line tools from apple app store
##### Open following project in xcode and setup signing with apple developer account
```sh
$ cd /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/
$ ./Scripts/bootstrap.sh
```

> /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj

##### Once done with above, setup is complete, start appium application and start server on default port (or change if you wish to)

## Android Setup [MAC]

##### Download Android SDK/Studio on mac 
[Android SDK](https://developer.android.com/studio/index.html
[Android Studio](https://developer.android.com/studio/index.html)

```sh
$ brew cask install android-platform-tools
```

##### Open SDK manager and install API levels required
##### Set the Environment path :
```sh
$ nano ~/.bash_profile 
$ export ANDROID_HOME=/YOUR_PATH_TO/android-sdk
$ export PATH=$ANDROID_HOME/platform-tools:$PATH
$ export PATH=$ANDROID_HOME/tools:$PATH
```
###### Reopen terminal and check if it worked

```sh
$ source ~/.bash_profile 
$ echo $ANDROID_HOME
$ adb devices
```
> adb devices should show all connected android devices

## Custom inspector
```sh
$ npm install macaca-cli -g
$ macaca doctor
$ npm install app-inspector -g
```
> To launch inspector, use
```sh
$ app-inspector -u YOUR-DEVICE-ID
```

## Setting up for execution of tests

> Create a device store and commit at root of the project with name as devices_<name>.json

##### Start running tests
> Start appium in session ovrride mode
```sh
$ cd <project_directory>
$ mvn test -DdeviceStore=../devices_<name>.json
```

###### To run only iOS tests
```sh
$ mvn test [-DdeviceStore=../devices_<name>.json] -Dsurefire.suiteXmlFiles=testng-ios.xml
```

###### To run only android tests
```sh
$ mvn test [-DdeviceStore=../devices_<name>.json] -Dsurefire.suiteXmlFiles=testng-android.xml
```

##### Important links
http://thucydides.info/docs/articles/an-introduction-to-serenity-bdd-with-cucumber.html
https://github.com/appium/appium/tree/master/docs/en
https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/running-tests.md
https://github.com/appium/appium/blob/master/docs/en/appium-setup/real-devices-ios.md
https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/uiautomator_uiselector.md
https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/ios_predicate.md
https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/touch-actions.md
https://github.com/SrinivasanTarget/awesome-appium	
https://github.com/priyankshah217/AppiumCucumberTest/blob/master/src/test/java/com/test/RunCukesTest.java

https://mmbs.github.io/tools/adb/2016/06/22/helpful-adb-commands/