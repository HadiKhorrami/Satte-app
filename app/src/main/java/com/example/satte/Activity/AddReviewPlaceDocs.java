package com.example.satte.Activity;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.satte.Adapters.FilesRecyclerAdapter;
import com.example.satte.DataModels.FilesLayout;
import com.example.satte.R;
import com.example.satte.db.util.JsonInsertUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.localization.LocalizationPlugin;
import com.mapbox.mapboxsdk.plugins.localization.MapLocale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddReviewPlaceDocs extends AppCompatActivity implements LocationListener {
    private PermissionsManager permissionsManager;
    private PermissionsListener permissionsListener;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationComponent locationComponent;
    private LocationEngine locationEngine;
    public Criteria criteria;
    public String bestProvider;

    private static final int CAMERA_REQUEST = 1888;
    Dialog dialog;

    private int PICK_PDF_REQUEST = 1;
    private Uri filePath;
    String pdfPath;
    String polygonWKT = "";
    String pointWKTFromEdt = "";
    String pointWKTByMarker = "";
    int wktFlag = 0;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private MapView mapView;
    private MapboxMap mapboxMap;
    ImageView hoveringMarker;
    Double onePointLat, onePointLng;
    private static final List<List<Point>> POINTS = new ArrayList<>();
    private static final List<Point> OUTER_POINTS = new ArrayList<>();
    ArrayList<FilesLayout> filesLayoutArrayList;
    ArrayList<String> polygonLatLng;
    ArrayList<String> fileImages;
    FilesRecyclerAdapter filesRecyclerAdapter;
    RecyclerView recyclerView;
    Spinner spnProvinces;
    AppCompatButton btnUpload, btnDelete, btnSave, btnShowOnMap, btnDrawPolygon, btnStopAddMarker, btnAddOnePoint, btnLocation;
    EditText edtLat, edtLng;
    TextView txtName;
    NestedScrollView filesLayer;
    RelativeLayout filesRelative;
    JSONArray relativeLayoutList;
    JSONArray documentsArray;
    JSONArray insertArray;
    JSONObject insertInspectionJson;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressWarnings({"MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(AddReviewPlaceDocs.this, getString(R.string.mapbox_access_token));
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                // No location access granted.
                            }
                        }
                );
        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
        setContentView(R.layout.activity_add_review_place_docs);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mapView = (MapView) findViewById(R.id.mapView1);
        spnProvinces = (Spinner) findViewById(R.id.spnProvinces);
        filesLayer = (NestedScrollView) findViewById(R.id.filesLayer);
        btnUpload = (AppCompatButton) findViewById(R.id.btnUpload);
        btnSave = (AppCompatButton) findViewById(R.id.btnSave);
        dialog = new Dialog(AddReviewPlaceDocs.this);
        dialog.setContentView(R.layout.choosefile_dialog_layout);
        TextView txtChooseFile = (TextView) dialog.findViewById(R.id.txtChooseFile);
        TextView txtTakePicture = (TextView) dialog.findViewById(R.id.txtTakePicture);
//        btnShowOnMap = (AppCompatButton)findViewById(R.id.btnShowOnMap);
//        btnDrawPolygon = (AppCompatButton)findViewById(R.id.btnDrawPolygon);
//        btnStopAddMarker = (AppCompatButton)findViewById(R.id.btnStopAddMarker);
//        btnAddOnePoint = (AppCompatButton)findViewById(R.id.btnAddOnePoint);
        btnLocation = (AppCompatButton) findViewById(R.id.btnLocation);
