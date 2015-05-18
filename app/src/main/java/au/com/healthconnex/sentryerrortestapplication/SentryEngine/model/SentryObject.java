package au.com.healthconnex.sentryerrortestapplication.SentryEngine.model;

import com.google.gson.Gson;

/**
 * Created by frincon on 18/05/2015.
 */
public class SentryObject{
    private String app_version;
    private String os_version;
    private String device;
    private String user_name;
    private String organisation_name;
    private String exception_type;

    public SentryObject(String app_version, String os_version, String device, String user_name, String organisation_name, String exception_type) {
        this.app_version = app_version;
        this.os_version = os_version;
        this.device = device;
        this.user_name = user_name;
        this.organisation_name = organisation_name;
        this.exception_type = exception_type;
    }

    public String getException_type() {
        return exception_type;
    }

    public void setException_type(String exception_type) {
        this.exception_type = exception_type;
    }

    public String getOrganisation_name() {
        return organisation_name;
    }

    public void setOrganisation_name(String organisation_name) {
        this.organisation_name = organisation_name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }



}
