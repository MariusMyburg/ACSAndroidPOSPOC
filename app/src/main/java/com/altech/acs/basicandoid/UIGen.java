package com.altech.acs.basicandoid;

/**
 * Created by Marius on 2017/05/05.
 */

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

// Generates UI elements from passed parameters/structures.
public class UIGen
{
    // This method takes in an array of S_AcsMenu and builds a LinearLayout container with a button for each S_AcsMenu.
    public android.widget.LinearLayout CreateMenuLayout(final MainActivity context, final Menu menu)
    {
        android.widget.LinearLayout result = new android.widget.LinearLayout(context);
        result.setOrientation(android.widget.LinearLayout.VERTICAL);

        android.widget.TextView tv = new android.widget.TextView(context);
        tv.setTextAlignment(android.widget.TextView.TEXT_ALIGNMENT_CENTER);
        tv.setText(menu.GetMenuHeader());
        result.addView(tv);

        for (int i = 0; i < menu.GetMenuItems().size(); i++)
        {
            if (menu.GetMenuItems().get(i).m_parentid != menu.GetCurrentParentMenuID())
            {
                continue;
            }

            android.widget.Button btn = new android.widget.Button(context);
            btn.setId(menu.GetMenuItems().get(i).m_id);

            btn.setText(menu.GetMenuItems().get(i).m_szText);
            btn.setHeight(100);

            final int finali = i;

            // Set up event handler. All the event handler does is call the HandleMenuSelection
            // method of the menu's IMenuHandler member, IF this menu item does not contain
            // any sub-menus.
            btn.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    int clickedMenuItemID = menu.GetMenuItems().get(finali).m_id;
                    boolean bMustDrillDown = false;
                    ArrayList<MenuItem> arrSubMenus = new ArrayList<MenuItem>();

                    // Set the current parent menu id to the clicked item's parent menu id.
                    menu.SetCurrentParentMenuID(menu.GetMenuItems().get(finali).m_parentid);

                    // We must drill down if the clicked menu item is a parent of child menu items.
                    for (int iMenuItem=0; iMenuItem < menu.GetMenuItems().size(); iMenuItem++)
                    {
                        if (menu.GetMenuItems().get(iMenuItem).m_parentid == clickedMenuItemID)
                        {
                            bMustDrillDown = true;
                            arrSubMenus.add(menu.GetMenuItems().get(iMenuItem));
                        }
                    }

                    // If the clicked menu item is not a parent and we therefore do not have to drill down,
                    // we just call the handle method.
                    if (!bMustDrillDown)
                    {
                        menu.GetMenuHandler().HandleMenuSelection(
                                menu.GetMenuItems().get(finali),
                                menu.GetMenuItems().get(finali).m_szText,
                                menu.GetMenuItems().get(finali).m_id);
                    }else
                    {
                        // If we are here we need to drill down. This effectively means showing a new menu which
                        // consists of the sub menu items of the clicked menu item. So, we construct a new Menu.
                        Menu subMenu = new Menu(menu, menu.GetMenuHandler(), menu.GetMenuHeader() + " submenu");
                        subMenu.SetCurrentParentMenuID(clickedMenuItemID);

                        // All all of the relevant menu items to this menu.
                        for (int iSubMenuItem = 0; iSubMenuItem < arrSubMenus.size(); iSubMenuItem++)
                        {
                            subMenu.AddMenuItem(arrSubMenus.get(iSubMenuItem));
                        }

                        context.setContentView(CreateMenuLayout(context, subMenu));
                    }
                }
            });

            android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);

            result.addView(btn, params);
        }

        // Add a Back button if applicable.
        if (menu.GetOriginatingMenu() != null)
        {
            android.widget.Button backButton = new android.widget.Button(context);
            backButton.setId(-100);
            backButton.setText("Back");
            backButton.setHeight(50);

            // Handler for the BACK button.
            backButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    // Simply navigate to the current menu's OriginatingMenu.
                    context.setContentView(CreateMenuLayout(context, menu.GetOriginatingMenu()));
                }
            });

            android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                    android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);

            result.addView(backButton, params);
        }

        return result;
    }



    public android.widget.LinearLayout CreateAmountEntryLayout(final MainActivity context, final String prompt)
    {
        android.widget.LinearLayout result = new android.widget.LinearLayout(context);
        result.setOrientation(android.widget.LinearLayout.VERTICAL);

        android.widget.TextView tv = new android.widget.TextView(context);
        tv.setTextAlignment(android.widget.TextView.TEXT_ALIGNMENT_CENTER);
        tv.setText(prompt);
        result.addView(tv);

        android.widget.EditText editText = new android.widget.EditText(context);
        result.addView(editText);

        return result;
    }
}

