package com.project.rxjavaexample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.rxjavaexample.databinding.FragmentListSubtitleBinding;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class  ListSubtitleFragment extends Fragment {
    private DatabaseReference reference;
    private ListSubtitleAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApiKey("MJAio2lUUgcK3aUZ5z71Rq6s9Hy6SCTqO6pqazSM")
                .setApplicationId("com.project.rxjavaexample")
                .setDatabaseUrl("https://yes-u-du-2-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(getActivity(),options);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentListSubtitleBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_subtitle,container,false);
        reference = FirebaseDatabase.getInstance().getReference("users");
        adapter = new ListSubtitleAdapter((MainActivity) getActivity());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
        setUpObservable();
        return binding.getRoot();
    }

    @SuppressLint("CheckResult")
    private void setUpObservable(){
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.show();
        Observable<User> observable = RxFirebaseDatabase.observeSingleValueEvent(reference,
                DataSnapshotMapper.listOf(User.class)).toObservable()
                .subscribeOn(Schedulers.io()).flatMapIterable(users -> users).observeOn(AndroidSchedulers.mainThread());
        DisposableObserver<User> observer = new DisposableObserver<User>() {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull User user) {
                    adapter.addUser(user);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                dialog.dismiss();
              // Toast.makeText(getContext(), R.string.download_done, Toast.LENGTH_SHORT).show();
            }
        };
        observable.subscribe(observer);
    }

}
