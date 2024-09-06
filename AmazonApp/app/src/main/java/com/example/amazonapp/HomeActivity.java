package com.example.amazonapp;

import static com.example.amazonapp.R.*;
import static com.example.amazonapp.R.id.*;
import static com.example.amazonapp.R.id.dynamicContent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amazonapp.MenuFiles.BaseActivity;
import com.example.amazonapp.MenuFiles.CartActivity;
import com.example.amazonapp.MenuFiles.SearchActivity;
import com.example.amazonapp.constant.Constant;
import com.example.amazonapp.model.Product;
import com.example.amazonapp.viewholder.ProductAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Calendar;

public class HomeActivity extends BaseActivity {
    Intent intentcart;
    String getcartupdate;

    Toolbar toolbar;

    CardView shoes1,shoes2,shoes3,shoes4,shoes5;

    TextView oddshoename,oddshoeprice, evenshoename , evenshoeprice,viewAll;

    public static ImageView home_cart;

    FirebaseStorage storage;
    StorageReference storageReference;

    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;
    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_home);

        dynamicContent = findViewById(R.id.dynamicContent);
        bottomNavBar = findViewById(R.id.bottomNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_home, null);
        dynamicContent.addView(wizard);


        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.bottom_home);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F51B5"));

//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setBackgroundResource(R.drawable.bg_color);

        shoes1 = findViewById(R.id.shoes1);
        shoes2 = findViewById(R.id.shoes2);
        shoes3 = findViewById(R.id.shoes3);
        shoes4 = findViewById(R.id.shoes4);
        shoes5 = findViewById(R.id.shoes5);

        oddshoename = findViewById(R.id.oddshoename);
        oddshoeprice = findViewById(R.id.oddshoeprice);
        evenshoename = findViewById(R.id.evenshoename);
        evenshoeprice = findViewById(R.id.evenshoeprice);

        viewAll = findViewById(R.id.viewAllProducts);

        home_cart = findViewById(R.id.home_cart);
        intentcart = getIntent();
        if (intentcart.getStringExtra("cartadd") != null && intentcart.getStringExtra("cartadd").equals("yes")) {
            home_cart.setImageResource(R.drawable.cart_notif);
        } else if (intentcart.getStringExtra("cartadd") != null && intentcart.getStringExtra("cartadd").equals("no")) {
            home_cart.setImageResource(R.drawable.cart);
        } else {
            home_cart.setImageResource(R.drawable.cart);
        }

        storage = FirebaseStorage.getInstance();

        shoes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", oddshoename.getText().toString());
                i.putExtra("category", "Men's Running Shoes");
                i.putExtra("price", oddshoeprice.getText().toString());
                i.putExtra("uniqueId", oddshoename.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

        shoes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", evenshoename.getText().toString());
                i.putExtra("category", "Men's Shoes");
                i.putExtra("price", evenshoeprice.getText().toString());
                i.putExtra("uniqueId", evenshoename.getText().toString());
                i.putExtra("id", 2);
                startActivity(i);
            }
        });

        shoes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", oddshoename.getText().toString());
                i.putExtra("category", "Men's Running Shoes");
                i.putExtra("price", oddshoeprice.getText().toString());
                i.putExtra("uniqueId", oddshoename.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

        shoes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", evenshoename.getText().toString());
                i.putExtra("category", "Men's Shoes");
                i.putExtra("price", evenshoeprice.getText().toString());
                i.putExtra("uniqueId", evenshoename.getText().toString());
                i.putExtra("id", 2);
                startActivity(i);
            }
        });

        shoes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", oddshoename.getText().toString());
                i.putExtra("category", "Men's Running Shoes");
                i.putExtra("price", oddshoeprice.getText().toString());
                i.putExtra("uniqueId", oddshoename.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

        ListView lvproducts = findViewById(id.productslist);
        ListView lvProducts = (ListView) findViewById(R.id.productslist);

        ProductAdapter productAdapter = new ProductAdapter(this);
        productAdapter.updateProducts(Constant.PRODUCT_LIST);

        lvProducts.setAdapter(productAdapter);

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Product product = Constant.PRODUCT_LIST.get(position);
                Intent intent = new Intent(HomeActivity.this, ProductDetails.class);
                intent.putExtra("id", 3);
                intent.putExtra("uniqueId", product.getName());
                intent.putExtra("name", product.getName());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("category", "Smartphone");
                intent.putExtra("pprice", Constant.CURRENCY + String.valueOf(product.getPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
                intent.putExtra("imageName", product.getImageName());
                Log.d("TAG", "View product: " + product.getName());
                startActivity(intent);
            }
        });

        home_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);
            }

        });

        addingToProdList();
    }

    private void addingToProdList() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All");
        String name="Samsung Galaxy F42";

        final HashMap<String, Object> prodMap = new HashMap<>();
        prodMap.put("pid",name );
        prodMap.put("name", name);
        prodMap.put("price", "₹17899");
        prodMap.put("category", "Mobile");
        prodMap.put("description","6 GB RAM\n128 GB ROM\nExpandable Upto 1 TB\n\" +n" +
                "                    \"16.76 cm (6.6 inch) Full HD+ Display\n\" +n" +
                "                    \"64MP + 5MP + 2MP | 8MP Front Camera\n\" +n" +
                "                    \"5000 mAh Lithium-ion Battery\\n\" +n" +
                "                    \"MediaTek Dimensity 700 Processor\",n" +
                "            \"samsung_galaxy_f42");
        prodMap.put("date", saveCurrentDate);
        prodMap.put("time", saveCurrentTime);
        prodListRef.child("User View").child("Products")
                .child(name).updateChildren(prodMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                             Log.i("task","Successfull");
                        }
                    }
                });
    }
}