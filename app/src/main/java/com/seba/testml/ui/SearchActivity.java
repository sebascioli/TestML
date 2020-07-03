package com.seba.testml.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seba.testml.R;
import com.seba.testml.databinding.ActivitySearchBinding;
import com.seba.testml.srv.RestAPI;
import com.seba.testml.srv.RestSrv;
import com.seba.testml.srv.dto.Product;
import com.seba.testml.srv.dto.QueryModel;
import com.seba.testml.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.seba.testml.srv.dto.Product.KEY_PRODUCT;
import static com.seba.testml.utils.Utils.hideKeyboard;
import static com.seba.testml.utils.Utils.showKeyboard;
import static java.net.HttpURLConnection.HTTP_OK;

public class SearchActivity extends BaseActivity implements QueryAdapter.OnProductListener {

    private static final int HTTP_TIMEOUT = 10; // s
    private static final int SPLASH_TIME = 1000; // ms
    private static final String URL_BASE = "https://api.mercadolibre.com";
    private static final String ID_SITE = "MLA";
    final int DRAWABLE_LEFT = 0, DRAWABLE_TOP = 1, DRAWABLE_RIGHT = 2, DRAWABLE_BOTTOM = 3;
    private ActivitySearchBinding bind;
    private LinearLayoutManager layoutMgr;
    private QueryAdapter queryAdapter;
    private RestAPI restAPI;
    private String query;
    private boolean isLoading = true;
    private int offset = 0, limit = 50, totalFound = -1;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewThreshold = 0;

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        keepSplash();
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        bind = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        layoutMgr = new LinearLayoutManager(this);

        bind.resultsRV.setVisibility(View.GONE);
        bind.emptyRV.setVisibility(View.VISIBLE);

        bind.queryRV.setHasFixedSize(true);
        bind.queryRV.setLayoutManager(layoutMgr);
        bind.queryRV.addItemDecoration(new DividerItemDecoration(bind.queryRV.getContext(), DividerItemDecoration.VERTICAL));

        bind.queryTIET.setOnKeyListener((view, keyCode, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                hideKeyboard(SearchActivity.this);
                bind.resultsRV.setVisibility(View.VISIBLE);
                bind.emptyRV.setVisibility(View.GONE);
                String queryNew = bind.queryTIET.getText().toString();
//                Toast.makeText(SearchActivity.this, queryNew, Toast.LENGTH_SHORT).show();
                if (!queryNew.equals(query)) {
                    query = queryNew;
                    clearScreen();
                    bind.infoItemsTV.setText(getString(R.string.searching));
                    getQueryResponse();
                }
                return true;
            }
            return false;
        });

        bind.queryTIET.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (motionEvent.getRawX() >= (bind.queryTIET.getRight() - bind.queryTIET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    bind.queryTIET.getText().clear();
                    query = "";
                    clearScreen();
                    bind.resultsRV.setVisibility(View.GONE);
                    bind.emptyRV.setVisibility(View.VISIBLE);
                    bind.queryTIET.requestFocus();
                    showKeyboard(SearchActivity.this);
                    return true;
                }
            }
            return false;
        });

        bind.queryRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutMgr.getChildCount();
                totalItemCount = layoutMgr.getItemCount();
                pastVisibleItems = layoutMgr.findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    } else {
                        Log.d(Utils.TAG, "\noffset=" + offset + "\nlimit=" + limit + "\ntotalFound=" + totalFound +
                                "\npastVisibleItems=" + pastVisibleItems + "\nvisibleItemCount=" + visibleItemCount + "\ntotalItemCount=" + totalItemCount + "\npreviousTotal=" + previousTotal);
                        if ((totalItemCount - visibleItemCount) <= (pastVisibleItems + viewThreshold)) {
                            Log.v(Utils.TAG, "Carga 50 más...");
                            offset += limit;
                            getQueryResponse();
                            isLoading = true;
                        }
                    }
                }
            }
        });

        bind.queryTIET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    bind.queryTIET.getCompoundDrawables()[DRAWABLE_RIGHT].setVisible(true, false);
                } else {
                    bind.queryTIET.getCompoundDrawables()[DRAWABLE_RIGHT].setVisible(false, false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void keepSplash() {
        try {
            Thread.sleep(SPLASH_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private RestAPI getRestAPI() {
        RestSrv restSrv = new RestSrv(URL_BASE, HTTP_TIMEOUT);
        return restSrv.getRestAPI();
    }

    private void getQueryResponse() {
        bind.progressBar.setVisibility(View.VISIBLE);
        if (restAPI == null) restAPI = getRestAPI();
        Call<QueryModel> call = restAPI.getItemsByQuery(ID_SITE, query, offset, limit);
        call.enqueue(new Callback<QueryModel>() {
            @Override
            public void onResponse(Call<QueryModel> call, Response<QueryModel> response) {
                if (response.code() == HTTP_OK) {
                    call.cancel();
                    QueryModel body = response.body();
                    if (body != null) {
                        if (totalFound == -1) {
                            totalFound = body.getPaging().getTotal();
                            bind.infoItemsTV.setText(getResources().getQuantityString(R.plurals.results_found, totalFound, totalFound));
                        }
                        ArrayList<Product> products = body.getProducts();
                        if (queryAdapter == null) {
                            queryAdapter = new QueryAdapter(SearchActivity.this, products, SearchActivity.this);
                            bind.queryRV.setAdapter(queryAdapter);
                        } else queryAdapter.addProducts(products);
                    }
                }
                bind.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<QueryModel> call, Throwable t) {
                Toast.makeText(SearchActivity.this, getString(R.string.search_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearScreen() {
        offset = 0;
        previousTotal = 0;
        totalFound = -1;
        bind.infoItemsTV.setText(getString(R.string.no_results));
        if (queryAdapter != null) queryAdapter.clearData();
    }

    @Override
    public void onProductClick(Product product) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(KEY_PRODUCT, product);
        startActivity(intent);
    }
}