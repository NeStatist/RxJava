package com.example.rxjavafuck;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.example.rxjavafuck.network.NetManager;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter = new MainPresenter();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // presenter.example();
       // presenter.user();
       // presenter.bang();
        presenter.maybe();

    }
}