//        edtLat = (EditText)findViewById(R.id.edtLat);
//        edtLng = (EditText)findViewById(R.id.edtLng);
        Intent intent = getIntent();
        String insertInspectionString = intent.getStringExtra("insertInspectionJson");
        try {
            insertInspectionJson = new JSONObject(insertInspectionString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("$$" + insertInspectionJson);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        relativeLayoutList = new JSONArray();
        documentsArray = new JSONArray();
        insertArray = new JSONArray();
        filesLayoutArrayList = new ArrayList<>();
        polygonLatLng = new ArrayList<>();
        fileImages = new ArrayList<>();
        OUTER_POINTS.clear();
        POINTS.clear();

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap , style);
                        localizationPlugin.setMapLanguage(MapLocale.LOCAL_NAME);
                    }
                });
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        txtChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        txtTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent.createChooser(cameraIntent, "Capture Image"), CAMERA_REQUEST);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                try {
//                    if (wktFlag == 1) {
//                        insertInspectionJson.put("wkt", pointWKTFromEdt);
//                        insertInspectionJson.put("lat", Double.parseDouble(edtLat.getText().toString()));
//                        insertInspectionJson.put("lng", Double.parseDouble(edtLng.getText().toString()));
//                    } else if (wktFlag == 2) {
//                        insertInspectionJson.put("wkt", polygonWKT);
//                        WKTReader wktReader = new WKTReader();
//                        Geometry geometry = wktReader.read(polygonWKT);
//                        geometry.setSRID(4326);
//                        insertInspectionJson.put("lat", geometry.getCentroid().getY());
//                        insertInspectionJson.put("lng", geometry.getCentroid().getX());
//                    } else if (wktFlag == 3) {
//                        insertInspectionJson.put("wkt", pointWKTByMarker);
//                        insertInspectionJson.put("lat", onePointLat);
//                        insertInspectionJson.put("lng", onePointLng);
//                    }

                    for (int i = 0; i < relativeLayoutList.length(); i++) {
                        JSONObject jsonObject = relativeLayoutList.getJSONObject(i);
                        JSONObject docsJsonObject = new JSONObject();
                        if (jsonObject.getString("name").split("\\.")[1].equals("jpg") || jsonObject.getString("name").split("\\.")[1].equals("jpeg") || jsonObject.getString("name").split("\\.")[1].equals("png")) {
                            fileImages.add("data:image/jpg;base64," + jsonObject.getString("base64") + "###");
                        }
                        docsJsonObject.put("name",jsonObject.getString("name"));
                        docsJsonObject.put("value",jsonObject.getString("base64"));
                        documentsArray.put(docsJsonObject);
                    }
                    insertInspectionJson.put("image", fileImages.toString());
                    insertInspectionJson.put("documents", documentsArray.toString());

                    insertArray.put(insertInspectionJson);
                    System.out.println(insertArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonInsertUtil.insertExecutionProjectInspectionFromJSON(insertArray, AddReviewPlaceDocs.this, "AddReview");
                Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                SpannableString efr = new SpannableString("بازدید ثبت شد");
                efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Toast.makeText(AddReviewPlaceDocs.this, efr, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddReviewPlaceDocs.this, MainActivity.class);
                startActivity(intent);
            }
        });
//        btnUploadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//        });

