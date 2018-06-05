package test.kietpt.smartmarket;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    TextView textViewUsername;
    ImageView imgPicAccount;
    String email;
    Database database = new Database(this);
    int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        listView = (ListView) findViewById(R.id.listViewAccountActi);
        textViewUsername = (TextView) findViewById(R.id.txtUsernameAccount);
        imgPicAccount = (ImageView) findViewById(R.id.imgPicAccount);

        Intent intent = getIntent();
        email = intent.getStringExtra("txtEmail");
        Account account = database.getCustomerInfo(email);
        textViewUsername.setText(account.getUsername().toString());
        if (account.getGender().equals("male")) {
            imgPicAccount.setBackground(getDrawable(R.drawable.maleicon));
        }


        arrayList = new ArrayList<String>();
        arrayList.add("My Profile");
        arrayList.add("My Orders");
        arrayList.add("My Canceled Orders");
        arrayList.add("Change Password");
        arrayList.add("Logout");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AccountActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    Log.e("IIIIII  ", i + " - - - --");
                    Intent intent = new Intent(AccountActivity.this, ProfileActi.class);
                    intent.putExtra("txtEmail", email);
                    startActivityForResult(intent, REQUEST_CODE);
                } else if (i == 1) {
                    Log.e("IIIIII  ", i + " - - - --");

                    Intent intent = new Intent(AccountActivity.this, MyCartActi.class);
                    intent.putExtra("txtEmail", email);
                    startActivity(intent);
                } else if (i == 2) {
                    Log.e("IIIIII  ", i + " - - - --");

                    Intent intent = new Intent(AccountActivity.this, MyCanceledCartActi.class);
                    intent.putExtra("txtEmail", email);
                    startActivity(intent);
                } else if(i == 3){
                    Log.e("IIIIII  ", i + " - - - --");
                    Intent intent = new Intent(AccountActivity.this, ChangePassActi.class);
                    intent.putExtra("txtEmail", email);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
