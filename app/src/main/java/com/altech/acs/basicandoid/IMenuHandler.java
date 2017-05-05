package com.altech.acs.basicandoid;

/**
 * Created by Marius on 2017/05/05.
 */

// Interface for menu item click handlers.
public interface IMenuHandler
{
    void HandleMenuSelection(MenuItem eMenu, String poText, int iItemId);
};
