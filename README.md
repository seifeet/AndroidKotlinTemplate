### 🤡 Why?

Almost all modern mobile consumer apps are constructed from the same 4 components.

1. A component to communicate to the backend server
2. A way to organize the flow (screen A opens screen B etc.)
3. Something to store temporary data (i.e. collecting data from the user to send it later to the backend)
4. A component that helps to easily test the app

This app combines all the above components so that it can be used as a default template to build simple consumer apps.
There will be separate tutorials to explain each of the 4 components and the general architecture of the app.


### 🚀 Quick start

1.  Build (debug)

    `./gradlew assembleDebug`


2.  Build (release)

    `./gradlew assembleRelease`


3.  Copy the Build to the device

    `adb push app/build/outputs/apk/release/app-release.apk /data/local/tmp`


4.  Install the Build
    Apps that are installed from the Play store have installer name equal to com.android.vending

    PackageManager packageManager = getPackageManager();
    String installerName = packageManager.getInstallerPackageName(getPackageName());
    if (installerName != null) {
        if (installerName.equals("com.android.vending")) {
            mHasGooglePlay = true;
        }
    }

    To simulate it we can use the following trick:

    `adb shell pm install -i com.android.vending /data/local/tmp/app-release.apk`
