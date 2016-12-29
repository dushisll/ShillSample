package sample.shillsample.ui.databinding.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sample.shillsample.R;
import sample.shillsample.databinding.UserItemBinding;
import sample.shillsample.ui.databinding.module.User;

/**
 * Created by we on 2016/12/20.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{
    private static final int USER_COUNT = 10;

    @NonNull
    private List<User> mUsers;

    public UserAdapter() {
        mUsers = new ArrayList<>(10);
        for (int i = 0; i < USER_COUNT; i ++) {
            User user = new User("shi"+i, "liang"+i);
            mUsers.add(user);
        }
    }

    public static class UserHolder extends RecyclerView.ViewHolder{
        private UserItemBinding mBinding;
        public UserHolder(View view){
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        public void bind(User user){
            mBinding.setUser(user);
        }
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bind(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
