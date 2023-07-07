# CountdownTimerLibrary
CountdownTimerLibrary is an open-source library for using countdown codes easily. Users can define and initialize the time on their program needs.

## The library contains two view model classes of the timer functions:
  ### CountdownTimerViewModel 
  which is running also in the background when the app is on pause.
  ### SensitiveCountdownTimerViewModel 
  which is sensitive to the app's lifecycle. When the app is onPause stage the timer is paused, 
  while on onResume the paused time is displayed till the user press on resume button to run the time from the paused time.


