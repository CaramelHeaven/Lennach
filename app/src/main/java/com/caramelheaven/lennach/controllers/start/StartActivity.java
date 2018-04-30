package com.caramelheaven.lennach.controllers.start;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.caramelheaven.lennach.R;
import com.caramelheaven.lennach.controllers.BaseActivity;
import com.caramelheaven.lennach.controllers.board.BoardFragment;
import com.caramelheaven.lennach.databinding.ActivityStartBinding;


public class StartActivity extends BaseActivity {

    private static final String TAG = StartActivity.class.getSimpleName();
    ActivityStartBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);

        setSupportActionBar(binding.toolbar);
        displayHomeAsUpEnabled();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, BoardFragment.newInstance())
                .commit();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "clicked on fab", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
