package au.com.healthconnex.sentryerrortestapplication.SentryEngine;

import android.content.Context;
import android.os.Build;

import net.kencochrane.raven.Raven;
import net.kencochrane.raven.RavenFactory;
import net.kencochrane.raven.event.Event;
import net.kencochrane.raven.event.EventBuilder;
import net.kencochrane.raven.event.interfaces.ExceptionInterface;

import au.com.healthconnex.sentryerrortestapplication.R;
import au.com.healthconnex.sentryerrortestapplication.SentryEngine.model.SentryObject;

/**
 * Created by frincon on 18/05/2015.
 */
public class SentryManager {

    static private SentryManager instance;
    static private Context context;
    private static Raven raven;

    static  public String EXCEPTION_TYPE_MESSAGE = "EXCEPTION_TYPE_MESSAGE";
    static  public String EXCEPTION_TYPE_CRASH = "EXCEPTION_TYPE_CRASH";
    static  public String EXCEPTION_TYPE_HANDLER = "EXCEPTION_TYPE_HANDLER";

    static public void init(Context ctx) {
        if (null==instance) {
            instance = new SentryManager(ctx);
        }
    }

    static public SentryManager getInstance() {
        return instance;
    }

    static public Raven getRaven() {
        return raven;
    }

    private SentryManager(Context ctx) {
        //helper = new DatabaseHelper(ctx);
        //SQLiteDatabase database = helper.getWritableDatabase();
        this.context = ctx;
        String url= "http://70c7b666264540319ba9d3eade4565bf:e68163f166be42cfbc1b54518ede1ee6@192.168.50.96/3";

        createRavenClient(url);

    }

    static public SentryObject getSentryBaseObject(String exception_type) {

        //String app_version, String os_version, String device, String user_name, String organisation_name, String exception_type
        //Get the information of the user version...
        SentryObject sentryObject = new SentryObject(context.getString(R.string.current_version_app),"", getDeviceName(), "fredyrincon", "HEALTHCONNEX",EXCEPTION_TYPE_CRASH);
        return sentryObject;
    }

    //create the client
    public void createRavenClient(String dsn) {
        raven = RavenFactory.ravenInstance(dsn);

        // It is also possible to use the DSN detection system like this
        //raven = RavenFactory.ravenInstance();

        // Advanced: To specify the ravenFactory used
        // raven = RavenFactory.ravenInstance(new Dsn(dsn), "net.kencochrane.raven.DefaultRavenFactory");
    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


}
