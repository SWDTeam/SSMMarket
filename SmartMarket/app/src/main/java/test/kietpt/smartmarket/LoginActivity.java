package test.kietpt.smartmarket;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {


    TextInputEditText email, pass;
    Database database = new Database(this);
    Button remove, login;
    ImageView imgTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (TextInputEditText) findViewById(R.id.txtEmail);
        pass = (TextInputEditText) findViewById(R.id.txtPassword);
        imgTest = (ImageView)findViewById(R.id.imgTest);

        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("KIET", email.getText().toString() + " - " + pass.getText().toString());

                if (email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Input Email or Password", Toast.LENGTH_SHORT).show();
                } else {
//                    loginCustomer("http://"+IpConfig.ipConfig+":8084/SSM_Project/LoginController?txtEmail=" + email.getText().toString()
//                            + "&txtPassword=" + pass.getText().toString());
                    String url = "http://192.168.100.8:8084/Project/MainController?txtAbc=" + "kiet";
                    loginCustomer("http://192.168.100.8:8084/Project/MainController?action=" + "View" + "&subAction=" +
                            "subViewPost" + "&subView=" + "sumittedPostMoDetail" + "&txtPostId=" + "165");
                }
            }
        });

    }


    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void loginCustomer(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("REPONSE : ", response.toString());
                if (email.getText().toString().equals("") || pass.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please input Email or Password", Toast.LENGTH_LONG).show();
                }
                if (response.toString().equals("{}")) {
                    Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
                    Log.e("FAIL", "FAIL  " + response.toString() + " - ");
                } else {
                    Log.e("ABC", "da vao Login ");
                    try {
                        String test = response.getString("symbolPic");
                        Log.e("JSON + ", test);
                        String test1 = test.substring(38);


                        System.out.println(test.substring(38));

                        int userId = response.getInt("userId");
                        String emailReponse = response.getString("email");
                        Log.e("EMAILREPONSE + ", emailReponse);

                        String usernameReponse = response.getString("username");
                        String genderReponse = response.getString("gender");
                        String phoneReponse = response.getString("phone");
                        String passswordReponse = response.getString("password");
                        String addressReponse = response.getString("address");
                        String statusReponse = response.getString("status");

                        Account account = new Account(emailReponse, usernameReponse, genderReponse, phoneReponse, passswordReponse,
                                addressReponse, statusReponse);
                        if (!database.checkEmail(emailReponse)) {
                            Log.e("KIETDEPCHAI ", " khong  ton tai");
                            database.insertCustomer(account);
                        } else {
                            Log.e("KIETDEPCHAI ", " da ton tai");
                        }
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                        intent.putExtra("txtEmail", email.getText().toString());
                        startActivity(intent);

                    } catch (Exception e) {
                        Log.e("ERROR LOGIN + ", e.getMessage());
                    }

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(stringRequest);
    }

}
