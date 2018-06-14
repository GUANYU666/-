package com.example.hp.huanxin.presenter;
import com.example.hp.huanxin.utils.DBUtils;
import com.example.hp.huanxin.utils.ThreadUtils;
import com.example.hp.huanxin.view.ContactsView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class ContactsPresenterImp implements ContactsPresenter {
    private ContactsView mcontactsView;
    public ContactsPresenterImp(ContactsView contactsView)
    {
        this.mcontactsView=contactsView;
    }
    private List<String> contactsList = new ArrayList<>();
    public void initContacts() {
        final List<String> countacts = DBUtils.getCountacts(EMClient.getInstance().getCurrentUser());
        contactsList.clear();
        contactsList.addAll(countacts);
        mcontactsView.showContacts(contactsList);

        //网络获取
        updata();

    }

    @Override
    public void updataContact() {
        updata();
    }
    private void updata()
    {
        ThreadUtils.runOnsubThread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> allContactsFromServer = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Collections.sort(allContactsFromServer, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o1.compareTo(o2);
                        }
                    });
                    contactsList.clear();
                    contactsList.addAll(allContactsFromServer);

                    DBUtils.savaContact(EMClient.getInstance().getCurrentUser(),contactsList);


                    ThreadUtils.runOnuiThread(new Runnable() {
                        @Override
                        public void run() {
                            mcontactsView.updataContact(true);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ThreadUtils.runOnuiThread(new Runnable() {
                        @Override
                        public void run() {
                            mcontactsView.updataContact(false);
                        }
                    });
                }
            }
        });
    }
}