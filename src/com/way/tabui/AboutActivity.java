package com.way.tabui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
       
        Button submit=(Button)findViewById(R.id.button1);
        submit.setOnClickListener(new Button.OnClickListener(){
       	
       	 public void onClick(View v){
       		 Toast.makeText(getApplicationContext(), "感谢您的评分！我们会继续努力！",Toast.LENGTH_SHORT).show();
       	 }
        });
	}

}

