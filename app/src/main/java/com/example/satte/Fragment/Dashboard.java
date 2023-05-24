package com.example.satte.Fragment;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.satte.Adapters.ProjectExpansionRecyclerAdapter;
import com.example.satte.DataModels.ProjectExpansionLayout;
import com.example.satte.R;
import com.example.satte.db.service.ProjectLocalServiceUtil;
import com.example.satte.db.tables.Project;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.plugins.localization.LocalizationPlugin;
import com.mapbox.mapboxsdk.plugins.localization.MapLocale;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard extends Fragment {
    private MapView mapView;
    private int finishedProjectsCount=0;
    private int doingProjectsCount=0;
    private int stoppedProjectsCount=0;
    private int notStartedProjectsCount=0;
    TextView txtShowAllProjects,txtFinishedCount,txtDoingCount,txtStoppedCount,txtNotStartedCount,txtAllProjects;
    AppCompatButton btnFinished,btnDoing,btnStopped,btnNotStarted;
    ArrayList<ProjectExpansionLayout> projectExpansionLayoutArrayList;
    ProjectExpansionRecyclerAdapter projectExpansionRecyclerAdapter;
    RecyclerView recyclerView;
    private static final List<Double> projectsLatLng = new ArrayList<>();
    private static final List<List<Double>> projectsCenter = new ArrayList<>();
    private static final List<List<Point>> POINTS = new ArrayList<>();
    private static final List<Point> OUTER_POINTS = new ArrayList<>();
    ArrayList<String> points = new ArrayList();
    String[] newPoints;

    public Dashboard() {
    }
    public static Dashboard newInstance(String param1, String param2) {
        Dashboard fragment = new Dashboard();

        return fragment;
    }
    public static Fragment newInstance() {
        Dashboard dashboard = new Dashboard();
        return dashboard;
    }
    public static Fragment getWKTInstance(ArrayList<String> arrayList) {
        Dashboard dashboard = new Dashboard();
        return dashboard;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Mapbox.getInstance(getActivity(), getString(R.string.mapbox_access_token));
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        txtAllProjects = (TextView) view.findViewById(R.id.txtAllProjects);
        txtShowAllProjects = (TextView) view.findViewById(R.id.txtShowAllProjects);
        txtFinishedCount = (TextView) view.findViewById(R.id.txtModifiedDate);
        txtDoingCount = (TextView) view.findViewById(R.id.txtDoingCount);
        txtStoppedCount = (TextView)view.findViewById(R.id.txtStoppedCount);
        txtNotStartedCount = (TextView)view.findViewById(R.id.txtNotStartedCount);
        btnFinished = (AppCompatButton) view.findViewById(R.id.btnFinished);
        btnDoing = (AppCompatButton)view.findViewById(R.id.btnDoing);
        btnStopped = (AppCompatButton)view.findViewById(R.id.btnStopped);
        btnNotStarted = (AppCompatButton)view.findViewById(R.id.btnNotStarted);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        projectExpansionLayoutArrayList = new ArrayList<>();
        mapView = (MapView)view.findViewById(R.id.mapView);
        getProject("fromOnCreate");
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap , style);
                        localizationPlugin.setMapLanguage(MapLocale.LOCAL_NAME);

                        ProjectLocalServiceUtil projectLocalServiceUtil = new ProjectLocalServiceUtil(getActivity());
                        List<Project> project = projectLocalServiceUtil.getProjects();

                        for (int i = 0; i < project.size(); i++) {
                            final String wkt;
                            final double lat,lng;
                            final long sourceId,layerId;
                            wkt = project.get(i).getWkt();
                            lat = project.get(i).getLat();
                            lng = project.get(i).getLng();
                            sourceId = project.get(i).getSourceid();
                            layerId = project.get(i).getProjectcode();
                            SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
                            symbolManager.setIconAllowOverlap(true);
                            symbolManager.setTextAllowOverlap(true);
                            SymbolOptions symbolOptions = new SymbolOptions().withLatLng(new LatLng(project.get(i).getLat(),project.get(i).getLng())).withIconImage("marker-15").withIconSize(3f);
                            symbolManager.create(symbolOptions);
                            symbolManager.addClickListener(new OnSymbolClickListener() {
                                @Override
                                public boolean onAnnotationClick(Symbol symbol) {
                                    ArrayList<String> points = new ArrayList();
                                    String sa,sa1,sa2,sa3,sa4;
                                    sa = wkt;
                                    sa1 = sa.replaceAll("POLYGON ","").replaceAll("POINT ","");
                                    sa2 = sa1.replaceAll("[()]","");
                                    sa3 = sa2.replaceAll(", ","#");
                                    sa4 = sa3.replaceAll(" ",",");
                                    String[] splitString = sa4.split( "#" );
                                    for (int i = 0;i<splitString.length;i++ )
                                    {
                                        points.add(splitString[i]);
                                    }
                                    OUTER_POINTS.clear();
                                    POINTS.clear();
                                    for(int i = 0;i<points.size();i++){
                                        newPoints = points.get(i).split( "," );
                                        OUTER_POINTS.add(Point.fromLngLat(Double.parseDouble(newPoints[0]), Double.parseDouble(newPoints[1])));
                                        POINTS.add(OUTER_POINTS);
                                    }
                                    if(OUTER_POINTS.size()>1) {
                                        mapboxMap.getStyle(new Style.OnStyleLoaded() {
                                            @Override
                                            public void onStyleLoaded(@NonNull Style style) {

                                                if(style.getSource(String.valueOf(sourceId))!=null){
                                                    style.removeLayer(String.valueOf(layerId));
                                                    style.removeSource(String.valueOf(sourceId));
                                                }else{
                                                    style.removeLayer(String.valueOf(layerId));
                                                    style.addSource(new GeoJsonSource(String.valueOf(sourceId), Polygon.fromLngLats(POINTS)));
                                                    style.addLayerBelow(new FillLayer(String.valueOf(layerId), String.valueOf(sourceId)).withProperties(
                                                            fillColor(Color.parseColor("#D62F2F"))), "settlement-label"
                                                    );
                                                }
                                            }
                                        });
                                    }
                                    CameraPosition position = new CameraPosition.Builder().target(new LatLng(lat, lng)).zoom(12).tilt(20).build();
                                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000);
                                    return false;
                                }
                            });
                        }
                        CameraPosition position = new CameraPosition.Builder().target(new LatLng(32.6539, 51.6660)).zoom(8).tilt(20).build();
                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000);
                    }
                });
            }
        });
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("ShowOnMap"));
        txtShowAllProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectExpansionLayoutArrayList.clear();
                getProject("fromTxt");
                txtAllProjects.setText("لیست همه پروژه ها");
            }
        });
        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectExpansionLayoutArrayList.clear();
                getProjectByState("خاتمه یافته");
                txtAllProjects.setText("پروژه های خاتمه یافته");
            }
        });
        btnDoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectExpansionLayoutArrayList.clear();
                getProjectByState("در دست اجرا");
                txtAllProjects.setText("پروژه های در دست اجرا");
            }
        });
        btnStopped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectExpansionLayoutArrayList.clear();
                getProjectByState("متوقف شده");
                txtAllProjects.setText("پروژه های متوقف شده");
            }
        });
        btnNotStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectExpansionLayoutArrayList.clear();
                getProjectByState("شروع نشده");
                txtAllProjects.setText("پروژه های شروع نشده");
            }
        });
        return view;
    }
    private void getProject(String from){
        ProjectLocalServiceUtil projectLocalServiceUtil = new ProjectLocalServiceUtil(getActivity());
        List<Project> project = projectLocalServiceUtil.getProjects();
        System.out.println("size" + project.size());
        for(int i = 0;i<project.size();i++){
            ProjectExpansionLayout expansionLayout = new ProjectExpansionLayout();
            expansionLayout.setLocalprojectid(project.get(i).getLocalprojectid());
            expansionLayout.setTitle(project.get(i).getTitle());
            expansionLayout.setCreatedate(project.get(i).getCreatedate());
            expansionLayout.setMasterpersonname(project.get(i).getMasterpersonname());
            expansionLayout.setProjectcode(project.get(i).getProjectcode());
            expansionLayout.setBahrebardarpersonname(project.get(i).getBahrebardarpersonname());
            expansionLayout.setProjecttype(project.get(i).getProjecttype());
            expansionLayout.setTargetname(project.get(i).getTargetname());
            expansionLayout.setProjectstatus(project.get(i).getProjectstatus());
            expansionLayout.setCreditAllocation(project.get(i).getCreditAllocation());
            expansionLayout.setCreditExchanged(project.get(i).getCreditExchanged());
            expansionLayout.setProjectcredit(project.get(i).getProjectcredit());
            expansionLayout.setPeriodtime(project.get(i).getPeriodtime());
            expansionLayout.setWkt(project.get(i).getWkt());
            expansionLayout.setLat(project.get(i).getLat());
            expansionLayout.setLng(project.get(i).getLng());
            projectExpansionLayoutArrayList.add(expansionLayout);
            projectExpansionRecyclerAdapter = new ProjectExpansionRecyclerAdapter(getActivity(), projectExpansionLayoutArrayList);
            recyclerView.setAdapter(projectExpansionRecyclerAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
            if(from.equals("fromOnCreate")){
                if(project.get(i).getProjectimplementationstatus().equals("خاتمه یافته")){
                    finishedProjectsCount ++;
                    txtFinishedCount.setText(String.valueOf(finishedProjectsCount));
                }else if(project.get(i).getProjectimplementationstatus().equals("در دست اجرا")){
                    doingProjectsCount ++;
                    txtDoingCount.setText(String.valueOf(doingProjectsCount));
                }else if(project.get(i).getProjectimplementationstatus().equals("متوقف شده")){
                    stoppedProjectsCount ++;
                    txtStoppedCount.setText(String.valueOf(stoppedProjectsCount));
                }else if(project.get(i).getProjectimplementationstatus().equals("شروع نشده")){
                    notStartedProjectsCount ++;
                    txtNotStartedCount.setText(String.valueOf(notStartedProjectsCount));
                }
            }
        }
    }
    private void getProjectByState(String state){
        ProjectLocalServiceUtil projectLocalServiceUtil = new ProjectLocalServiceUtil(getActivity());
        List<Project> project = projectLocalServiceUtil.getProjectsByState("projectimplementationstatus",state);
        System.out.println(project.size());
        if(project.size()>0){
            for(int i = 0;i<project.size();i++){
                ProjectExpansionLayout expansionLayout = new ProjectExpansionLayout();
                expansionLayout.setLocalprojectid(project.get(i).getLocalprojectid());
                expansionLayout.setTitle(project.get(i).getTitle());
                expansionLayout.setCreatedate(project.get(i).getCreatedate());
                expansionLayout.setMasterpersonname(project.get(i).getMasterpersonname());
                expansionLayout.setProjectcode(project.get(i).getProjectcode());
                expansionLayout.setBahrebardarpersonname(project.get(i).getBahrebardarpersonname());
                expansionLayout.setProjecttype(project.get(i).getProjecttype());
                expansionLayout.setTargetname(project.get(i).getTargetname());
                expansionLayout.setProjectstatus(project.get(i).getProjectstatus());
                expansionLayout.setCreditAllocation(project.get(i).getCreditAllocation());
                expansionLayout.setCreditExchanged(project.get(i).getCreditExchanged());
                expansionLayout.setProjectcredit(project.get(i).getProjectcredit());
                expansionLayout.setPeriodtime(project.get(i).getPeriodtime());
                expansionLayout.setWkt(project.get(i).getWkt());
                expansionLayout.setLat(project.get(i).getLat());
                expansionLayout.setLng(project.get(i).getLng());
                projectExpansionLayoutArrayList.add(expansionLayout);
                projectExpansionRecyclerAdapter = new ProjectExpansionRecyclerAdapter(getActivity(), projectExpansionLayoutArrayList);
                recyclerView.setAdapter(projectExpansionRecyclerAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
            }
        }else if(project.size()==0){
            projectExpansionLayoutArrayList.clear();
            projectExpansionRecyclerAdapter = new ProjectExpansionRecyclerAdapter(getActivity(), projectExpansionLayoutArrayList);
            recyclerView.setAdapter(projectExpansionRecyclerAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
        }

    }
    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            double centerLat= intent.getDoubleExtra("centerLat",0);
            double centerLng= intent.getDoubleExtra("centerLng",0);
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull MapboxMap mapboxMap) {
                    mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                        @Override
                        public void onStyleLoaded(@NonNull Style style) {
                            CameraPosition position = new CameraPosition.Builder().target(new LatLng(centerLat, centerLng)).zoom(10).tilt(20).build();
                            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000);
                        }
                    });

                }
            });
            System.out.println(POINTS);
        }
    };

}
