package com.altech.acs.basicandoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Marius on 2017/05/06.
 */

public class Manager
{
    private static final Manager ourInstance = new Manager();
    private MainActivity mainActivity;

    public static Manager getInstance()
    {
        return ourInstance;
    }

    public void setMainActivity(MainActivity activity)
    {
        mainActivity = activity;
    }

    private Manager()
    {
    }



}
