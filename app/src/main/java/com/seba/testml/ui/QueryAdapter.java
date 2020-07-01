package com.seba.testml.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seba.testml.R;
import com.seba.testml.srv.dto.Product;
import com.seba.testml.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {

    private ArrayList<Product> products;
    private OnProductListener onProductListener;

    public QueryAdapter(ArrayList<Product> products, OnProductListener onProductListener) {
        this.products = products;
        this.onProductListener = onProductListener;
    }

    @NonNull
    @Override
    public QueryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new QueryAdapter.ViewHolder(view, this.onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryAdapter.ViewHolder holder, int position) {

        Product product = products.get(position);

        String title = product.getTitle();
        String id = product.getId();
        String thumbnail = product.getThumbnail();

        holder.titleTV.setText(title);
        holder.idTV.setText(id);

//        Picasso.get().load(thumbnailPath).into(holder.thumbnailIV);
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
        private TextView titleTV, idTV;

        public ViewHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);

            thumbnailIV = itemView.findViewById(R.id.thumbnailIV);
            titleTV = itemView.findViewById(R.id.titleTV);
            idTV = itemView.findViewById(R.id.idTV);
            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProductListener.onProductClick(products.get(getAdapterPosition()));
        }
    }
}