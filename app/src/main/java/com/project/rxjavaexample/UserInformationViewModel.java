package com.project.rxjavaexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserInformationViewModel extends ViewModel {

    private final MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public LiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void putMutableLiveData(User user){
        userMutableLiveData.setValue(user);
    }
}
