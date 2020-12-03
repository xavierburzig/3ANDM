package com.supinfo.andm

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    /**
     * https://developer.android.com/guide/components/activities/activity-lifecycle#alc
     */
    /*
    On activity creation, the activity enters the Created state.
    In the onCreate() method, you perform basic application startup logic that should happen only once for the entire life of the activity.
    For example, your implementation of onCreate() might bind data to lists, associate the activity with a ViewModel, and instantiate some class-scope variables.
    This method receives the parameter savedInstanceState, which is a Bundle object containing the activity's previously saved state.
    If the activity has never existed before, the value of the Bundle object is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        Logger.getLogger(MainActivity::class.java.name).info("Hello.. from main activity")
        Log.i(MainActivity::class.java.name, "OnCreate")
    }

    /*
    When the activity enters the Started state, the system invokes this callback.
    The onStart() call makes the activity visible to the user, as the app prepares for the activity to enter the foreground and become interactive.
    For example, this method is where the app initializes the code that maintains the UI.
    When the activity moves to the started state, any lifecycle-aware component tied to the activity's lifecycle will receive the ON_START event.
    The onStart() method completes very quickly and, as with the Created state, the activity does not stay resident in the Started state.
    Once this callback finishes, the activity enters the Resumed state, and the system invokes the onResume() method.
     */
    override fun onStart() {
        super.onStart()
        Log.i(MainActivity::class.java.name, "OnStart")

    }

    /*
    When the activity enters the Resumed state, it comes to the foreground, and then the system invokes the onResume() callback.
    This is the state in which the app interacts with the user. The app stays in this state until something happens to take focus away from the app.
    Such an event might be, for instance, receiving a phone call, the user’s navigating to another activity, or the device screen’s turning off.
    When the activity moves to the resumed state, any lifecycle-aware component tied to the activity's lifecycle will receive the ON_RESUME event.
    This is where the lifecycle components can enable any functionality that needs to run while the component is visible and in the foreground,
    such as starting a camera preview.
    When an interruptive event occurs, the activity enters the Paused state, and the system invokes the onPause() callback.
    If the activity returns to the Resumed state from the Paused state, the system once again calls onResume() method.
    For this reason, you should implement onResume() to initialize components that you release during onPause(),
    and perform any other initializations that must occur each time the activity enters the Resumed state.
     */
    override fun onResume() {
        super.onResume()
        Log.i(MainActivity::class.java.name, "OnResume")
    }
    /*
    The system calls this method as the first indication that the user is leaving your activity (though it does not always mean the activity is being destroyed);
    it indicates that the activity is no longer in the foreground (though it may still be visible if the user is in multi-window mode).
    Use the onPause() method to pause or adjust operations that should not continue (or should continue in moderation) while the Activity is in the Paused state,
    and that you expect to resume shortly. There are several reasons why an activity may enter this state.
    When the activity moves to the paused state, any lifecycle-aware component tied to the activity's lifecycle will receive the ON_PAUSE event. This is where the lifecycle components can stop any functionality that does not need to run while the component is not in the foreground, such as stopping a camera preview.
    You can also use the onPause() method to release system resources, handles to sensors (like GPS),
    or any resources that may affect battery life while your activity is paused and the user does not need them.
    However, as mentioned above in the onResume() section, a Paused activity may still be fully visible if in multi-window mode.
    As such, you should consider using onStop() instead of onPause() to fully release or adjust UI-related resources and operations to better support multi-window mode.
     */
    override fun onPause() {
        super.onPause()
        Log.i(MainActivity::class.java.name, "OnPause")
    }

    /*
     When your activity is no longer visible to the user, it has entered the Stopped state, and the system invokes the onStop() callback.
     This may occur, for example, when a newly launched activity covers the entire screen.
     The system may also call onStop() when the activity has finished running, and is about to be terminated.
     When the activity moves to the stopped state, any lifecycle-aware component tied to the activity's lifecycle will receive the ON_STOP event.
     This is where the lifecycle components can stop any functionality that does not need to run while the component is not visible on the screen.
     In the onStop() method, the app should release or adjust resources that are not needed while the app is not visible to the user.
     For example, your app might pause animations or switch from fine-grained to coarse-grained location updates.
     Using onStop() instead of onPause() ensures that UI-related work continues, even when the user is viewing your activity in multi-window mode.
     You should also use onStop() to perform relatively CPU-intensive shutdown operations.
     For example, if you can't find a more opportune time to save information to a database, you might do so during onStop().
     */
    override fun onStop() {
        super.onStop()
        Log.i(MainActivity::class.java.name, "OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(MainActivity::class.java.name, "OnRestart")
    }
    /*
    onDestroy() is called before the activity is destroyed. The system invokes this callback either because:
    the activity is finishing (due to the user completely dismissing the activity or due to finish() being called on the activity), or
    the system is temporarily destroying the activity due to a configuration change (such as device rotation or multi-window mode)
    When the activity moves to the destroyed state, any lifecycle-aware component tied to the activity's lifecycle will receive the ON_DESTROY event.
    This is where the lifecycle components can clean up anything it needs to before the Activity is destroyed.
    */
    override fun onDestroy() {
        super.onDestroy()
        Log.i(MainActivity::class.java.name, "OnDestroy")
    }

    /**
     * Pour les soucis avec le save instance
     * https://developer.android.com/guide/components/activities/activity-lifecycle#instance-state
     */
}