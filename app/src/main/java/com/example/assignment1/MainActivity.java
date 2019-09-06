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

import java.util.Locale;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean isImperial = true;

    double inch = 0.0, foot = 0.0, yard = 0.0, mile = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv1 = findViewById(R.id.tv1), tv2 = findViewById(R.id.tv2), tv3 = findViewById(R.id.tv3), tv4 = findViewById(R.id.tv4);
        final EditText et1 = findViewById(R.id.et1), et2 = findViewById(R.id.et2), et3 = findViewById(R.id.et3), et4 = findViewById(R.id.et4);

        setSystem("imperial", tv1, tv2, tv3, tv4, et1, et2, et3, et4);

        FloatingActionButton calculate = findViewById(R.id.middle);
        FloatingActionButton reset = findViewById(R.id.right);


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
                boolean validInput = true;
                int emptyCounter = 0;
                if(TextUtils.isEmpty(et1.getText())) emptyCounter++;
                else inch = Double.parseDouble(et1.getText().toString());
                if(TextUtils.isEmpty(et2.getText())) emptyCounter++;
                else foot = Double.parseDouble(et2.getText().toString());
                if(TextUtils.isEmpty(et3.getText())) emptyCounter++;
                else yard = Double.parseDouble(et3.getText().toString());
                if(TextUtils.isEmpty(et4.getText())) emptyCounter++;
                else mile = Double.parseDouble(et4.getText().toString());

                if(emptyCounter != 3){
                    if(emptyCounter != 4) Snackbar.make(view, "Please reset values to enter another conversion", Snackbar.LENGTH_LONG)
                                          .setAction("Action", null).show();
                    else                  Snackbar.make(view, "Please input a single value to be converted", Snackbar.LENGTH_LONG)
                                          .setAction("Action", null).show();
                    validInput = false;
                }
                if(validInput) {
                    if (!TextUtils.isEmpty(et1.getText())) {
                        if(isImperial) {
                            foot = inch / 12;
                            yard = foot / 3;
                            mile = foot / 5280;
                        }
                        else{
                            foot = inch / 100;
                            yard = foot / 1000;
                            mile = yard / 299792;
                        }
                    } else if (!TextUtils.isEmpty(et2.getText())) {
                        if(isImperial) {
                            inch = foot * 12;
                            yard = foot / 3;
                            mile = foot / 5280;
                        }
                        else{
                            inch = foot * 100;
                            yard = foot / 1000;
                            mile = yard / 299792;
                        }
                    } else if (!TextUtils.isEmpty(et3.getText())) {
                        if(isImperial) {
                            foot = yard * 3;
                            inch = foot * 12;
                            mile = foot / 5280;
                        }
                        else{
                            foot = yard * 1000;
                            inch = foot * 100;
                            mile = yard / 299792;
                        }
                    } else {
                        if(isImperial) {
                            foot = mile * 5280;
                            yard = foot / 3;
                            inch = foot * 12;
                        }
                        else{
                            yard = mile * 299792;
                            foot = yard * 1000;
                            inch = foot * 100;
                        }
                    }
                    et1.setText(String.format(Locale.US, "%.2f", inch));
                    et2.setText(String.format(Locale.US, "%.2f", foot));
                    et3.setText(String.format(Locale.US, "%.2f", yard));
                    if(isImperial) {
                        et4.setText(String.format(Locale.US, "%.2f", mile));
                    }
                    else {
                        et4.setText(String.format(Locale.US, "%.9f", mile));
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setSystem(String s, TextView tv1, TextView tv2, TextView tv3, TextView tv4, EditText et1, EditText et2, EditText et3, EditText et4){
        if(!s.equals("imperial")){
            tv1.setText("Centimeters");
            tv2.setText("Meters");
            tv3.setText("Kilometers");
            tv4.setText("Light-Seconds");
            et1.setHint("Enter a value in centimeters");
            et2.setHint("Enter a value in meters");
            et3.setHint("Enter a value in kilometers");
            et4.setHint("Enter a value in light-seconds");
            isImperial = false;
        }
        else{
            tv1.setText("Inches");
            tv2.setText("Feet");
            tv3.setText("Yards");
            tv4.setText("Miles");
            et1.setHint("Enter a value in inches");
            et2.setHint("Enter a value in feet");
            et3.setHint("Enter a value in yards");
            et4.setHint("Enter a value in miles");
            isImperial = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if(isImperial){
                setSystem("metric", (TextView)(findViewById(R.id.tv1)), (TextView)findViewById(R.id.tv2), (TextView)findViewById(R.id.tv3), (TextView)findViewById(R.id.tv4), (EditText)findViewById(R.id.et1), (EditText)findViewById(R.id.et2), (EditText)findViewById(R.id.et3), (EditText)findViewById(R.id.et4));
            }
            else{
                setSystem("imperial", (TextView)(findViewById(R.id.tv1)), (TextView)findViewById(R.id.tv2), (TextView)findViewById(R.id.tv3), (TextView)findViewById(R.id.tv4), (EditText)findViewById(R.id.et1), (EditText)findViewById(R.id.et2), (EditText)findViewById(R.id.et3), (EditText)findViewById(R.id.et4));
            }
            ((EditText) findViewById(R.id.et1)).getText().clear();
            ((EditText) findViewById(R.id.et2)).getText().clear();
            ((EditText) findViewById(R.id.et3)).getText().clear();
            ((EditText) findViewById(R.id.et4)).getText().clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
