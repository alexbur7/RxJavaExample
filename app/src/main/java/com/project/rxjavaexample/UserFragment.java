package com.project.rxjavaexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.project.rxjavaexample.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {
    //String url = "https://s3.amazonaws.com/koya-dev-videos/kindness/8da807aa-1e1e-413d-bf9b-5bb084646593/medialibrary/9456621508/videos/1eb78337-d569-41bd-95ad-153d9098de03.png";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentUserBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user,container,false);
        UserInformationViewModel viewModel = ViewModelProviders.of(getActivity()).get(UserInformationViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, user -> {
                Glide.with(getActivity()).load(user.getUrlImage()).into(binding.image);
                binding.subtitleText.setText(user.getSubtitle());
                binding.text.setText(user.getText());
        }
        );
        return binding.getRoot();
    }
}
