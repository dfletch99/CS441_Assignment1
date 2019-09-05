package com.example.assignment1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    double inch = 0.0, foot = 0.0, yard = 0.0, mile = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton calculate = findViewById(R.id.middle);
        FloatingActionButton swap = findViewById(R.id.left);
        FloatingActionButton reset = findViewById(R.id.right);
        final TextView tv1 = findViewById(R.id.tv1), tv2 = findViewById(R.id.tv2), tv3 = findViewById(R.id.tv3), tv4 = findViewById(R.id.tv4);
        final EditText et1 = findViewById(R.id.et1), et2 = findViewById(R.id.et2), et3 = findViewById(R.id.et3), et4 = findViewById(R.id.et4);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.getText().clear();
                et2.getText().clear();
                et3.getText().clear();
                et4.getText().clear();
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int emptyCounter = 0;
                if(TextUtils.isEmpty(et1.getText())) emptyCounter++;
                else inch = Double.parseDouble(et1.getText().toString());
                if(TextUtils.isEmpty(et2.getText())) emptyCounter++;
                else foot = Double.parseDouble(et2.getText().toString());
                if(TextUtils.isEmpty(et3.getText())) emptyCounter++;
                else yard = Double.parseDouble(et3.getText().toString());
                if(TextUtils.isEmpty(et4.getText())) emptyCounter++;
                else mile = Double.parseDouble(et4.getText().toString());

                if(emptyCounter != 3) throw new IllegalArgumentException("Exactly one box must have a value");

                if(!TextUtils.isEmpty(et1.getText())) {
                    foot = inch / 12;
                    yard = foot / 3;
                    mile = foot / 5280;
                }
                else if(!TextUtils.isEmpty(et2.getText())){
                    inch = foot * 12;
                    yard = foot / 3;
                    mile = foot / 5280;
                }
                else if(!TextUtils.isEmpty(et3.getText())){
                    inch = yard * 36;
                    foot = yard * 3;
                    mile = foot / 5280;
                }
                else{
                    foot = mile * 5280;
                    yard = foot / 3;
                    inch = foot * 12;
                }
                et1.setText(Double.toString(inch));
                et2.setText(Double.toString(foot));
                et3.setText(Double.toString(yard));
                et4.setText(Double.toString(mile));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
