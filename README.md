# Mobile Automation By www.tftus.com

Dillinger is a cloud-enabled, mobile-ready, offline-storage, AngularJS powered HTML5 Markdown editor.

  - Type some Markdown on the left
  - See HTML in the right
  - Magic

## iOS Setup

##### Install homebrew
#
```sh
$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
  ```
##### Install Xcode with command line tools from apple app store
##### Install maven if not already done
#
```sh
$ brew install maven
```
##### Install following appium specific applications
#
```sh
$ brew install node libimobiledevice ideviceinstaller carthage
$ npm install -g appium grunt-cli wd authorize-ios ios-deploy
$ sudo authorize-ios
```
> last command here will ask for a password
##### Download and move appium app to applications folder
##### Open following project in xcode and setup signing with apple developer account
#
> /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj

##### Once done with above, setup a complete, start applium application and start server on default port (or change if you wish to)

### Setting up for execution of tests

> Create a device store and commit at root of the project with name as devices_<name>.json
#
##### Start running tests
#
```sh
$ cd <project_directory>
$ mvn test -DdeviceStore=../devices_<name>.json
```

###### To run only iOS tests
```sh
$ mvn test -DdeviceStore=../devices_<name>.json -DsuiteFile=./testng-ios.xml
```