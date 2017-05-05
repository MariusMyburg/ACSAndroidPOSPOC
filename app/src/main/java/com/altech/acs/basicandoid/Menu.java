package com.altech.acs.basicandoid;

/**
 * Created by Marius on 2017/05/05.
 */

import java.util.ArrayList;

public class Menu
{
    Menu(Menu originatingMenu, IMenuHandler menuHandler, String strMenuHeader)
    {
        m_OriginatingMenu = originatingMenu;
        SetMenuHandler(menuHandler);
        m_strMenuHeader = strMenuHeader;

        m_arrMenuItems = new ArrayList<MenuItem>();
        m_iCurrentParentMenuID = -1;
    }

    boolean AddMenuItem(MenuItem newMenu)
    {
        m_arrMenuItems.add(newMenu);
        return true;
    }

    boolean SetMenuHandler(IMenuHandler menuHandler)
    {
        m_MenuHandler = menuHandler;
        return true;
    }

    String GetMenuHeader()
    {
        return m_strMenuHeader;
    }

    IMenuHandler GetMenuHandler()
    {
        return m_MenuHandler;
    }

    ArrayList<MenuItem> GetMenuItems()
    {
        return m_arrMenuItems;
    }

    void SetCurrentParentMenuID(int newCurrentParentMenuID)
    {
        m_iCurrentParentMenuID = newCurrentParentMenuID;
    }

    int GetCurrentParentMenuID()
    {
        return m_iCurrentParentMenuID;
    }

    Menu GetOriginatingMenu()
    {
        return m_OriginatingMenu;
    }



    private String					m_strMenuHeader;
    private ArrayList<MenuItem>	m_arrMenuItems;					// The menu items.
    private IMenuHandler			m_MenuHandler;					// The entity that will handle clicks of non-parent menu items.
    private int						m_iCurrentParentMenuID = -1;	// We track the current parent menu ID.

    // This might be useful when creating a new Menu object instance based on this menu.sub-menu
    private Menu					m_OriginatingMenu;
}

