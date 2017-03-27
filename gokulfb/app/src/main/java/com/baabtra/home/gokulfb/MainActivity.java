package com.baabtra.home.gokulfb;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {
    EditText pass,uname;
    Button bt1,ne;
    String password;
    String username;
    TextView msgg;
    final mysqlitehelper mydb=new mysqlitehelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar action=getActionBar();
        action.setDisplayOptions(action.DISPLAY_SHOW_CUSTOM);
        action.setDisplayShowCustomEnabled(true);
        action.setCustomView(R.layout.customtitlebar);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.activity_main);
//        View view =getSupportActionBar().getCustomView();
//

        uname= (EditText)findViewById(R.id.eami_id);
        pass=(EditText)findViewById(R.id.pwd);
        bt1=(Button)findViewById(R.id.og_btn);
        msgg=(TextView)findViewById(R.id.msg);

        ne=(Button)findViewById(R.id.newacc);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String unames=uname.getText().toString();
                String passwords=pass.getText().toString();
                Cursor res=mydb.getAllData(unames);
                if(res.getCount()==0)
                {
                    msgg.setVisibility(View.VISIBLE);
                    msgg.setText("The Email address that you've entered doesn't match any account  Sign up for an account");
                }
                StringBuffer strc=new StringBuffer();
                while ((res.moveToNext())){
                   // strc.append("Firstname:"+res.getString(0)+"\n");
                    //strc.append("Secondname:" + res.getString(1) + "\n");
                    String check=res.getString(0);
                    String check1=res.getString(1);



                    if(check.equals(unames) && check1.equals(passwords))
                    {

                        Intent i =new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(i);
                        msgg.setVisibility(view.INVISIBLE);

                        // Toast.makeText(getApplicationContext(), "correct", Toast.LENGTH_SHORT).show();


                    }


                    if(!check1.equals(passwords))

                    {
                        msgg.setVisibility(View.VISIBLE);
                        msgg.setText("The password you've enterd is incorrect Forgettern Password?");

                        //Toast.makeText(getApplicationContext(), " password error", Toast.LENGTH_SHORT).show();
                    }



                }


            }


        });
        ne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String password = pass.getText().toString();
                System.out.println("username:" + username);
                System.out.println("Password:" + password);

                if (!isvalidEmail(username)) {
                    uname.setError("Invalid Email,eg:ex@gmail.com");
                    //Toast.makeText(getApplicationContext(),"ex@gmail.com",Toast.LENGTH_SHORT).show();
                }
                if (!isValidPassword(password)) {
                    pass.setError("Invalid Password");
                    Toast.makeText(getApplicationContext(),"invalid email,Account Not Created ",Toast.LENGTH_SHORT).show();
                }
                if (isvalidEmail(username) && isValidPassword(password)) {
                    boolean inserted = mydb.insert(username, password);

                    if (inserted == true) {
                        Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

            }
    private boolean isvalidEmail(String username)
    {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 6) {
            return true;
        }
        return false;
    }


}