//        btnShowOnMap.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.P)
//            @Override
//            public void onClick(View v) {
//                mapView.getMapAsync(new OnMapReadyCallback() {
//                    @Override
//                    public void onMapReady(@NonNull MapboxMap mapboxMap) {
//
//                        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
//                            @Override
//                            public void onStyleLoaded(@NonNull Style style) {
//                                if(!edtLat.getText().toString().equals("") && !edtLng.getText().toString().equals("")){
//                                    wktFlag = 1;
//                                    pointWKTFromEdt = "POINT (("+edtLng.getText().toString() + " " + edtLat.getText().toString() + "))";
//                                    SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
//                                    symbolManager.setIconAllowOverlap(true);
//                                    symbolManager.setTextAllowOverlap(true);
//                                    SymbolOptions symbolOptions = new SymbolOptions().withLatLng(new LatLng(Double.parseDouble(edtLat.getText().toString()),Double.parseDouble(edtLng.getText().toString()))).withIconImage("marker-15").withIconSize(3f);
//                                    symbolManager.create(symbolOptions);
//                                    symbolManager.addClickListener(new OnSymbolClickListener() {
//                                        @Override
//                                        public boolean onAnnotationClick(Symbol symbol) {
//
//                                            return false;
//                                        }
//                                    });
//                                    CameraPosition position = new CameraPosition.Builder().target(new LatLng(Double.parseDouble(edtLat.getText().toString()),Double.parseDouble(edtLng.getText().toString()))).zoom(8).tilt(20).build();
//                                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000);
//                                }else{
//                                    Typeface font = Typeface.createFromAsset(AddReviewPlaceDocs.this.getAssets(), "fonts/IRANSansWeb.ttf");
//                                    SpannableString efr = new SpannableString("طول و عرض جغرافیایی را وارد کنید");
//                                    efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                    Toast.makeText(AddReviewPlaceDocs.this, efr, Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//                    }
//                });
//            }
//        });
//        btnDrawPolygon.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.P)
//            @Override
//            public void onClick(View v) {
//                mapView.getMapAsync(new OnMapReadyCallback() {
//                    @Override
//                    public void onMapReady(@NonNull MapboxMap mapboxMap) {
//
//                        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
//                            @Override
//                            public void onStyleLoaded(@NonNull Style style) {
//                                mapboxMap.setCameraPosition(new CameraPosition.Builder().target(new LatLng(32.4279, 53.6880)).zoom(4).build());
//
//                                mapboxMap.getUiSettings().setAttributionEnabled(false);
//                                mapboxMap.getUiSettings().setLogoEnabled(false);
//
//                                LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap, style);
//                                localizationPlugin.setMapLanguage(MapLocale.LOCAL_NAME);
//
//                                hoveringMarker = new ImageView(AddReviewPlaceDocs.this);
//                                hoveringMarker.setImageResource(R.drawable.marker);
//                                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
//                                hoveringMarker.setLayoutParams(params);
//                                mapView.addView(hoveringMarker);
//                                hoveringMarker.setOnClickListener(view -> {
//                                    style.removeLayer(String.valueOf("layerId"));
//                                    style.removeSource(String.valueOf("sourceId"));
//                                    LatLng mapTargetLatLng = mapboxMap.getCameraPosition().target;
//                                    OUTER_POINTS.add(Point.fromLngLat(mapTargetLatLng.getLongitude(), mapTargetLatLng.getLatitude()));
//
//                                    polygonLatLng.add(mapTargetLatLng.getLongitude() + " " +mapTargetLatLng.getLatitude());
//
//                                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
//                                    SpannableString efr = new SpannableString("انتخاب شد");
//                                    efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                    Toast.makeText(AddReviewPlaceDocs.this, efr, Toast.LENGTH_SHORT).show();
//
//                                });
//
//                            }
//                        });
//                    }
//                });
//            }
//        });
//        btnStopAddMarker.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.P)
//            @Override
//            public void onClick(View v) {
//                if(OUTER_POINTS.size()>0) {
//                    mapView.getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(@NonNull MapboxMap mapboxMap) {
//
//                            mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
//                                @Override
//                                public void onStyleLoaded(@NonNull Style style) {
//                                    wktFlag = 2;
//                                    OUTER_POINTS.add(OUTER_POINTS.get(0));
//                                    POINTS.add(OUTER_POINTS);
//                                    style.addSource(new GeoJsonSource(String.valueOf("sourceId"), Polygon.fromLngLats(POINTS)));
//                                    style.addLayerBelow(new FillLayer(String.valueOf("layerId"), String.valueOf("sourceId")).withProperties(
//                                            fillColor(Color.parseColor("#D62F2F"))), "settlement-label"
//                                    );
//                                    polygonLatLng.add(polygonLatLng.get(0));
//                                    polygonWKT = "POLYGON ((" + polygonLatLng.toString().substring(1, polygonLatLng.toString().length()-1) + "))";
//                                    System.out.println(polygonWKT);
////                                    for(int i = 0;i<polygonLatLng.size();i++){
////                                        String latlng = polygonLatLng.get(i)
////                                        polygonWKT = "POLYGON (("+
////                                    }
//
//                                    OUTER_POINTS.clear();
//                                    POINTS.clear();
//                                    mapView.removeView(hoveringMarker);
//                                    polygonLatLng.clear();
//
//                                }
//                            });
//                        }
//                    });
//                }else if(OUTER_POINTS.size()==0){
//                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
//                    SpannableString efr = new SpannableString("نقاط مورد نظر را انتخاب کنید");
//                    efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    Toast.makeText(AddReviewPlaceDocs.this, efr, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        btnAddOnePoint.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.P)
//            @Override
//            public void onClick(View v) {
//                    mapView.getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(@NonNull MapboxMap mapboxMap) {
//
//                            mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
//                                @Override
//                                public void onStyleLoaded(@NonNull Style style) {
//                                    OUTER_POINTS.clear();
//                                    POINTS.clear();
//                                    mapView.removeView(hoveringMarker);
//                                    if(style.getSource(String.valueOf("sourceId"))!=null){
//                                        style.removeLayer(String.valueOf("layerId"));
//                                        style.removeSource(String.valueOf("sourceId"));
//                                    }
//
//
//                                    mapboxMap.setCameraPosition(new CameraPosition.Builder().target(new LatLng(32.4279, 53.6880)).zoom(4).build());
//
//                                    mapboxMap.getUiSettings().setAttributionEnabled(false);
//                                    mapboxMap.getUiSettings().setLogoEnabled(false);
//
//                                    LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap, style);
//                                    localizationPlugin.setMapLanguage(MapLocale.LOCAL_NAME);
//
//                                    hoveringMarker = new ImageView(AddReviewPlaceDocs.this);
//                                    hoveringMarker.setImageResource(R.drawable.marker);
//                                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
//                                    hoveringMarker.setLayoutParams(params);
//                                    mapView.addView(hoveringMarker);
//                                    hoveringMarker.setOnClickListener(view -> {
//                                        wktFlag = 3;
//                                        style.removeLayer(String.valueOf("layerId"));
//                                        style.removeSource(String.valueOf("sourceId"));
//                                        LatLng mapTargetLatLng = mapboxMap.getCameraPosition().target;
//                                        onePointLat = mapTargetLatLng.getLatitude();
//                                        onePointLng = mapTargetLatLng.getLongitude();
//
//                                        pointWKTByMarker = "POINT (("+onePointLng.toString() + " " + onePointLat.toString() + "))";
//
//                                        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
//                                        SpannableString efr = new SpannableString("مکان مورد نظرانتخاب شد");
//                                        efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                        Toast.makeText(AddReviewPlaceDocs.this, efr, Toast.LENGTH_SHORT).show();
//
//                                    });
//                                }
//                            });
//                        }
//                    });
//
//            }
//        });
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("deleteFile"));
        setProvinceSpinners();
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf/jpg/image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String id = intent.getStringExtra("id");
            for (int i = 0; i < relativeLayoutList.length(); i++) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = relativeLayoutList.getJSONObject(i);
                    if (jsonObject.getString("id").equals(id)) {
                        relativeLayoutList.remove(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            File file = new File(filePath.toString());
            String fileName = file.getName();
            System.out.println("onActivityResult: uri" + fileName.toString());
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream in = getContentResolver().openInputStream(filePath);

                byte[] bytes = getBytes(in);
                Log.d("data", "onActivityResult: bytes size=" + bytes.length);
                System.out.println("onActivityResult: base64" + Base64.encodeToString(bytes, Base64.DEFAULT));

                LayoutInflater inflater = LayoutInflater.from(AddReviewPlaceDocs.this);

                View view = inflater.inflate(R.layout.files_layout, null);
                txtName = (TextView) view.findViewById(R.id.txtChooseFile);
                btnDelete = (AppCompatButton) view.findViewById(R.id.btnDelete);

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("name", fileName.toString());
                if (relativeLayoutList.length() == 0) {
                    jsonObj.put("id", 0);
                } else
                jsonObj.put("id", String.valueOf(relativeLayoutList.length()));
                jsonObj.put("base64", Base64.encodeToString(bytes, Base64.DEFAULT));

                relativeLayoutList.put(jsonObj);
                System.out.println("relativeLayoutList" + relativeLayoutList);

                filesLayoutArrayList.clear();
                for (int i = 0; i < relativeLayoutList.length(); i++) {
                    JSONObject jsonObject = relativeLayoutList.getJSONObject(i);
                    FilesLayout filesLayout = new FilesLayout();
                    filesLayout.setId(jsonObject.getString("id"));
                    filesLayout.setName(jsonObject.getString("name"));
                    filesLayout.setBase64(jsonObject.getString("base64"));
                    System.out.println(fileImages);
                    filesLayoutArrayList.add(filesLayout);
                    System.out.println("mmm" + " " + filesLayoutArrayList);
                    filesRecyclerAdapter = new FilesRecyclerAdapter(AddReviewPlaceDocs.this, filesLayoutArrayList);
                    recyclerView.setAdapter(filesRecyclerAdapter);
                }
                recyclerView.setLayoutManager(new GridLayoutManager(AddReviewPlaceDocs.this, 1, LinearLayoutManager.VERTICAL, false));
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                Log.d("error", "onActivityResult: " + e.toString());
            }
            if (filePath.toString() != null) {
                Log.d("Path: ", filePath.toString());
                pdfPath = filePath.toString();
                String filename = pdfPath.substring(pdfPath.lastIndexOf("/") + 1);
                filename = filename.replace("%20", " ");
            }
            dialog.dismiss();
        } else if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {

            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                JSONObject jsonObj = new JSONObject();

                if (relativeLayoutList.length() == 0) {
                    jsonObj.put("id", 0);
                } else
                    jsonObj.put("id", String.valueOf(relativeLayoutList.length()));

                Random randomGenerator = new Random();
                long newimagename = randomGenerator.nextInt((11111 + 1 - 1111) + 1111);
                File f = new File(Environment.getExternalStorageDirectory()
                        + File.separator + newimagename);
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String fileName = f.getName();
                String encodedImage = encodeImage(photo);
                jsonObj.put("name", newimagename + ".jpg");
                jsonObj.put("base64", encodedImage);
                relativeLayoutList.put(jsonObj);
                System.out.println("relativeLayoutList" + relativeLayoutList);

                filesLayoutArrayList.clear();
                for (int i = 0; i < relativeLayoutList.length(); i++) {
                    JSONObject jsonObject = relativeLayoutList.getJSONObject(i);
                    FilesLayout filesLayout = new FilesLayout();
                    filesLayout.setId(jsonObject.getString("id"));
                    filesLayout.setName(jsonObject.getString("name"));
                    filesLayout.setBase64(jsonObject.getString("base64"));
                    System.out.println(fileImages);
                    filesLayoutArrayList.add(filesLayout);
                    System.out.println("mmm" + " " + filesLayoutArrayList);
                    filesRecyclerAdapter = new FilesRecyclerAdapter(AddReviewPlaceDocs.this, filesLayoutArrayList);
                    recyclerView.setAdapter(filesRecyclerAdapter);
                }
                recyclerView.setLayoutManager(new GridLayoutManager(AddReviewPlaceDocs.this, 1, LinearLayoutManager.VERTICAL, false));
            } catch (Exception e) {

            }
            dialog.dismiss();

            //write the bytes in file

