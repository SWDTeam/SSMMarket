package test.kietpt.smartmarket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import test.kietpt.smartmarket.R;
import test.kietpt.smartmarket.adapter.CartConfirmAdapter;


public class ConfirmCartActi extends AppCompatActivity {

    ListView listProductInCart;
    TextView txtCartIsEmpty,emailConfirmCart,phoneConfirmCart;
    static TextView txtTotalPrice;
    Button btnConfirm, btnBackToHome;
    Toolbar toolbar;
    CartConfirmAdapter cartAdapterConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cart);
        reflect();
        actionToolBar();
        showInforAccount();
        checkCart();
        getDataInCart();
        backToHome();
    }

    private void showInforAccount() {
        emailConfirmCart.setText(MainActivity.account.getEmail());
        phoneConfirmCart.setText(MainActivity.account.getPhone());
    }

    private void backToHome() {
        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void getDataInCart() {
        float totalOfPrice = 0;
        for (int i = 0; i < MainActivity.listCart.size(); i++) {
            totalOfPrice += MainActivity.listCart.get(i).getProductPrice();
        }
        Log.e("TOTLAPRICE ",totalOfPrice+"");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalPrice.setText(decimalFormat.format(totalOfPrice)+" $ ");
    }
    private void checkCart() {
        if (MainActivity.listCart.size() <= 0) {
            cartAdapterConfirm.notifyDataSetChanged();
            txtCartIsEmpty.setVisibility(View.VISIBLE);
            listProductInCart.setVisibility(View.INVISIBLE);
        } else {
            cartAdapterConfirm.notifyDataSetChanged();
            txtCartIsEmpty.setVisibility(View.INVISIBLE);
            listProductInCart.setVisibility(View.VISIBLE);
        }
    }
    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void reflect() {
        listProductInCart = (ListView) findViewById(R.id.listViewCartItemConfirm);
        txtCartIsEmpty = (TextView) findViewById(R.id.txtCartIsEmptyConfirm);
        emailConfirmCart = (TextView)findViewById(R.id.emailConfirmCart);
        phoneConfirmCart = (TextView)findViewById(R.id.phoneConfirmCart);
        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPriceConfirm);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnBackToHome = (Button) findViewById(R.id.btnBackToHome);
        toolbar = (Toolbar) findViewById(R.id.toolbarCartConfirm);
        cartAdapterConfirm = new CartConfirmAdapter(ConfirmCartActi.this, MainActivity.listCart);
        listProductInCart.setAdapter(cartAdapterConfirm);

    }
    public void confirmCart(View view) {
        if(MainActivity.listCart.size() <= 0){
            Toast.makeText(this, "Please to shop before confirm ", Toast.LENGTH_SHORT).show();
        }else{

        }

    }
}
