package com.example.satte.Service;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

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
import com.example.satte.Activity.LoadData;
import com.example.satte.Activity.LogIn;
import com.example.satte.Activity.MainActivity;
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

public class LoadDataService extends IntentService {
    public Context context;
    public String username = "";
    public String password = "";
    ArrayList<Long> localInspectionsId;
    JSONArray inspectionList;
    public LoadDataService() {
        super("LoadDataService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            while(true)
            {
                Thread.sleep(30000);//update every minutes
                if(isNetworkConnected()){
                    localInspectionsId = new ArrayList<>();
                    inspectionList = new JSONArray();
                    SharedPreferences shared = getSharedPreferences("userInfo", MODE_PRIVATE);
                     username = shared.getString("user","");
                     password = shared.getString("pass","");
                     System.out.println("user" + username + "pass" + password);
                    handleSSLHandshake();
                    sendToServer();
                    deleteProjectTable();
                    deleteExecutionProjectInspectionTable();
                    getProjectFromServer();
                    getProjectInspections();

                    Log.d(TAG, "Service Running...");
                }
                else
                    Log.d(TAG, "No Internet :(");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm != null && cm.getActiveNetworkInfo() != null;
    }
    private void deleteProjectTable(){
        JsonInsertUtil.deleteProjectDataBase(getApplicationContext());
    }
    private void deleteExecutionProjectInspectionTable(){
        JsonInsertUtil.deleteExecutionProjectInspectionDataBase(getApplicationContext());
    }
    private void getProjectFromServer(){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, getString(R.string.url) + "/*******************" ,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("Hadi" + response.length());
                JsonInsertUtil.insertProjectFromJSON(response,getApplicationContext());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Hadi" + error.getMessage());
            }
        }) {
            Map params = new HashMap();

            @Override
            public Map getParams() throws AuthFailureError {
//                params.put("companyId", 20155);
//                params.put("userId", 20435);
//                params.put("groupId", 1758958);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String credentials = username+":"+password;
                String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
    private void getProjectInspections(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, getString(R.string.url) + "/*******************" ,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("Hadi" + response);
                JsonInsertUtil.insertExecutionProjectInspectionFromJSON(response,getApplicationContext(),"LoadData");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            Map params = new HashMap();

            @Override
            public Map getParams() throws AuthFailureError {
//                params.put("companyId", 20155);
//                params.put("userId", 20435);
//                params.put("groupId", 1758958);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String credentials = username+":"+password;;
                String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
    private void sendToServer()  {
        System.out.println("asefasef");
        ExecutionProjectInspectionLocalServiceUtil executionProjectInspectionLocalServiceUtil = new ExecutionProjectInspectionLocalServiceUtil(getApplicationContext());
        List<ExecutionProjectInspection> executionProjectInspection = executionProjectInspectionLocalServiceUtil.getExecutionProjectInspection();
        if (executionProjectInspection.size() > 0 ) {
            for (int i = 0; i < executionProjectInspection.size(); i++) {
                if (executionProjectInspection.get(i).getExecutionprojectinspectionid() < 0) {
                    localInspectionsId.add(executionProjectInspection.get(i).getExecutionprojectinspectionid());
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("executionProjectInspectionId", executionProjectInspection.get(i).getExecutionprojectinspectionid());
                        jsonObject.put("projectCode", executionProjectInspection.get(i).getProjectcode());
                        jsonObject.put("rialProgress", executionProjectInspection.get(i).getRialprogress());
                        jsonObject.put("percentageProgressFirstYear", executionProjectInspection.get(i).getPercentageprogressfirstyear());
                        jsonObject.put("physicalProgressInspectionTime", executionProjectInspection.get(i).getPhysicalprogressinspectiontime());
                        jsonObject.put("executionStatus", executionProjectInspection.get(i).getExecutionstatusint());
                        jsonObject.put("performanceQuality", executionProjectInspection.get(i).getPerformancequalityint());
                        jsonObject.put("howReferWork", executionProjectInspection.get(i).getHowreferworkint());
                        jsonObject.put("contractRateBasics", executionProjectInspection.get(i).getContractratebasicsint());
                        jsonObject.put("technicalSpecifications", executionProjectInspection.get(i).getTechnicalspecificationsint());
                        jsonObject.put("operationVolume", executionProjectInspection.get(i).getOperationvolumeint());
                        jsonObject.put("practical", executionProjectInspection.get(i).getPracticalint());
                        jsonObject.put("physicalProgressType", executionProjectInspection.get(i).getPhysicalprogresstypeint());
                        jsonObject.put("expressingOpinionsProviding", executionProjectInspection.get(i).getExpressingopinionsproviding());
                        jsonObject.put("causesDelay", executionProjectInspection.get(i).getCausesdelay());
                        jsonObject.put("geometry", executionProjectInspection.get(i).getWkt());
                        jsonObject.put("documents", executionProjectInspection.get(i).getDocuments());
                        inspectionList.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (inspectionList.length() > 0) {
                StringRequest request = new StringRequest(Request.Method.POST, getString(R.string.url) + "/*******************", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("response : " + response);
                        if(response.contains("success")){
                            for(int i = 0;i<localInspectionsId.size();i++){
                                System.out.println("sda" + localInspectionsId.get(i));
                                executionProjectInspectionLocalServiceUtil.deleteExecutionProjectInspection(localInspectionsId.get(i));
                            }
                        }else{

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    Map<String, Object> params = new HashMap();

                    @Override
                    public Map getParams() throws AuthFailureError {
                        params.put("inspectionList", inspectionList.toString());
                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        String credentials = username + ":" + password;
                        ;
                        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                            params.put("Content-Type", "application/json");
                        params.put("Authorization", auth);
                        return params;
                    }
                };
                request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(request);
            }
        }
    }
}
