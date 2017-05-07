package com.altech.acs.basicandoid;

import android.content.Intent;

/**
 * Created by Marius on 2017/05/06.
 */

public interface IOnActivityCompletedHandler
{
    void OnActivityCompleted(int requestCode, int resultCode, Intent data);
}
