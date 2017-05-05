package com.altech.acs.basicandoid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements IMenuHandler
{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* MENU */
        // Test the menu - create a Menu object instance passing in the menu handler, add elements to it, and ask the UIGen
        // to create buttons for it that can be shown on-screen.
        Menu mnuTestMenu = new Menu(null, this, "Main Menu");
        mnuTestMenu.AddMenuItem(new MenuItem(0, -1, "Purchase"));
        mnuTestMenu.AddMenuItem(new MenuItem(1,  0, "Sub Menu Item 1"));
        mnuTestMenu.AddMenuItem(new MenuItem(2,  0, "Sub Menu Item 2"));
        mnuTestMenu.AddMenuItem(new MenuItem(3, -1, "Hot Card Enquiry"));
        mnuTestMenu.AddMenuItem(new MenuItem(3, -1, "Application Configuration"));

        UIGen uiGen = new UIGen();

        //setContentView(uiGen.CreateMenuLayout(this, mnuTestMenu));

        //setContentView(uiGen.CreateAmountEntryLayout(this, "Please enter value"));

        Intent enterAmountIntent = new Intent(this, EnterAmountActivity.class);
        enterAmountIntent.putExtra("MyData", "42.00");
        startActivityForResult(enterAmountIntent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Retrieve data in the intent
        String editTextValue = data.getStringExtra("EnteredAmount");

        Toast.makeText(getBaseContext(), "The value returned from the activity is " + editTextValue, Toast.LENGTH_LONG).show();
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    @Override
    public void HandleMenuSelection(MenuItem menu, String poText, int iItemId)
    {
        Toast.makeText(getBaseContext(), "This is HandleMenuSelection. The passed menu item text is " + menu.m_szText, Toast.LENGTH_LONG).show();
    }
}
