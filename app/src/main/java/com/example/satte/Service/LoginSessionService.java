package com.example.satte.Service;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.satte.Activity.LogIn;
import com.example.satte.R;
import com.example.satte.db.service.ExecutionProjectInspectionLocalServiceUtil;
import com.example.satte.db.tables.ExecutionProjectInspection;
import com.example.satte.db.util.JsonInsertUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class LoginSessionService extends IntentService {
    public Context context;
    public String username = "";
    public String password = "";
    ArrayList<Long> localInspectionsId;
    JSONArray inspectionList;
    public LoginSessionService() {
        super("LoadDataService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            while(true)
            {
                Thread.sleep(604800000);//end session every week
                SharedPreferences pref = getApplicationContext().getSharedPreferences("userInfo", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("user", "");
                editor.putString("pass", "");
                editor.commit();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }

}
