# Curbside Mobile Automation Android | IOS

##Prerequisite - Android Automation
- Appium 
- Android SDK

##Prerequisite - IOS Automation

—Install homebrew
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

-Install Xcode with command line
-This need to be done from apple app store

-Install maven if not already done
brew install maven

-Install node and NPM
brew install node

-Install appium and dependencies
npm install -g grunt-cli
npm install -g appium
npm install wd
brew install libimobiledevice
brew install ideviceinstaller
npm install -g authorize-ios
npm install -g ios-deploy
brew install carthage
sudo authorize-ios

-Open following in xcode and setup code sign
/Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj

-To start appium server on local machine
-Start appium app and select advance option Session Override and start server

-To run tests
> create a device store and add devices to it
> Run tests using following command after cd into main directory

mvn test -DdeviceStore=../devices_ios.json