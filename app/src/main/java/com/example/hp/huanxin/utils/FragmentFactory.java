package com.example.hp.huanxin.utils;


import android.support.v4.app.Fragment;

import com.example.hp.huanxin.common.BaseFragment;
import com.example.hp.huanxin.view.ContactsFragment;
import com.example.hp.huanxin.view.ConversationFragment;
import com.example.hp.huanxin.view.PluginFragment;

public class FragmentFactory {

    private static ConversationFragment sConversationFragment;
    private static ContactsFragment sContactsFragment;
    private static PluginFragment sPluginFragment;
    private static ConversationFragment getsConversationFragment()
    {
        if(sConversationFragment==null)
        {
            sConversationFragment = new ConversationFragment();
        }

        return sConversationFragment;
    }
    private static ContactsFragment getsContactsFragment()
    {
        if(sContactsFragment==null)
        {
            sContactsFragment = new ContactsFragment();
        }

        return sContactsFragment;
    }
    private static PluginFragment getsPluginFragment()
    {
        if(sPluginFragment==null)
        {
            sPluginFragment = new PluginFragment() ;
        }

        return sPluginFragment;
    }

    public static BaseFragment getFragment(int index)
    {
        switch (index)
        {
            case 0:
                return getsConversationFragment();

            case 1:
                return getsContactsFragment();

            case 2:
                return getsPluginFragment();

        }
        return null;
    }

}
