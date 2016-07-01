package com.multicampus.androidlab.ch17;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.multicampus.androidlab.R;

public class IntentTestActivity extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch17_activity_intent_test);

        setView();
    }

    private void setView(){
        btn = (Button)findViewById(R.id.btn);
    }

    public void makeIntent(View view){
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));


        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://010-111-2222"));
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        startActivity(intent);
    }


}
