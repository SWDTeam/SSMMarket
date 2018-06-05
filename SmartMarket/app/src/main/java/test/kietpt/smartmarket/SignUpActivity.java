package test.kietpt.smartmarket;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONObject;



import java.util.ArrayList;

import java.util.List;


public class SignUpActivity extends AppCompatActivity {

    TextInputEditText email, pass, username, address, phone, confirmPass;
    private Spinner spGender;
    private String selectedGender;
    private Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (TextInputEditText) findViewById(R.id.txtEmailSignUp);
        pass = (TextInputEditText) findViewById(R.id.txtPasswordSignUp);
        confirmPass = (TextInputEditText) findViewById(R.id.txtConfirmPasswordSignUp);
        username = (TextInputEditText) findViewById(R.id.txtUsernameSignUp);
        address = (TextInputEditText) findViewById(R.id.txtAddressSignUp);
        phone = (TextInputEditText) findViewById(R.id.txtPhoneSignUp);
        spGender = (Spinner) findViewById(R.id.spGenderSignUp);
        List<String> dataSrc = new ArrayList<>();
        dataSrc.add("male");
        dataSrc.add("female");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataSrc);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(arrayAdapter);
        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public boolean checkValidate() {
        TextView checkEmail = (TextView) findViewById(R.id.checkEmail);
        TextView checkPass = (TextView) findViewById(R.id.checkPassword);
        TextView checkConfirmPass = (TextView) findViewById(R.id.checkConfirmPass);
        TextView checkUsername = (TextView) findViewById(R.id.checkUsername);
        TextView checkAddress = (TextView) findViewById(R.id.checkAddress);
        TextView checkPhone = (TextView) findViewById(R.id.checkPhone);


        String txtEmail = email.getText().toString();
        String txtPass = pass.getText().toString();
        String txtConfirmPass = confirmPass.getText().toString();
        String txtUsername = username.getText().toString();
        String txtAddress = address.getText().toString();
        String txtPhone = phone.getText().toString();

        boolean checked = false;
        if (!txtEmail.matches("[a-zA-Z0-9]{3,50}@[a-z]{3,10}.[a-z]{3,10}")) {
            checkEmail.setVisibility(View.VISIBLE);
            checkEmail.setText("Format abc123@gmail.com");
            checked = true;
        }
        if (txtPass.length() == 0 || txtConfirmPass.length() == 0 || !txtPass.equalsIgnoreCase(txtConfirmPass)) {
            if (txtPass.length() == 0) {
                checkPass.setVisibility(View.VISIBLE);
                checkPass.setText("Please not empty");
                checked = true;
            }
            if (txtConfirmPass.length() == 0) {
                checkConfirmPass.setVisibility(View.VISIBLE);
                checkConfirmPass.setText("Please not empty");
                checked = true;
            }
            checkConfirmPass.setVisibility(View.VISIBLE);
            checkConfirmPass.setText("Please input same password");
        }
        if (!txtUsername.matches("[a-zA-Z]{1,50}")) {
            checkUsername.setVisibility(View.VISIBLE);
            checkUsername.setText("Please input name");
            checked = true;
        }
        if (txtAddress.length() == 0) {
            checkAddress.setVisibility(View.VISIBLE);
            checkAddress.setText("Please not empty");
            checked = true;
        }
        if (!txtPhone.matches("0[1-9]{10,11}")) {
            if (txtPhone.length() == 0) {
                checkPhone.setVisibility(View.VISIBLE);
                checkPhone.setText("Please input phone");
                checked = true;
            }
        }
        return checked;
    }

    public void onClickSignUp(View view) {

        if(!checkValidate()){
            Toast.makeText(SignUpActivity.this,"Something wrong!!! please sign up again ",Toast.LENGTH_SHORT).show();
        }else{
            signUpCustomer("http://"+IpConfig.ipConfig+":8084/SSM_Project/RegisterController?txtEmail=" + email.getText().toString()
                    + "&txtPassword=" + pass.getText().toString() + "&txtUsername=" + username.getText().toString() +
                    "&txtAddress=" + address.getText().toString() + "&txtPhone=" + phone.getText().toString() + "&txtGender=" + selectedGender.toString()
                    + "&txtStatus=" + "active");
        }

    }

    public void getCustomer() {
        Log.e("123456", "da vao getCustomer() ");
        SQLiteDatabase sqlDB = database.getReadableDatabase();
        Cursor dataCustomer = sqlDB.rawQuery("Select * From Account", null);
        while (dataCustomer.moveToNext()) {
            int userId = dataCustomer.getInt(0);
            String email = dataCustomer.getString(1);
            String username = dataCustomer.getString(2);
            String gender = dataCustomer.getString(3);
            String phone = dataCustomer.getString(4);
            String passsword = dataCustomer.getString(5);
            String address = dataCustomer.getString(6);
            String status = dataCustomer.getString(7);
            Toast.makeText(SignUpActivity.this, userId + " - " + email + " - " + username +
                    " - " + gender + " - " + phone + " - " + passsword + " - " + address + " - " + status, Toast.LENGTH_SHORT).show();
        }
    }

    public void signUpCustomer(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.toString() != null) {
                            try {
                                int userId = response.getInt("userId");
                                String email = response.getString("email");
                                String username = response.getString("username");
                                String gender = response.getString("gender");
                                String phone = response.getString("phone");
                                String passsword = response.getString("password");
                                String address = response.getString("address");
                                String status = response.getString("status");


                                Account account = new Account(userId, email, username, gender, phone, passsword, address, status);
                                if (account != null) {
                                    database.insertCustomer(account);
                                }
                                Toast.makeText(SignUpActivity.this, "Create Successfully", Toast.LENGTH_SHORT).show();
                                //getCustomer();
                                database.getAllCustomer();
                                Toast.makeText(SignUpActivity.this, userId + " - " + email + " - " + username +
                                        " - " + gender + " - " + phone + " - " + passsword + " - " + address + " - " + status, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } catch (Exception e) {
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "Email has already, please input another email", Toast.LENGTH_LONG).show();
                        Log.e("ERRROR SIGNUP", "ERROR---- " + error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
