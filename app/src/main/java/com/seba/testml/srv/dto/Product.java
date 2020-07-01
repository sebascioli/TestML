package com.seba.testml.srv.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product implements Parcelable {

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    public static String KEY_PRODUCT = "product";
    @SerializedName("id")
    private String id;
    @SerializedName("site_id")
    private String site_id;
    @SerializedName("title")
    private String title;
    @SerializedName("seller")
    private Object seller;
    @SerializedName("price")
    private float price;
    @SerializedName("currency_id")
    private String currency_id;
    @SerializedName("available_quantity")
    private int available_quantity;
    @SerializedName("sold_quantity")
    private int sold_quantity;
    @SerializedName("buying_mode")
    private String buying_mode;
    @SerializedName("listing_type_id")
    private String listing_type_id;
    @SerializedName("stop_time")
    private String stop_time;
    @SerializedName("condition")
    private String condition;
    @SerializedName("permalink")
    private String permalink;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("accepts_mercadopago")
    private boolean accepts_mercadopago;
    @SerializedName("installments")
    private Object installments;
    @SerializedName("address")
    private Object address;
    @SerializedName("shipping")
    private Object shipping;
    @SerializedName("seller_address")
    private Object seller_address;
    @SerializedName("attributes")
    private List<Object> attributes;
    @SerializedName("original_price")
    private float original_price;
    @SerializedName("category_id")
    private String category_id;
    @SerializedName("official_store_id")
    private int official_store_id;
    @SerializedName("domain_id")
    private String domain_id;
    @SerializedName("catalog_product_id")
    private String catalog_product_id;
    @SerializedName("tags")
    private List<String> tags;

    protected Product(Parcel in) {
        id = in.readString();
        site_id = in.readString();
        title = in.readString();
        price = in.readFloat();
        currency_id = in.readString();
        available_quantity = in.readInt();
        sold_quantity = in.readInt();
        buying_mode = in.readString();
        listing_type_id = in.readString();
        stop_time = in.readString();
        condition = in.readString();
        permalink = in.readString();
        thumbnail = in.readString();
        accepts_mercadopago = in.readByte() != 0;
        original_price = in.readFloat();
        category_id = in.readString();
        official_store_id = in.readInt();
        domain_id = in.readString();
        catalog_product_id = in.readString();
        tags = in.createStringArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getSeller() {
        return seller;
    }

    public void setSeller(Object seller) {
        this.seller = seller;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public int getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(int sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public String getBuying_mode() {
        return buying_mode;
    }

    public void setBuying_mode(String buying_mode) {
        this.buying_mode = buying_mode;
    }

    public String getListing_type_id() {
        return listing_type_id;
    }

    public void setListing_type_id(String listing_type_id) {
        this.listing_type_id = listing_type_id;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isAccepts_mercadopago() {
        return accepts_mercadopago;
    }

    public void setAccepts_mercadopago(boolean accepts_mercadopago) {
        this.accepts_mercadopago = accepts_mercadopago;
    }

    public Object getInstallments() {
        return installments;
    }

    public void setInstallments(Object installments) {
        this.installments = installments;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getShipping() {
        return shipping;
    }

    public void setShipping(Object shipping) {
        this.shipping = shipping;
    }

    public Object getSeller_address() {
        return seller_address;
    }

    public void setSeller_address(Object seller_address) {
        this.seller_address = seller_address;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public float getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(float original_price) {
        this.original_price = original_price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getOfficial_store_id() {
        return official_store_id;
    }

    public void setOfficial_store_id(int official_store_id) {
        this.official_store_id = official_store_id;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getCatalog_product_id() {
        return catalog_product_id;
    }

    public void setCatalog_product_id(String catalog_product_id) {
        this.catalog_product_id = catalog_product_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(site_id);
        parcel.writeString(title);
        parcel.writeFloat(price);
        parcel.writeString(currency_id);
        parcel.writeInt(available_quantity);
        parcel.writeInt(sold_quantity);
        parcel.writeString(buying_mode);
        parcel.writeString(listing_type_id);
        parcel.writeString(stop_time);
        parcel.writeString(condition);
        parcel.writeString(permalink);
        parcel.writeString(thumbnail);
        parcel.writeByte((byte) (accepts_mercadopago ? 1 : 0));
        parcel.writeFloat(original_price);
        parcel.writeString(category_id);
        parcel.writeInt(official_store_id);
        parcel.writeString(domain_id);
        parcel.writeString(catalog_product_id);
        parcel.writeStringList(tags);
    }
}