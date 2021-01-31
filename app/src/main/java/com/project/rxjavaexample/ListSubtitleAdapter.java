package com.project.rxjavaexample;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.project.rxjavaexample.databinding.ItemUserBinding;

import java.util.ArrayList;
import java.util.List;

public class ListSubtitleAdapter extends Adapter<ListSubtitleAdapter.SubtitleHolder> {
    private List<User> users;
    private MainActivity activity;
    private Callback callback;

    public ListSubtitleAdapter(MainActivity activity){
        users = new ArrayList<>();
        this.activity = activity;
        callback = (Callback) activity;
    }
    @NonNull
    @Override
    public SubtitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_user,parent,false);
        return new SubtitleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubtitleHolder holder, int position) {
        holder.onBind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addUser(User user){
        users.add(user);
        notifyDataSetChanged();
    }

    public class SubtitleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ItemUserBinding binding;
        private User user;
        public SubtitleHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        public void onBind(User user){
            binding.setSubtitle(user.getSubtitle());
            this.user = user;
        }


        @Override
        public void onClick(View v) {
            UserInformationViewModel viewModel = ViewModelProviders.of(activity).get(UserInformationViewModel.class);
            viewModel.putMutableLiveData(user);
            callback.openUserFragment();
        }
    }

    public interface Callback{
        void openUserFragment();
    }
}
