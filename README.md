
# react-native-send-intent

## Getting started

`$ npm install react-native-send-intent --save`

### Mostly automatic installation

`$ react-native link react-native-send-intent`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNSendIntentPackage;` to the imports at the top of the file
  - Add `new RNSendIntentPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-send-intent'
  	project(':react-native-send-intent').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-send-intent/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-send-intent')
  	```


## Usage
```javascript
import RNSendIntent from 'react-native-send-intent';

// TODO: What to do with the module?
RNSendIntent;
```
  