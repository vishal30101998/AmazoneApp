package com.example.amazonapp.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.amazonapp.R;

import com.example.amazonapp.constant.Constant;
import com.example.amazonapp.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private static final String TAG = "ProductAdapter";

    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView tvName;
        TextView tvPrice;
        ImageView ivImage;
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.activity_product_adapter, parent, false);
            tvName = (TextView) view.findViewById(R.id.tvProductName);
            tvPrice = (TextView) view.findViewById(R.id.tvProductPrice);
            ivImage = (ImageView) view.findViewById(R.id.ivProductImage);
            view.setTag(new ViewHolder(tvName, tvPrice, ivImage));
        } else {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            tvName = viewHolder.tvProductName;
            tvPrice = viewHolder.tvProductPrice;
            ivImage = viewHolder.ivProductImage;
        }

        final Product product = getItem(position);
        tvName.setText(product.getName());
        tvPrice.setText(Constant.CURRENCY+String.valueOf(product.getPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
//        Log.d(TAG, "Context package name: " + context.getPackageName());
        ivImage.setImageResource(context.getResources().getIdentifier(
                product.getImageName(), "drawable", context.getPackageName()));
        return view;
    }

    private static class ViewHolder {
        public final TextView tvProductName;
        public final TextView tvProductPrice;
        public final ImageView ivProductImage;

        public ViewHolder(TextView tvProductName, TextView tvProductPrice, ImageView ivProductImage) {
            this.tvProductName = tvProductName;
            this.tvProductPrice = tvProductPrice;
            this.ivProductImage = ivProductImage;
        }
    }
}
