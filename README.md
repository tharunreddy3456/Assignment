1-	Set JAVA_HOME in Environment Variables.  Sample: C:\Program Files\Java\jdk1.8.0_161 

2-	Download and install Android Studio, if you want to use Emulators, in this test I have used real device.

3-	Set ANDROID_HOME in Environment Variable. Sample:  C:\Users\tharun\AppData\Local\Android\sdk

4-	Make sure you have SDK Platform Tools installed. It can be downloaded from here https://developer.android.com/studio/releases/platform-tools

5-	Connect your real device to your PC with USB debugging ON 

6-	Now find out the device ID and device name for the device which you will use for testing

7-	Device name can be found it the Phone Setting and for DeviceID, Open adb.exe from the SDK Platform Tools in command prompt and enter the following command: adb devices. This will return the device ID for the connected device and it current status.

8-	Download and install Appium from the following link  https://github.com/appium/appium-desktop/releases/tag/v1.13.0

9-	Open Appium application after installation and click on the start server button

10-	Verify Appium is up and running
 
11-	Now Open the MobileAutomation Project in eclipse replace the Device name and device ID in the Test Scripts 
Onboarding Script:
 
PlaySong Script:
 
12-	Now right click on the testing.xml file

 
13-	Now select Run As > TestNG Suite. This should start running the script one by one.
