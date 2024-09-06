package com.example.amazonapp.MenuFiles;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapp.PlaceOrderActivity;
import com.example.amazonapp.R;
import com.example.amazonapp.model.Cart;
import com.example.amazonapp.viewholder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AppCompatButton nextBtn;
    RecyclerView.LayoutManager layoutManager;
    TextView totalprice;
    private int overallPrice=0;
    Toolbar cartToolbar;

    FirebaseAuth auth;
    LinearLayout dynamicContent, bottonNavBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        bottonNavBar= (LinearLayout) findViewById(R.id.bottomNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_cart, null);
        dynamicContent.addView(wizard);


        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb=(RadioButton)findViewById(R.id.bottom_cart);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F51B5"));

        cartToolbar=findViewById(R.id.cart_toolbar);
        cartToolbar.setBackgroundResource(R.drawable.bg_color);

        auth=FirebaseAuth.getInstance();

        recyclerView=findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        nextBtn=findViewById(R.id.next_button);
        totalprice=findViewById(R.id.totalprice);

        nextBtn.setBackgroundResource(R.drawable.bg_color);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalprice.getText().toString().equals("₹0") || totalprice.getText().toString().length()==0) {
                    Toast.makeText(CartActivity.this, "Cannot place order with 0 items", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(CartActivity.this, PlaceOrderActivity.class);
                    intent.putExtra("totalAmount", totalprice.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options= new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View").child(auth.getCurrentUser().getUid())
                        .child("Products"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter=
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
                        String name=model.getName().replaceAll("\n"," ");
                        holder.cartProductName.setText(name);
                        holder.cartProductPrice.setText(model.getPrice());
                        String intPrice= model.getPrice().replace("₹","");
                        overallPrice+=Integer.valueOf(intPrice);
                        totalprice.setText("₹"+String.valueOf(overallPrice));

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this);
                                builder.setTitle("Delete item");
                                builder.setMessage("Do you want to remove this product from cart?")
                                        .setCancelable(false)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                cartListRef.child("User View")
                                                        .child(auth.getCurrentUser().getUid())
                                                        .child("Products")
                                                        .child(model.getPid())
                                                        .removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    String intPrice= model.getPrice().replace("₹","");
                                                                    overallPrice-=Integer.valueOf(intPrice);
                                                                    totalprice.setText("₹"+String.valueOf(overallPrice));
                                                                    Toast.makeText(CartActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });

                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //  Action for 'NO' Button
                                                dialog.cancel();
                                            }
                                        });
                                builder.show();
                            }
                        });
                    }
                    @NonNull

                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                        CartViewHolder holder=new CartViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}