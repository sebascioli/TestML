package com.seba.testml.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;
import com.seba.testml.R;
import com.seba.testml.srv.dto.Product;
import com.seba.testml.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.seba.testml.utils.Utils.formatPrice;
import static java.lang.Math.round;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<Product> products;
    private OnProductListener onProductListener;

    public QueryAdapter(Context ctx, ArrayList<Product> products, OnProductListener onProductListener) {
        this.ctx = ctx;
        this.products = products;
        this.onProductListener = onProductListener;
    }

    @NonNull
    @Override
    public QueryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new QueryAdapter.ViewHolder(view, this.onProductListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QueryAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.normalDeliveryCV.setVisibility(View.GONE);
        try {
            if (product.getTags().get(5).equals("shipping_guaranteed")) {
                holder.normalDeliveryCV.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.titleTV.setText(product.getTitle());

        float price = product.getPrice();
        holder.priceTV.setText(formatPrice(price, "$", 0));

        holder.percentTV.setVisibility(View.GONE);
        float originalPrice = product.getOriginal_price();
        if (originalPrice > 0f) {
            int percent = round(((originalPrice - price) / originalPrice) * 100);
            holder.percentTV.setVisibility(View.VISIBLE);
            holder.percentTV.setText(percent + "%" + " " + "OFF");
        }

        holder.infoSellerTV.setVisibility(View.GONE);
        try {
            String sellerArray = (String) ((LinkedTreeMap) product.getSeller()).values().toArray()[1];
            String[] split = sellerArray.split("/");
            String seller = split[split.length - 1];
            holder.infoSellerTV.setText(ctx.getString(R.string.sold_by) + " " + seller);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String thumbnail = product.getThumbnail();
        Picasso.get()
                .load(thumbnail)
                .centerCrop()
                .transform(new CircleTransform(30, 0))
                .fit()
                .into(holder.thumbnailIV);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addProducts(List<Product> productsToAdd) {
        products.addAll(productsToAdd);
        notifyDataSetChanged();
    }

    public void clearData() {
        products.clear();
        notifyDataSetChanged();
    }

    public interface OnProductListener {
        void onProductClick(Product product);
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnProductListener onProductListener;
        private ImageView thumbnailIV;
        private CardView normalDeliveryCV;
        private TextView titleTV, priceTV, percentTV, infoSellerTV;

        public ViewHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);

            thumbnailIV = itemView.findViewById(R.id.thumbnailIV);
            normalDeliveryCV = itemView.findViewById(R.id.normalDeliveryCV);
            titleTV = itemView.findViewById(R.id.titleTV);
            priceTV = itemView.findViewById(R.id.priceTV);
            percentTV = itemView.findViewById(R.id.percentTV);
            infoSellerTV = itemView.findViewById(R.id.infoSellerTV);
            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProductListener.onProductClick(products.get(getAdapterPosition()));
        }
    }
}