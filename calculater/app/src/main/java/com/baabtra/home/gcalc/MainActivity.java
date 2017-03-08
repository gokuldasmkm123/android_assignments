package com.baabtra.home.gcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView value1;
    TextView value2;
    TextView Result;
    Button btnAdd;
    Button btndiv;
    Button btnmul;
    double num1,num2,sum,mul,div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value1=(TextView)findViewById(R.id.fvalue);
        value2=(TextView)findViewById(R.id.svalue);
        btnAdd=(Button)findViewById(R.id.sbtn);
        btndiv=(Button)findViewById(R.id.dbtn);
        btnmul=(Button)findViewById(R.id.pbtn);
        Result=(TextView)findViewById(R.id.txtResult);


        btnAdd.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                num1 = Double.parseDouble(value1.getText().toString());
                num2 = Double.parseDouble(value2.getText().toString());
                sum = num1 + num2;
                mul= num1 * num2;
                div= num1/num2;
                Result.setText(Double.toString(sum));

            }
        });
        btndiv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                num1 = Double.parseDouble(value1.getText().toString());
                num2 = Double.parseDouble(value2.getText().toString());
                div= num1/num2;
                Result.setText(Double.toString(div));
            }
        });
        btnmul.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                num1 = Double.parseDouble(value1.getText().toString());
                num2 = Double.parseDouble(value2.getText().toString());

                mul= num1 * num2;


                Result.setText(Double.toString(mul));

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
