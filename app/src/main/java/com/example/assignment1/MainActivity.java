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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean isImperial = true;
    boolean inDegreeMode = false;
    boolean cats = false;

    double tiny = 0.0, small = 0.0, medium = 0.0, large = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv1 = findViewById(R.id.tv1), tv2 = findViewById(R.id.tv2), tv3 = findViewById(R.id.tv3), tv4 = findViewById(R.id.tv4);
        final EditText et1 = findViewById(R.id.et1), et2 = findViewById(R.id.et2), et3 = findViewById(R.id.et3), et4 = findViewById(R.id.et4);
        final ImageView img = (ImageView)findViewById(R.id.catpicture);
        final ImageView img2 = (ImageView)findViewById(R.id.catpicture2);
        final ImageView img3 = (ImageView)findViewById(R.id.catpicture3);
        final ImageView img4 = (ImageView)findViewById(R.id.catpicture4);

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
                else tiny = Double.parseDouble(et1.getText().toString());
                if(TextUtils.isEmpty(et2.getText())) emptyCounter++;
                else small = Double.parseDouble(et2.getText().toString());
                if(TextUtils.isEmpty(et3.getText())) emptyCounter++;
                else medium = Double.parseDouble(et3.getText().toString());
                if(TextUtils.isEmpty(et4.getText())) emptyCounter++;
                else large = Double.parseDouble(et4.getText().toString());

                if(emptyCounter != 3){
                    if(emptyCounter != 4) Snackbar.make(view, "Please reset values to enter another conversion", Snackbar.LENGTH_LONG)
                                          .setAction("Action", null).show();
                    else                  Snackbar.make(view, "Please input a single value to be converted", Snackbar.LENGTH_LONG)
                                          .setAction("Action", null).show();
                    validInput = false;
                }
                if(validInput) {
                    if (!TextUtils.isEmpty(et1.getText())) {
                        if(!inDegreeMode) {
                            if (isImperial) {
                                small = tiny / 12;
                                medium = small / 3;
                                large = small / 5280;
                            } else {
                                small = tiny / 100;
                                medium = small / 1000;
                                large = medium / 299792;
                            }
                        }
                        else{
                            small = (tiny - 32) * (5.0/9.0);
                            medium = small + 273.15;
                            large = (int)(100*Math.random() + 10);
                        }
                    } else if (!TextUtils.isEmpty(et2.getText())) {
                        if(!inDegreeMode) {
                            if (isImperial) {
                                tiny = small * 12;
                                medium = small / 3;
                                large = small / 5280;
                            } else {
                                tiny = small * 100;
                                medium = small / 1000;
                                large = medium / 299792;
                            }
                        }
                        else{
                            tiny = small * (5.0/9.0) + 32;
                            medium = small + 273.15;
                            large = (int)(100*Math.random()+10);
                        }
                    } else if (!TextUtils.isEmpty(et3.getText())) {
                        if(!inDegreeMode) {
                            if (isImperial) {
                                small = medium * 3;
                                tiny = small * 12;
                                large = small / 5280;
                            } else {
                                small = medium * 1000;
                                tiny = small * 100;
                                large = medium / 299792;
                            }
                        }
                        else{
                            small = medium - 273.15;
                            tiny = small * (5.0/9.0) + 32;
                            large = (int)(100*Math.random()+10);
                        }
                    } else {
                        if(!inDegreeMode) {
                            if (isImperial) {
                                small = large * 5280;
                                medium = small / 3;
                                tiny = small * 12;
                            } else {
                                medium = large * 299792;
                                small = medium * 1000;
                                tiny = small * 100;
                            }
                        }
                        else{
                            cats = true;
                            et1.setText("m a s h e d p o t a t o");
                            et2.setText("C A T S");
                            et3.setText("C A T S");
                            et4.setText("C A T S");
                            img.setVisibility(View.VISIBLE);
                            img2.setVisibility(View.VISIBLE);
                            img3.setVisibility(View.VISIBLE);
                            img4.setVisibility(View.VISIBLE);
                        }
                    }
                    if(!cats) {
                        et1.setText(String.format(Locale.US, "%.2f", tiny));
                        et2.setText(String.format(Locale.US, "%.2f", small));
                        et3.setText(String.format(Locale.US, "%.2f", medium));
                        if (isImperial || inDegreeMode) {
                            et4.setText(String.format(Locale.US, "%.2f", large));
                        } else {
                            et4.setText(String.format(Locale.US, "%.9f", large));
                        }
                    }
                    cats = false;
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
        if(s.equals("metric")){
            tv1.setText("Centimeters");
            tv2.setText("Meters");
            tv3.setText("Kilometers");
            tv4.setText("Light-Seconds");
            et1.setHint("Enter a value in centimeters");
            et2.setHint("Enter a value in meters");
            et3.setHint("Enter a value in kilometers");
            et4.setHint("Enter a value in light-seconds");
            isImperial = false;
            inDegreeMode = false;
        }
        else if(s.equals("imperial")){
            tv1.setText("Inches");
            tv2.setText("Feet");
            tv3.setText("Yards");
            tv4.setText("Miles");
            et1.setHint("Enter a value in inches");
            et2.setHint("Enter a value in feet");
            et3.setHint("Enter a value in yards");
            et4.setHint("Enter a value in miles");
            isImperial = true;
            inDegreeMode = false;
        }
        else{
            tv1.setText("Fahrenheit");
            tv2.setText("Celsius");
            tv3.setText("Kelvin");
            tv4.setText("Cats");
            et1.setHint("Enter a temperature in F");
            et2.setHint("Enter a temperature in C");
            et3.setHint("Enter a temperature in K");
            et4.setHint("Enter a number of Cats");
            inDegreeMode = true;
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
        if(id == R.id.swap_degrees){
            setSystem("degree", (TextView)(findViewById(R.id.tv1)), (TextView)findViewById(R.id.tv2), (TextView)findViewById(R.id.tv3), (TextView)findViewById(R.id.tv4), (EditText)findViewById(R.id.et1), (EditText)findViewById(R.id.et2), (EditText)findViewById(R.id.et3), (EditText)findViewById(R.id.et4));
            ((EditText) findViewById(R.id.et1)).getText().clear();
            ((EditText) findViewById(R.id.et2)).getText().clear();
            ((EditText) findViewById(R.id.et3)).getText().clear();
            ((EditText) findViewById(R.id.et4)).getText().clear();
        }

        return super.onOptionsItemSelected(item);
    }
}
