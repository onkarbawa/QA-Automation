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

> /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj

##### Once done with above, setup a complete, start applium application and start server on default port (or change if you wish to)

## Android Setup [MAC]

###### To run only iOS tests
##### Download Android SDK/Studio on mac 
* [SDK](https://developer.android.com/studio/index.html
* [Studio](https://developer.android.com/studio/index.html)

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

## Setting up for execution of tests

> Create a device store and commit at root of the project with name as devices_<name>.json

##### Start running tests
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
