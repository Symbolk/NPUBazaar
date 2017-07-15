
package com.way.tabui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SearchActivity extends Activity {
    private ArrayAdapter<String> adapter_history;
     private AutoCompleteTextView auto;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sea);
        auto = (AutoCompleteTextView) findViewById(R.id.auto);
        auto.setThreshold(1);
        ok = (Button) findViewById(R.id.save);
        SharedPreferences sp = getSharedPreferences("history_strs", 0);
        String save_history = sp.getString("history", "");
        String[] hisArrays = save_history.split(",");
        adapter_history = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, hisArrays);
        if (hisArrays.length > 5) {
            String[] newArrays = new String[50];
            System.arraycopy(hisArrays, 0, newArrays, 0, 50);
            adapter_history = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, newArrays);
        }
        auto.setAdapter(adapter_history);
        ok.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Save();
            }
        });

    }

    private void Save() {

        String text = auto.getText().toString();
        SharedPreferences sp = getSharedPreferences("history_strs", 0);
        String save_Str = sp.getString("history", "");
        String[] hisArrays = save_Str.split(",");
        for(int i=0;i<hisArrays.length;i++)
        {
            if(hisArrays[i].equals(text))
            {
                return;
            }
        }
        StringBuilder sb = new StringBuilder(save_Str);
        sb.append(text + ",");
        sp.edit().putString("history", sb.toString()).commit();
        Toast.makeText(SearchActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
    }

}

