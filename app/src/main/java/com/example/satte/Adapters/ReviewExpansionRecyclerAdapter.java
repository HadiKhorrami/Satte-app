package com.example.satte.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.satte.DataModels.ProjectReviewExpansionLayout;
import com.example.satte.R;

import java.util.ArrayList;

public class ReviewExpansionRecyclerAdapter extends RecyclerView.Adapter<ReviewExpansionRecyclerAdapter.ReviewExpansionViewHolder> {
    ArrayList<ProjectReviewExpansionLayout> projectReviewExpansionLayouts;
    RecyclerView recyclerView;
    private Context context;
    private int lastPosition = -1;


    public ReviewExpansionRecyclerAdapter(Context context, ArrayList<ProjectReviewExpansionLayout> arrayList) {
        projectReviewExpansionLayouts = new ArrayList<ProjectReviewExpansionLayout>();
        projectReviewExpansionLayouts = arrayList;
    }

    @NonNull
    @Override
    public ReviewExpansionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_expansion_layout, parent, false);
        context = parent.getContext();
        return new ReviewExpansionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewExpansionViewHolder holder, final int position) {
        final ProjectReviewExpansionLayout dataModel = projectReviewExpansionLayouts.get(position);
        holder.txtUserName.setText(String.valueOf(dataModel.getUsername()));
        holder.txtModifiedDate.setText(String.valueOf(dataModel.getModifieddate()));
        holder.txtRialProgress.setText(String.valueOf(dataModel.getRialprogress()));
        holder.txtPercentageProgressFirstYear.setText(String.valueOf(dataModel.getPercentageprogressfirstyear()));
        holder.txtPhysicalProgressInspectionTime.setText(String.valueOf(dataModel.getPhysicalprogressinspectiontime()));
        holder.txtExecutionStatus.setText(dataModel.getExecutionstatus());
        holder.txtPerformanceQuality.setText(dataModel.getPerformancequality());
        holder.txtPhysicalProgressType.setText(String.valueOf(dataModel.getPhysicalprogresstype()));
        holder.txtExpressingOpinionsProviding.setText(String.valueOf(dataModel.getExpressingopinionsproviding()));
        holder.txtHowReferWork.setText(String.valueOf(dataModel.getHowreferwork()));
        holder.txtContractRateBasics.setText(String.valueOf(dataModel.getContractratebasics()));
        holder.txtTechnicalSpecifications.setText(String.valueOf(dataModel.getTechnicalspecifications()));
        holder.txtOperationVolume.setText(String.valueOf(dataModel.getOperationvolume()));
        holder.txtPractical.setText(String.valueOf(dataModel.getPractical()));

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


    public class ReviewExpansionViewHolder extends RecyclerView.ViewHolder {
        public TextView txtModifiedDate,txtUserName,txtRialProgress,txtPercentageProgressFirstYear,txtPhysicalProgressInspectionTime,
                txtExecutionStatus,txtPerformanceQuality,txtPhysicalProgressType,txtExpressingOpinionsProviding,txtHowReferWork,txtContractRateBasics,txtTechnicalSpecifications,txtOperationVolume,txtPractical;
        public int id;
        public ReviewExpansionViewHolder(View itemView) {
            super(itemView);
            txtUserName = (TextView)itemView.findViewById(R.id.txtUserName);
            txtModifiedDate = (TextView)itemView.findViewById(R.id.txtModifiedDate);
            txtRialProgress = (TextView)itemView.findViewById(R.id.txtRialProgress);
            txtPercentageProgressFirstYear = (TextView)itemView.findViewById(R.id.txtPercentageProgressFirstYear);
            txtPhysicalProgressInspectionTime = (TextView)itemView.findViewById(R.id.txtPhysicalProgressInspectionTime);
            txtExecutionStatus = (TextView)itemView.findViewById(R.id.txtExecutionStatus);
            txtPerformanceQuality = (TextView)itemView.findViewById(R.id.txtPerformanceQuality);
            txtPhysicalProgressType = (TextView)itemView.findViewById(R.id.txtPhysicalProgressType);
            txtExpressingOpinionsProviding = (TextView)itemView.findViewById(R.id.txtExpressingOpinionsProviding);
            txtHowReferWork = (TextView)itemView.findViewById(R.id.txtHowReferWork);
            txtContractRateBasics = (TextView)itemView.findViewById(R.id.txtContractRateBasics);
            txtTechnicalSpecifications = (TextView)itemView.findViewById(R.id.txtTechnicalSpecifications);
            txtOperationVolume = (TextView)itemView.findViewById(R.id.txtOperationVolume);
            txtPractical = (TextView)itemView.findViewById(R.id.txtPractical);
        }


    }

}
