package com.example.hp.huanxin.presenter;
import com.example.hp.huanxin.model.User;
import com.example.hp.huanxin.utils.DBUtils;
import com.example.hp.huanxin.utils.ThreadUtils;
import com.example.hp.huanxin.view.AddFriendView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
public class AddFriendPresenterImp implements AddFriendPresenter {
    private AddFriendView mAddFriendView;
    public AddFriendPresenterImp(AddFriendView addFriendView)
    {
      this.mAddFriendView = addFriendView;
    }
    @Override
    public void seachFriend(String keyword) {
        //去bmob服务器中搜索
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereContains("username",keyword);
        query.addWhereNotEqualTo("username", EMClient.getInstance().getCurrentUser());
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e==null&&list.size()>0)
                {
                    List<String> countacts = DBUtils.getCountacts(EMClient.getInstance().getCurrentUser());
                    mAddFriendView.afterSerch(list,countacts,true);
                }
                else
                {
                    mAddFriendView.afterSerch(null,null,false);
                }
            }
        });
    }
    @Override
    public void addContact(final String username) {

        ThreadUtils.runOnsubThread(new Runnable() {
            @Override
            public void run() {
                //添加好友
                try {
                    EMClient.getInstance().contactManager().addContact(username,"想加你好友");
                afterAdd(true,null,username);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    afterAdd(false,e.getMessage(),username );
                }
            }
        });
    }
    private void afterAdd(final boolean success, final String msg, final String username)
    {
        ThreadUtils.runOnuiThread(new Runnable() {
            @Override
            public void run() {
                mAddFriendView.afterAddCoutact(success,msg,username);
            }
        });
    }
}