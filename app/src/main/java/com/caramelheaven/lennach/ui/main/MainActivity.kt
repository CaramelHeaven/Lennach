package com.caramelheaven.lennach.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.caramelheaven.lennach.R
import com.caramelheaven.lennach.ui.board.BoardFragment
import com.caramelheaven.lennach.ui.main.navigation.BoardNavigationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, BoardNavigationFragment.newInstance())
                    .commit()
        }
    }
}
