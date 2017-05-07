package com.altech.acs.basicandoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_EnteredAmount;
import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_InputAmount;
import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_InputPrompt;
import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterPINActivity_InputPrompt;

public class MainActivity extends Activity implements IMenuHandler, IOnActivityCompletedHandler
{
    final int MENU_ITEM_ID_PURCHASE             = 0;
    final int MENU_ITEM_ID_MANUAL_TRANSACTION   = 1;
    final int MENU_ITEM_ID_GET_PIN              = 2;

    final int ENTER_AMOUNT_REQUEST_CODE         = 100;
    final int ENTER_PIN_REQUEST_CODE            = 101;


    private Map<Number, IOnActivityCompletedHandler> m_mapUserHandlerByRequestCode = new HashMap<Number, IOnActivityCompletedHandler>();

    // Used to load the 'native-lib' library on application startup.
    static
    {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Manager.getInstance().setMainActivity(this);

        /* MENU */
        // Test the menu - create a Menu object instance passing in the menu handler, add elements to it, and ask the UIGen
        // to create buttons for it that can be shown on-screen.
        Menu mnuTestMenu = new Menu(null, this, "Main Menu");
        mnuTestMenu.AddMenuItem(new MenuItem(MENU_ITEM_ID_PURCHASE, -1, "Purchase"));
        mnuTestMenu.AddMenuItem(new MenuItem(MENU_ITEM_ID_MANUAL_TRANSACTION,  0, "Manual Transaction"));
        mnuTestMenu.AddMenuItem(new MenuItem(MENU_ITEM_ID_GET_PIN,  0, "Get PIN"));
        mnuTestMenu.AddMenuItem(new MenuItem(3, -1, "Hot Card Enquiry"));
        mnuTestMenu.AddMenuItem(new MenuItem(3, -1, "Application Configuration"));

        UIGen uiGen = new UIGen();

        setContentView(uiGen.CreateMenuLayout(this, mnuTestMenu));
    }




    /* The POC 'API' */
    boolean GetUserInput_Amount(String strPrompt, String strDefaultValue, IOnActivityCompletedHandler resultHandler)
    {
        Intent enterAmountIntent = new Intent(this, EnterAmountActivity.class);
        m_mapUserHandlerByRequestCode.put(ENTER_AMOUNT_REQUEST_CODE, resultHandler);
        enterAmountIntent.putExtra(EnterAmountActivity_InputPrompt, strPrompt);
        enterAmountIntent.putExtra(EnterAmountActivity_InputAmount, strDefaultValue);
        startActivityForResult(enterAmountIntent, ENTER_AMOUNT_REQUEST_CODE);
        return true;
    }

    boolean GetUserInput_PIN(String strPrompt, IOnActivityCompletedHandler resultHandler)
    {
        Intent enterPINIntent = new Intent(this, EnterPINActivity.class);
        m_mapUserHandlerByRequestCode.put(ENTER_PIN_REQUEST_CODE, resultHandler);
        enterPINIntent.putExtra(EnterPINActivity_InputPrompt, strPrompt);
        startActivityForResult(enterPINIntent, ENTER_PIN_REQUEST_CODE);
        return true;
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

        if (iItemId == MENU_ITEM_ID_MANUAL_TRANSACTION)
        {
            GetUserInput_Amount("Please enter amount", "", this);
        }else if (iItemId == MENU_ITEM_ID_GET_PIN)
        {
            GetUserInput_PIN("Please enter PIN", this);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Call the handler that the user specified for this requestCode.
        Number num = requestCode;

        if (m_mapUserHandlerByRequestCode.containsKey(num))
        {
            m_mapUserHandlerByRequestCode.get(num).OnActivityCompleted(requestCode, resultCode, data);
            m_mapUserHandlerByRequestCode.remove(num);
        }

    }

    @Override
    public void OnActivityCompleted(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode) {
            case ENTER_AMOUNT_REQUEST_CODE: {
                //Retrieve data in the intent
                String editTextValue = data.getStringExtra(EnterAmountActivity_EnteredAmount);
                Toast.makeText(getBaseContext(), "The entered amount is " + editTextValue, Toast.LENGTH_LONG).show();
                break;
            }

            case ENTER_PIN_REQUEST_CODE: {
                String editTextValue = data.getStringExtra("EnteredPIN");
                Toast.makeText(getBaseContext(), "The entered PIN is " + editTextValue, Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
