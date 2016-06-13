package org.despotic.crimefinder.Services;
import com.loopj.android.http.*;
/**
 * Created by ahand on 6/9/16.
 */

public class SeattleOpenDataClient {


        private static final String BASE_URL = "https://data.seattle.gov/resource/y7pv-r3kh.json?$$app_token=w7kpmAob4tegC6EjhunoSxMWC";

        private static AsyncHttpClient client = new SyncHttpClient();

        public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }

        public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
            client.post(getAbsoluteUrl(url), params, responseHandler);
        }

        private static String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }
}



