package com.example.satte.Adapters;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.satte.DataModels.ProjectReviewExpansionLayout;
import com.example.satte.R;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.plugins.localization.LocalizationPlugin;
import com.mapbox.mapboxsdk.plugins.localization.MapLocale;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.smarteist.autoimageslider.SliderView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;

public class ProjectReviewExpansionRecyclerAdapter extends RecyclerView.Adapter<ProjectReviewExpansionRecyclerAdapter.ProjectReviewExpansionViewHolder> {
    ArrayList<ProjectReviewExpansionLayout> projectReviewExpansionLayouts;
    private static final List<List<Point>> POINTS = new ArrayList<>();
    private static final List<Point> OUTER_POINTS = new ArrayList<>();
    String[] newPoints;
    String[] images;
    RecyclerView recyclerView;
    private MapView mapView;
    Dialog dialog;
    private Context context;
    private int lastPosition = -1;


    public ProjectReviewExpansionRecyclerAdapter(Context context, ArrayList<ProjectReviewExpansionLayout> arrayList) {
        projectReviewExpansionLayouts = new ArrayList<ProjectReviewExpansionLayout>();
        projectReviewExpansionLayouts = arrayList;
    }

    @NonNull
    @Override
    public ProjectReviewExpansionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_review_expansion_layout, parent, false);

        context = parent.getContext();
        return new ProjectReviewExpansionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectReviewExpansionViewHolder holder, final int position) {
        ProjectReviewExpansionLayout dataModel = projectReviewExpansionLayouts.get(position);
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.project_review_detail_dialog_layout);

        System.out.println(dataModel.getPhysicalprogressinspectiontime());
        holder.btnShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtRialProgress = (TextView) dialog.findViewById(R.id.txtRialProgress);
                TextView txtPercentageProgressFirstYear = (TextView) dialog.findViewById(R.id.txtPercentageProgressFirstYear);
                TextView txtPhysicalProgressInspectionTime = (TextView) dialog.findViewById(R.id.txtPhysicalProgressInspectionTime);
                TextView txtExecutionStatus = (TextView) dialog.findViewById(R.id.txtExecutionStatus);
                TextView txtPerformanceQuality = (TextView) dialog.findViewById(R.id.txtPerformanceQuality);
                TextView txtPhysicalProgressType = (TextView) dialog.findViewById(R.id.txtPhysicalProgressType);
                TextView txtExpressingOpinionsProviding = (TextView) dialog.findViewById(R.id.txtExpressingOpinionsProviding);
                TextView txtHowReferWork = (TextView) dialog.findViewById(R.id.txtHowReferWork);
                TextView txtContractRateBasics = (TextView) dialog.findViewById(R.id.txtContractRateBasics);
                TextView txtTechnicalSpecifications = (TextView) dialog.findViewById(R.id.txtTechnicalSpecifications);
                TextView txtOperationVolume = (TextView) dialog.findViewById(R.id.txtOperationVolume);
                TextView txtPractical = (TextView) dialog.findViewById(R.id.txtPractical);
                AppCompatButton btnNext = (AppCompatButton) dialog.findViewById(R.id.btnNext);
                AppCompatButton btnPrev = (AppCompatButton) dialog.findViewById(R.id.btnPrev);


                txtRialProgress.setText(String.valueOf(dataModel.getRialprogress()));
                txtPercentageProgressFirstYear.setText(String.valueOf(dataModel.getPercentageprogressfirstyear()));
                txtPhysicalProgressInspectionTime.setText(String.valueOf(dataModel.getPhysicalprogressinspectiontime()));
                txtExecutionStatus.setText(dataModel.getExecutionstatus());
                txtPerformanceQuality.setText(dataModel.getPerformancequality());
                txtPhysicalProgressType.setText(dataModel.getPhysicalprogresstype());
                txtExpressingOpinionsProviding.setText(String.valueOf(dataModel.getExpressingopinionsproviding()));
                txtHowReferWork.setText(String.valueOf(dataModel.getHowreferwork()));
                txtContractRateBasics.setText(String.valueOf(dataModel.getContractratebasics()));
                txtTechnicalSpecifications.setText(String.valueOf(dataModel.getTechnicalspecifications()));
                txtOperationVolume.setText(String.valueOf(dataModel.getOperationvolume()));
                txtPractical.setText(String.valueOf(dataModel.getPractical()));

                ViewFlipper viewFlipper = (ViewFlipper) dialog.findViewById(R.id.slider);
                if(!dataModel.getImage().equals("")) {
                    images = dataModel.getImage().split("###");
                    viewFlipper.removeAllViews();

                    for (int i = 0; i < images.length; i++) {
                        byte[] decodedString = Base64.decode(images[i].replace("data:image/jpg;base64,", ""), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        ImageView image = new ImageView(context);
                        image.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, 1700, 1200, false));
                        viewFlipper.addView(image);
                    }
                    viewFlipper.setFlipInterval(5000); //5s intervals
                    viewFlipper.startFlipping();
                }
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (images.length > 0)
                            viewFlipper.showNext();
                    }
                });
                btnPrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (images.length > 0)
                            viewFlipper.showPrevious();
                    }
                });

                mapView = (MapView) dialog.findViewById(R.id.mapView);
                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull MapboxMap mapboxMap) {

                        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                LocalizationPlugin localizationPlugin = new LocalizationPlugin(mapView, mapboxMap , style);
                                localizationPlugin.setMapLanguage(MapLocale.LOCAL_NAME);

                                ArrayList<String> points = new ArrayList();
                                String sa, sa1, sa2, sa3, sa4;
                                sa = dataModel.getWkt();
                                if (!sa.equals("")) {
                                    sa1 = sa.replaceAll("POLYGON ", "").replaceAll("POINT ", "");
                                    sa2 = sa1.replaceAll("[()]", "");
                                    sa3 = sa2.replaceAll(", ", "#");
                                    sa4 = sa3.replaceAll(" ", ",");
                                    String[] splitString = sa4.split("#");
                                    for (int i = 0; i < splitString.length; i++) {
                                        points.add(splitString[i]);
                                    }
                                    OUTER_POINTS.clear();
                                    POINTS.clear();
                                    for (int i = 0; i < points.size(); i++) {
                                        newPoints = points.get(i).split(",");
                                        OUTER_POINTS.add(Point.fromLngLat(Double.parseDouble(newPoints[0]), Double.parseDouble(newPoints[1])));
                                        POINTS.add(OUTER_POINTS);
                                    }
                                    if (OUTER_POINTS.size() > 1) {
                                        if (style.getSource(String.valueOf("sourceId")) != null) {
                                            style.removeLayer(String.valueOf("layerId"));
                                            style.removeSource(String.valueOf("sourceId"));
                                        } else {
                                            style.removeLayer(String.valueOf("layerId"));
                                            style.addSource(new GeoJsonSource("sourceId", Polygon.fromLngLats(POINTS)));
                                            style.addLayerBelow(new FillLayer("layerId", "sourceId").withProperties(
                                                    fillColor(Color.parseColor("#D62F2F"))), "settlement-label"
                                            );
                                        }
                                        CameraPosition position = new CameraPosition.Builder().target(new LatLng(dataModel.getLat(), dataModel.getLng())).zoom(6).tilt(20).build();
                                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000);
                                    } else if (OUTER_POINTS.size() == 1) {
                                        SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
                                        symbolManager.setIconAllowOverlap(true);
                                        symbolManager.setTextAllowOverlap(true);
                                        SymbolOptions symbolOptions = new SymbolOptions().withLatLng(new LatLng(dataModel.getLat(), dataModel.getLng())).withIconImage("marker-15").withIconSize(3f);
                                        symbolManager.create(symbolOptions);
                                        CameraPosition position = new CameraPosition.Builder().target(new LatLng(dataModel.getLat(), dataModel.getLng())).zoom(6).tilt(20).build();
                                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000);
                                    }
                                }
                            }
                        });
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
        holder.txtProjectCode.setText(String.valueOf(dataModel.getProjectcode()));
        holder.txtUserName.setText(dataModel.getUsername());
        holder.txtModifiedDate.setText(dataModel.getModifieddate());
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return projectReviewExpansionLayouts.size();
    }

    public class ProjectReviewExpansionViewHolder extends RecyclerView.ViewHolder {
        AppCompatButton btnShowDetail;
        TextView txtUserName, txtProjectCode, txtModifiedDate;
        public int id;

        public ProjectReviewExpansionViewHolder(View itemView) {
            super(itemView);
            btnShowDetail = (AppCompatButton) itemView.findViewById(R.id.btnShowDetail);
            txtUserName = (TextView) itemView.findViewById(R.id.txtUserName);
            txtProjectCode = (TextView) itemView.findViewById(R.id.txtProjectCode);
            txtModifiedDate = (TextView) itemView.findViewById(R.id.txtModifiedDate);

        }
    }
}
