package com.altech.acs.basicandoid;

/**
 * Created by Marius on 2017/05/05.
 */

public class MenuItem
{
    public MenuItem(int id, int parentid, String szText)
    {
        this.m_id = id;
        this.m_parentid = parentid;
        this.m_szText = szText;
    }


    int m_id;
    int m_parentid;
    String m_szText;  // Item string to display
};