//                try {
//                    fo = new FileOutputStream(f.getAbsoluteFile());
//                } catch (FileNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                try {
//                    fo.write(bytes.toByteArray());
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                uri=f.getAbsolutePath();
//                //this is the url that where you are saved the image
//            }
        }
    }


    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public void setProvinceSpinners() {
        String[] provincesArray = getResources().getStringArray(R.array.spnProvinces);
        if (provincesArray != null && provincesArray.length > 0) {
            String[] names = new String[provincesArray.length];
            String[] longitude = new String[provincesArray.length];
            String[] latitude = new String[provincesArray.length];
            // Now we will parse the records and split them into name and value
            for (int i = 0; i < provincesArray.length; i++) {
                String provinces = provincesArray[i];
                if (provinces == null || provinces.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameLatLng = provinces.split("-");
                if (nameLatLng.length < 2) {
                    continue;
                }
                names[i] = nameLatLng[0];
                longitude[i] = nameLatLng[2];
                latitude[i] = nameLatLng[1];
                System.out.println(longitude + "*//*" + latitude);
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReviewPlaceDocs.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnProvinces.setAdapter(adapter);
            int spinnerPosition = adapter.getPosition("اصفهان");
            spnProvinces.setSelection(spinnerPosition);
            spnProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    double lat = 0;
                    double lng = 0;
                    try {
                        lat = Double.parseDouble(latitude[position]); // Here you have value as numeric type
                        lng = Double.parseDouble(longitude[position]); // Here you have value as numeric type
                        System.out.println(lat + "*//*" + lng);
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }
                    double finalLat = lat;
                    double finalLng = lng;

                    mapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull MapboxMap mapboxMap) {
                            mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                                @Override
                                public void onStyleLoaded(@NonNull Style style) {
                                    LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap , style);
                                    localizationPlugin.setMapLanguage(MapLocale.LOCAL_NAME);
                                    CameraPosition position = new CameraPosition.Builder().target(new LatLng(finalLat, finalLng)).zoom(7).tilt(20).build();
                                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000);
                                }
                            });

                        }
                    });

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
        }
    }

    @SuppressWarnings({"MissingPermission"})
    public void zoomToMyLocation(View view) {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @SuppressWarnings({"MissingPermission"})
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap , style);
                        localizationPlugin.setMapLanguage(MapLocale.LOCAL_NAME);

                        if (mapboxMap != null && mapboxMap.getStyle() != null) {
                            enableLocationComponent(mapboxMap.getStyle(), mapboxMap);

                            final Handler handler = new Handler();
                            handler.postDelayed(() -> {

                                if (locationComponent != null && locationComponent.getLastKnownLocation() != null) {
                                    try {
                                        insertInspectionJson.put("wkt", "POINT ((" + locationComponent.getLastKnownLocation().getLongitude() + " " + locationComponent.getLastKnownLocation().getLatitude() + "))");
                                        insertInspectionJson.put("lat", locationComponent.getLastKnownLocation().getLatitude());
                                        insertInspectionJson.put("lng", locationComponent.getLastKnownLocation().getLongitude());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    mapboxMap.setCameraPosition(new CameraPosition.Builder().target(new LatLng(locationComponent.getLastKnownLocation().getLatitude(), locationComponent.getLastKnownLocation().getLongitude())).zoom(Math.max(15, mapboxMap.getCameraPosition().zoom)).build());
                                }
                            }, 2000);
                        }
                    }
                });
            }
        });

    }

    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle, @NonNull MapboxMap mapboxMap) {
        // Create and customize the LocationComponent's options
        LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(this)
                .elevation(5)
                .accuracyAlpha(.6f)
                .accuracyColor(Color.RED)
                .foregroundTintColor(Color.BLUE)
                .build();

        // Get an instance of the component
        locationComponent = mapboxMap.getLocationComponent();

        LocationComponentActivationOptions locationComponentActivationOptions =
                LocationComponentActivationOptions.builder(this, loadedMapStyle)
                        .locationComponentOptions(customLocationComponentOptions)
                        .build();

        // Activate with options
        locationComponent.activateLocationComponent(locationComponentActivationOptions);

        // Enable to make component visible
        locationComponent.setLocationComponentEnabled(true);

        // Set the component's camera mode
        locationComponent.setCameraMode(CameraMode.TRACKING_GPS);

        // Set the component's render mode
        locationComponent.setRenderMode(RenderMode.GPS);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        System.out.println("OOPP" + b);
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
//        return encodeToString(b);
        return encImage;
    }

}
