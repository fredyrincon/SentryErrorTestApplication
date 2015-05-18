package au.com.healthconnex.sentryerrortestapplication.main;

import android.app.Application;

import net.kencochrane.raven.event.Event;
import net.kencochrane.raven.event.EventBuilder;
import net.kencochrane.raven.event.interfaces.ExceptionInterface;

import au.com.healthconnex.sentryerrortestapplication.SentryEngine.SentryManager;

/**
 * Created by frincon on 18/05/2015.
 */
public class ApplicationObject extends Application
{
    public void onCreate ()
    {
        // Setup handler for uncaught exceptions.
        Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException (Thread thread, Throwable e)
            {
                handleUncaughtException (thread, e);
            }
        });
    }

    public void handleUncaughtException (Thread thread, Throwable e)
    {
        SentryManager.init(this);

        EventBuilder eventBuilder = new EventBuilder()
                .withMessage("Exception caught on the main app")
                .withLevel(Event.Level.ERROR)
                        //.withLogger(MainActivity.class.getName())
                .withExtra("BaseObject", SentryManager.getInstance().getSentryBaseObject(SentryManager.EXCEPTION_TYPE_CRASH).toString())
                .withSentryInterface(new ExceptionInterface(e));
        SentryManager.getInstance().getRaven().runBuilderHelpers(eventBuilder); // Optional
        SentryManager.getInstance().getRaven().sendEvent(eventBuilder.build());

        System.exit(1); // kill off the crashed app
    }
}
