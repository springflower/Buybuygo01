package com.example.springflower.buybuygo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;import butterknife.ButterKnife;
import butterknife.InjectView;


public class NDActivity extends Activity {

    //private Button mapbtn;
    //@InjectView(R.id.btnmap) protected Button mapbtn;
    @InjectView(R.id.et_email) protected AutoCompleteTextView emailText;
    @InjectView(R.id.et_password) protected EditText passwordText;
    @InjectView(R.id.b_signin) protected Button signInButton;
    Thread Login;
    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("Users", MODE_PRIVATE );
        String sname = null;
        sname = preferences.getString("UserAccount", "");
        if(sname != null){
            Intent i = new Intent(NDActivity.this,MainActivity.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_nd2);
        ButterKnife.inject(this);
        getview();
    }

    private void getview(){
        //mapbtn = (Button)findViewById(R.id.btnmap);
//        mapbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(NDActivity.this,MapsActivity.class);
//                startActivity(intent);
//            }
//        });
        final TextView signUpText = (TextView) findViewById(R.id.tv_signup);
        signUpText.setMovementMethod(LinkMovementMethod.getInstance());
        signUpText.setText(Html.fromHtml(getString(R.string.signup_link)));
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login = new Login();
                Login.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class Login extends Thread{

        @Override
        public void run(){

            //建立HttpClient用以跟伺服器溝通

            HttpClient client = new DefaultHttpClient();

            try {

                HttpPost post = new HttpPost("http://59.126.92.205:8080/login.php");

                //建立POST的變數
                List<NameValuePair> vars = new ArrayList<NameValuePair>();
                vars.add(new BasicNameValuePair("Username",emailText.getText().toString()));
                vars.add(new BasicNameValuePair("Password",passwordText.getText().toString()));
                //Toast.makeText(NDActivity.this, emailText.getText().toString(), Toast.LENGTH_LONG).show();
                //String response=new String(emailText.getText().toString());
                System.out.println();
                //發出POST要求

                post.setEntity(new UrlEncodedFormEntity(vars, HTTP.UTF_8));


                //建立ResponseHandler,以接收伺服器回傳的訊息
                ResponseHandler<String> h=new BasicResponseHandler();

                //將回傳的訊息轉為String
                String response=new String(client.execute(post,h).getBytes(),HTTP.UTF_8);
                String success = "Login Succeeded";

                Looper.prepare();

                //若回傳的訊息等於"Login Succeeded"，跳轉到另一個頁面

                if(response.equals(success)){

                    Intent i = new Intent(NDActivity.this,MainActivity.class);
                    preferences.edit().putString("UserAccount", emailText.getText().toString()).commit();
                    //MainActivity.
                    startActivity(i);

                }

                //否則只顯示回傳訊息

                else{

                    Toast.makeText(NDActivity.this, response, Toast.LENGTH_LONG).show();

                }

                Looper.loop();

            }


            catch(Exception ex){

                //若伺服器無法與PHP檔連接時的動作




            }


        }

    }
}
