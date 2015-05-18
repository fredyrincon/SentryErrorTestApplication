package au.com.healthconnex.sentryerrortestapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.kencochrane.raven.event.Event;
import net.kencochrane.raven.event.EventBuilder;
import net.kencochrane.raven.event.interfaces.ExceptionInterface;

import au.com.healthconnex.sentryerrortestapplication.SentryEngine.SentryManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SentryManager.init(this);

        logSimpleMessage();
        logException();
        logNotHandlerException();
    }

    //create the client
    public void createRavenClient(String dsn) {
        //Init the Sentry Manager
        SentryManager.init(this);
    }

    void logSimpleMessage() {
        // This adds a simple message to the logs
        EventBuilder eventBuilder = new EventBuilder()
                .withMessage("MainActivity:logSimpleMessage")
                        .withLevel(Event.Level.INFO)
                        .withExtra("BaseObject", SentryManager.getInstance().getSentryBaseObject(SentryManager.EXCEPTION_TYPE_MESSAGE))
                        .withLogger(MainActivity.class.getName());
        SentryManager.getInstance().getRaven().runBuilderHelpers(eventBuilder); // Optional
        SentryManager.getInstance().getRaven().sendEvent(eventBuilder.build());
    }

    void logException() {
        try {
            unsafeMethod();
        } catch (Exception e) {
            // This adds an exception to the logs
            EventBuilder eventBuilder = new EventBuilder()
                    .withMessage("Exception caught")
                    .withLevel(Event.Level.ERROR)
                    .withLogger(MainActivity.class.getName())
                    .withExtra("BaseObject", SentryManager.getInstance().getSentryBaseObject(SentryManager.EXCEPTION_TYPE_HANDLER))
                    .withSentryInterface(new ExceptionInterface(e));
            SentryManager.getInstance().getRaven().runBuilderHelpers(eventBuilder); // Optional
            SentryManager.getInstance().getRaven().sendEvent(eventBuilder.build());
        }
    }

    void logNotHandlerException() {
        String stringData = null;
        int sizeData = stringData.length();
    }


    void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call that");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
