package com.example.rxjavafuck

import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    private val presenter = MainPresenter()
    private val task5 = MainPresenter.Task4.Task5()

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // presenter.example();
        //  presenter.user();
        // presenter.bang();
        // task5.maybeReturn1();
        //presenter.task7();
        //presenter.task8();
        // presenter.task9();
        //presenter.task10();
      // presenter.task11();
        presenter.task6()


    }
}
