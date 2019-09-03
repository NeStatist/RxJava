package com.example.rxjavafuck;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter = new MainPresenter();
    private MainPresenter.Task4.Task5 task5 = new MainPresenter.Task4.Task5();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // presenter.example();
      //  presenter.user();
       // presenter.bang();
       // task5.maybeReturn1();
        //presenter.task7();
        presenter.task8();





    }
}
