package com.example.satte.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.satte.DataModels.ProjectExpansionLayout;
import com.example.satte.DataModels.ProjectReviewExpansionLayout;
import com.example.satte.R;
import com.example.satte.db.service.ExecutionProjectInspectionLocalServiceUtil;
import com.example.satte.db.tables.ExecutionProjectInspection;
import com.github.florent37.expansionpanel.ExpansionHeader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectExpansionRecyclerAdapter extends RecyclerView.Adapter<ProjectExpansionRecyclerAdapter.ProjectExpansionViewHolder> {
    ArrayList<ProjectExpansionLayout> projectExpansionLayoutsArrayList;
    RecyclerView recyclerView;
    Dialog dialog;
    private Context context;
    ArrayList<ProjectReviewExpansionLayout> projectReviewExpansionLayoutArrayList;
    ProjectReviewExpansionRecyclerAdapter projectReviewExpansionRecyclerAdapter;
    private int lastPosition = -1;


    public ProjectExpansionRecyclerAdapter(Context context, ArrayList<ProjectExpansionLayout> arrayList) {
        projectExpansionLayoutsArrayList = new ArrayList<ProjectExpansionLayout>();
        projectExpansionLayoutsArrayList = arrayList;
    }


    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }

    @NonNull
    @Override
    public ProjectExpansionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_expansion_layout, parent, false);
        context = parent.getContext();
        return new ProjectExpansionViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ProjectExpansionViewHolder holder, final int position) {
        final ProjectExpansionLayout dataModel = projectExpansionLayoutsArrayList.get(position);
        ArrayList<String> points = new ArrayList();

        holder.btnShowOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points.clear();
                double centerLat = dataModel.getLat();
                double centerLng = dataModel.getLng();

                Intent intent = new Intent("ShowOnMap");
                SharedPreferences pref = context.getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                Set<String> set = new HashSet<String>();
                set.addAll(points);
                editor.putStringSet("points", set);
                editor.commit();
                //////////////////////////////////
                System.out.println(centerLat + "$$$" + centerLng);
                intent.putExtra("centerLat" , centerLat);
                intent.putExtra("centerLng" , centerLng);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
        holder.txtProjectId.setText(String.valueOf(dataModel.getLocalprojectid()));
        holder.txtTitle.setText(dataModel.getTitle());
        holder.txtCreateDate.setText(String.valueOf(dataModel.getCreatedate()));
        holder.txtMasterPersonName.setText(dataModel.getMasterpersonname());
        holder.txtProjectCode.setText(String.valueOf(dataModel.getProjectcode()));
        holder.txtBahreBardarPersonName.setText(dataModel.getBahrebardarpersonname());
        holder.txtProjectType.setText(dataModel.getProjecttype());
        holder.txtTargetName.setText(dataModel.getTargetname());
        holder.txtProjectStatus.setText(dataModel.getProjectstatus());
        holder.txtAlllocationlastyeartotal.setText(String.valueOf(dataModel.getAlllocationlastyeartotal()));
        holder.txtCreditExchanged.setText(String.valueOf(dataModel.getCreditExchanged()));
        holder.txtProjectCredit.setText(String.valueOf(dataModel.getProjectcredit()));
        holder.txtPeriodTime.setText(String.valueOf(dataModel.getPeriodtime()));
        holder.btnReviewList.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.project_review_list_detail_dialog_layout);
                RecyclerView recyclerView = (RecyclerView)dialog.findViewById(R.id.dialogRecyclerView);

                projectReviewExpansionLayoutArrayList = new ArrayList<>();

                    ExecutionProjectInspectionLocalServiceUtil executionProjectInspectionLocalServiceUtil = new ExecutionProjectInspectionLocalServiceUtil(context);
                    List<ExecutionProjectInspection> executionProjectInspection = executionProjectInspectionLocalServiceUtil.getExecutionProjectInspectionByProjectCode("projectcode",dataModel.getProjectcode());
                    if(executionProjectInspection.size()>0){
                        for(int i = 0;i<executionProjectInspection.size();i++){
                            ProjectReviewExpansionLayout projectReviewExpansionLayout = new ProjectReviewExpansionLayout();

                            projectReviewExpansionLayout.setExecutionprojectinspectionid(executionProjectInspection.get(i).getExecutionprojectinspectionid());
                            projectReviewExpansionLayout.setProjectcode(executionProjectInspection.get(i).getProjectcode());
                            projectReviewExpansionLayout.setUsername(executionProjectInspection.get(i).getUsername());
                            projectReviewExpansionLayout.setModifieddate(executionProjectInspection.get(i).getModifieddate());

                            projectReviewExpansionLayout.setRialprogress(executionProjectInspection.get(i).getRialprogress());
                            projectReviewExpansionLayout.setPercentageprogressfirstyear(executionProjectInspection.get(i).getPercentageprogressfirstyear());
                            projectReviewExpansionLayout.setPhysicalprogressinspectiontime(executionProjectInspection.get(i).getPhysicalprogressinspectiontime());
                            projectReviewExpansionLayout.setExecutionstatus(executionProjectInspection.get(i).getExecutionstatus());
                            projectReviewExpansionLayout.setExecutionstatusint(executionProjectInspection.get(i).getExecutionstatusint());
                            projectReviewExpansionLayout.setPerformancequality(executionProjectInspection.get(i).getPerformancequality());
                            projectReviewExpansionLayout.setPerformancequalityint(executionProjectInspection.get(i).getPerformancequalityint());
                            projectReviewExpansionLayout.setPhysicalprogresstype(executionProjectInspection.get(i).getPhysicalprogresstype());
                            projectReviewExpansionLayout.setExpressingopinionsproviding(executionProjectInspection.get(i).getExpressingopinionsproviding());
                            projectReviewExpansionLayout.setHowreferwork(executionProjectInspection.get(i).getHowreferwork());
                            projectReviewExpansionLayout.setHowreferworkint(executionProjectInspection.get(i).getHowreferworkint());
                            projectReviewExpansionLayout.setContractratebasics(executionProjectInspection.get(i).getContractratebasics());
                            projectReviewExpansionLayout.setTechnicalspecifications(executionProjectInspection.get(i).getTechnicalspecifications());
                            projectReviewExpansionLayout.setTechnicalspecificationsint(executionProjectInspection.get(i).getTechnicalspecificationsint());
                            projectReviewExpansionLayout.setOperationvolume(executionProjectInspection.get(i).getOperationvolume());
                            projectReviewExpansionLayout.setOperationvolumeint(executionProjectInspection.get(i).getOperationvolumeint());
                            projectReviewExpansionLayout.setPractical(executionProjectInspection.get(i).getPractical());
                            projectReviewExpansionLayout.setPracticalint(executionProjectInspection.get(i).getPracticalint());
                            projectReviewExpansionLayout.setWkt(executionProjectInspection.get(i).getWkt());
                            projectReviewExpansionLayout.setLat(executionProjectInspection.get(i).getLat());
                            projectReviewExpansionLayout.setLng(executionProjectInspection.get(i).getLng());
                            projectReviewExpansionLayout.setImage(executionProjectInspection.get(i).getImage());

                            projectReviewExpansionLayoutArrayList.add(projectReviewExpansionLayout);
                            projectReviewExpansionRecyclerAdapter = new ProjectReviewExpansionRecyclerAdapter(context, projectReviewExpansionLayoutArrayList);
                            recyclerView.setAdapter(projectReviewExpansionRecyclerAdapter);
                            recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                            //show dialog
                            dialog.show();
                        }
                    }else if(executionProjectInspection.size()==0){
                        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
                        SpannableString efr = new SpannableString("بازدیدی یافت نشد");
                        efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Toast.makeText(context, efr, Toast.LENGTH_LONG).show();
                    }
                    
                //set transparent background

            }
        });
        holder.btnRouting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "google.navigation:q="+dataModel.getLat()+","+dataModel.getLng()+ "&mode=d";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectExpansionLayoutsArrayList.size();
    }


    public class ProjectExpansionViewHolder extends RecyclerView.ViewHolder {
        public AppCompatButton btnReviewList,btnShowOnMap,btnRouting;
        public ExpansionHeader expansionHeader;
        TextView txtProjectId,txtTitle,txtCreateDate,txtMasterPersonName,txtProjectCode,txtBahreBardarPersonName,txtProjectType,txtTargetName,txtProjectStatus,txtAlllocationlastyeartotal,txtCreditExchanged,txtProjectCredit,txtPeriodTime;
        public int id;
        public ProjectExpansionViewHolder(View itemView) {
            super(itemView);
            txtProjectId = (TextView)itemView.findViewById(R.id.txtProjectId);
            txtTitle = (TextView)itemView.findViewById(R.id.edtTitle);
            txtCreateDate = (TextView)itemView.findViewById(R.id.txtCreateDate);
            txtMasterPersonName = (TextView)itemView.findViewById(R.id.txtRialProgress);
            txtProjectCode = (TextView)itemView.findViewById(R.id.txtProjectCode);
            txtBahreBardarPersonName = (TextView)itemView.findViewById(R.id.txtContractRateBasics);
            txtProjectType = (TextView)itemView.findViewById(R.id.txtHowReferWork);
            txtTargetName = (TextView)itemView.findViewById(R.id.txtPercentageProgressFirstYear);
            txtProjectStatus = (TextView)itemView.findViewById(R.id.txtPhysicalProgressInspectionTime);
            txtAlllocationlastyeartotal = (TextView)itemView.findViewById(R.id.txtPerformanceQuality);
            txtCreditExchanged = (TextView)itemView.findViewById(R.id.txtExecutionStatus);
            txtProjectCredit = (TextView)itemView.findViewById(R.id.txtPhysicalProgressType);
            txtPeriodTime = (TextView)itemView.findViewById(R.id.txtOperationVolume);
            btnReviewList = (AppCompatButton)itemView.findViewById(R.id.btnReviewList);
            btnShowOnMap = (AppCompatButton)itemView.findViewById(R.id.btnShowOnMap);
            btnRouting = (AppCompatButton)itemView.findViewById(R.id.btnRouting);
            expansionHeader = (ExpansionHeader)itemView.findViewById(R.id.expansionHeader);
        }
    }


}
