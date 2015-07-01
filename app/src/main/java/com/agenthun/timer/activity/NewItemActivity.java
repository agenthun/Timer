package com.agenthun.timer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.agenthun.timer.R;
import com.agenthun.timer.adapter.DetailAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NewItemActivity extends AppCompatActivity {

    public static final String ITEM_INFO = "ITEM_INFO";
    @InjectView(R.id.item_name)
    EditText itemName;
    @InjectView(R.id.spinner_type)
    Spinner spinnerType;
    @InjectView(R.id.new_item_botton)
    Button newItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        ButterKnife.inject(this);

/*        getApplicationContext().getSharedPreferences(ITEM_INFO, 0).getString(ITEM_NAME, "");
        getApplicationContext().getSharedPreferences(ITEM_INFO, 0).getString(ITEM_NAME, "");*/
        Intent intent = getIntent();
        final String title = (String) intent.getStringExtra(DetailAdapter.DETAIL_TIME);
        setTitle(title);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_item_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        newItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemName.getText().toString().trim();
                String type = spinnerType.getSelectedItem().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(type)) {
                    Toast.makeText(NewItemActivity.this, "事项名称和类别不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    getApplicationContext().getSharedPreferences(ITEM_INFO, 0).edit().putString(title + "_NAME", name).commit();
                    getApplicationContext().getSharedPreferences(ITEM_INFO, 0).edit().putString(title + "_TYPE", type).commit();
                    NewItemActivity.this.finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
