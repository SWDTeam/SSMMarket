package test.kietpt.smartmarket.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import test.kietpt.smartmarket.R;
import test.kietpt.smartmarket.adapter.ProductListAdapter;
import test.kietpt.smartmarket.model.ProductDTO;
import test.kietpt.smartmarket.ulti.IpConfig;

public class ProductListActi extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    ProductListAdapter productListAdapter;
    ArrayList<ProductDTO> arrayList;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        reflect();
        getListProductById();
        backByToolBar();
    }

    private void backByToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getListProductById() {
        Intent intent = getIntent();
        position = intent.getIntExtra("cateId", -1);
        Log.e("CATEID PRODUCT LIST ", position + "");
        getData("http://" + IpConfig.ipConfig + ":8084/SSM_Project/GetProductListByCateId?txtCateId=" + position);
    }

    private void getData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("RESPONSE PRODUCT LIST ", response.toString());
                if (response.toString() != null) {
                    try {
                        //JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("productId");
                            String name = jsonObject.getString("productName");
                            String des = jsonObject.getString("description");
                            String urlPic = jsonObject.getString("urlPic");
                            String key = jsonObject.getString("productKey");
                            int cateId = jsonObject.getInt("categoryId");
                            float price = (float) jsonObject.getDouble("price");
                            arrayList.add(new ProductDTO(name, des, urlPic, key, cateId, id, price));
                            productListAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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
        requestQueue.add(jsonArrayRequest);
    }

    public void reflect() {
        toolbar = (Toolbar) findViewById(R.id.toolbarProductListItem);
        listView = (ListView) findViewById(R.id.listViewProductListItem);
        arrayList = new ArrayList<>();
        productListAdapter = new ProductListAdapter(this, arrayList);
        listView.setAdapter(productListAdapter);
    }
}
