# ScoreAppTest - Automation for theScore app
This repository is to run automated test suite for theScore app against android device through appium server

## Prerequisites
Java 17 (Jdk 17.0.6)
AndroidStudio (Android Studio Electric Eel | 2022.1.1)
Nodejs (node 18.15.0) 
Appium server (1.22.3-4)
Maven (3.9.1)

## Installing pre-requisites
1. Install JDK https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
Specify the path to your JDK version in the JAVA_HOME environment variable
And add JAVA_HOME/bin to PATH variable

2. Install NodeJS and NPM
https://nodejs.org/en/download
Specify the path to your JDK version in the JAVA_HOME environment variable
Save node path

3. Install appium 
Either through NPM
npm install -g appium
OR
Install appium server GUI version https://github.com/appium/appium-desktop/releases
Save appium server path

4. Install AndroidStudio
https://developer.android.com/studio
Specify the path to your Android SDK version in the ANDROID_HOME environment variable
And add ANDROID_HOME/platform-tools and to ANDROID_HOME/tools to PATH variable

5. Connect real device or Configure Virtual device in Android studio’s Virtual device manager

6. Obtain android device details to run : like platformName, platformVersion and deviceName. Sample provided in 'To run the test' section
(Android 10, Q build used)

7. Install Apache maven 
Specify the path to your JDK version in the M2_HOME environment variable
And add M2_HOME/bin to PATH variable

8. Ensure all environment variables are setup properly 
- MAC -> In ~/.bash_profile
export JAVA_HOME=<jdk path>
export ANDROID_HOME =<android sdk path>
export M2_HOME = <maven path installed>
export PATH=${PATH}:$JAVA_HOME/bin:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$M2_HOME
(Assuming nodejs is installed in the default global location; else append the path in $PATH variable)
- WINDOWS -> Similarly configure these environment variables in system properties -> Environment variables (Change System or user variaables)



## To run the test
### Setup repository
1. Clone the reppsitory from Github
2. Ensure pre-requisites are complete; Else follow instructions for Installing pre-requisites
3. Start appium server
MAC - from terminal -> sh appium
Windows - from cmd prompt -> appium 
4. Connect the device OR Create a virtual device and start the emulator
- If using emulator : Open AndroidStudio -> Virtual Device manager ; Create a new device ; Start the emulator
- If connecting real device : Ensure device is connected and open

5. Install theScore from apk file in ./src/test/resources/apk/com.fivemobile.thescore_2023-03-01.apk on the device/emulator 

6. Provide appium server and android device details in testngAndroid.xml (Replace the values for these parameters)
Sample:
<parameter name="platformName" value="Android"></parameter>
<parameter name="platformVersion" value="10"></parameter>
<parameter name="deviceName" value="Pixel-6"></parameter>
<parameter name="appiumRemoteHost" value="http://127.0.0.1"></parameter>
<parameter name="appiumPort" value="4723"></parameter>
<parameter name="appiumRemotePath" value="/wd/hub/"></parameter>


7. To run Validate Team stats test : Since the TeamStats is changing everyday in score app, please provide latest reference data and first 5 team stats for each team in below files in ./src/test/resources/testdata/
- In data.json -> Specify the team name and icon abbrevation (Eg: Golden State Warriors , GS)
- Add/update the first 5 team stats data in corresponding team files -> File should be named as <Team name as displayed in app>.json

8. Run from command line - 
‘mvn clean test -DsuiteXmlFile=testngAndroid.xml'
This runs the entire test suite on an android device (real device connected or running on emulator)

