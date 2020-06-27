package com.seba.testml.mgr;

import android.content.Context;

public class BaseMgr {

    protected final String TAG = this.getClass().getName();
    protected Context ctx;
//    BaseActivity vista;
//    DaoFactory daoFactory;

//  public BaseMgr(Context ctx) {
//    this.ctx = ctx;
//    this.vista = (BaseActivity) this.ctx;
//    daoFactory = DaoFactory.getInstance(ctx);
//  }
//
//  public boolean isNetworkAvailable() {
//    ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(CONNECTIVITY_SERVICE);
//    NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
//    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//  }

}