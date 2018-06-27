package test.kietpt.smartmarket.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import test.kietpt.smartmarket.R;

public class OrderedNotiActi extends AppCompatActivity {

    TextView accountOrderNoti,myOrderOrderNoti,orderCodeOrderNoti,prrceNoti;
    Button btnContinueNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_noti);
        reflect();

        showInforAboutOrder();
    }

    private void showInforAboutOrder() {
        Intent intent = getIntent();
        String orderAndPrice = intent.getStringExtra("OrderCodeAndPrice");
        String[] orderArr = orderAndPrice.split("-");
        orderCodeOrderNoti.setText(orderArr[0].trim().toString());
        prrceNoti.setText(orderArr[1].trim().toString()+" $ ");
        accountOrderNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AccountActivity.class);
                startActivity(intent);
            }
        });
        myOrderOrderNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),MyOrderedActi.class);
                startActivity(intent1);
            }
        });
        btnContinueNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuCart:
                Intent intentCart = new Intent(getApplicationContext(),MyCartActi.class);
                startActivity(intentCart);
                break;
            case R.id.menuSearch:
                Intent intentSearch = new Intent(getApplicationContext(),SearchViewActi.class);
                startActivity(intentSearch);
                break;
            case R.id.menuAccount:
                if(MainActivity.account != null){
                    Intent intentAccount = new Intent(getApplicationContext(),AccountActivity.class);
                    startActivity(intentAccount);
                }else{
                    Intent intentAccount = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intentAccount);
                }
                break;
            case R.id.menuCall:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:01676243500"));
                startActivity(intent);
                break;
            case R.id.menuMessage:
                Intent intentasd = new Intent();
                intentasd.setAction(Intent.ACTION_SENDTO);
                intentasd.putExtra("sms_body","");
                intentasd.setData(Uri.parse("sms:01676243500"));
                startActivity(intentasd);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private void reflect() {

        accountOrderNoti = (TextView)findViewById(R.id.accountOrderNoti);
        myOrderOrderNoti = (TextView)findViewById(R.id.myOrderOrderNoti);
        orderCodeOrderNoti = (TextView)findViewById(R.id.orderCodeOrderNoti);
        prrceNoti = (TextView)findViewById(R.id.priceOrderNoti);
        btnContinueNoti = (Button)findViewById(R.id.btnContinueOrderNoti);
    }

}
