package com.caramelheaven.lennach.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.caramelheaven.lennach.R
import com.caramelheaven.lennach.ui.board.BoardFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temp = intent.getStringExtra("ITEM");

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, BoardFragment.newInstance(temp))
                    .commit()
        }
    }
}
