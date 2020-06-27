package com.seba.testml.srv;

public class RestSrv { // https://futurestud.io/blog/retrofit-2-upgrade-guide-from-1-9

    private RestAPI restAPI;

    public RestSrv(final String username, final String password, final String baseUrl, long timeout_s) {

    }

    public RestAPI getRestAPI() {
        return restAPI;
    }

    public void setRestAPI(RestAPI restAPI) {
        this.restAPI = restAPI;
    }

}