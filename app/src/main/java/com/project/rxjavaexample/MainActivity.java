package com.project.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListSubtitleAdapter.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        if (fragment==null){
            fragment = new ListSubtitleFragment();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment, fragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void openUserFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new UserFragment())
                .addToBackStack(null).commit();
    }
}