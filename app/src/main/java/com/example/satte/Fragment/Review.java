package com.example.satte.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.satte.Activity.AddReview;
import com.example.satte.Activity.AddReviewPlaceDocs;
import com.example.satte.Adapters.ProjectReviewExpansionRecyclerAdapter;
import com.example.satte.Adapters.ReviewExpansionRecyclerAdapter;
import com.example.satte.DataModels.ProjectReviewExpansionLayout;
import com.example.satte.DataModels.ReviewExpansionLayout;
import com.example.satte.R;
import com.example.satte.db.service.ExecutionProjectInspectionLocalServiceUtil;
import com.example.satte.db.tables.ExecutionProjectInspection;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Review#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Review extends Fragment {
    RecyclerView recyclerView;
    AppCompatButton btnAddReview;
    EditText edtSearch;
    ArrayList<ProjectReviewExpansionLayout> projectReviewExpansionLayoutArrayList;
    ArrayList<String> searchList;
    ReviewExpansionRecyclerAdapter reviewExpansionRecyclerAdapter;
    Context context;

    public Review() {
        // Required empty public constructor
    }

    public static Review newInstance(String param1, String param2) {
        Review fragment = new Review();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance() {
        Review review = new Review();
        return review;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        btnAddReview = (AppCompatButton) view.findViewById(R.id.btnAddReview);
        edtSearch = (EditText) view.findViewById(R.id.edtSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        projectReviewExpansionLayoutArrayList = new ArrayList<>();
        searchList = new ArrayList<>();
        getAllInspections();
        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddReview.class);
                startActivity(intent);
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int k, int i1, int i2) {
                //filter arraylist
                if (edtSearch.getText().toString().length() > 0) {
                    projectReviewExpansionLayoutArrayList.clear();
                    reviewExpansionRecyclerAdapter = new ReviewExpansionRecyclerAdapter(context, projectReviewExpansionLayoutArrayList);
                    recyclerView.setAdapter(reviewExpansionRecyclerAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));

                    ExecutionProjectInspectionLocalServiceUtil executionProjectInspectionLocalServiceUtil = new ExecutionProjectInspectionLocalServiceUtil(context);
                    List<ExecutionProjectInspection> executionProjectInspection = executionProjectInspectionLocalServiceUtil.getExecutionProjectInspection();

                    for (int i = 0; i < executionProjectInspection.size(); i++) {
                        if (executionProjectInspection.size() > 0 && String.valueOf(executionProjectInspection.get(i).getProjectcode()).startsWith(charSequence.toString())) {
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
                            projectReviewExpansionLayoutArrayList.add(projectReviewExpansionLayout);
                            reviewExpansionRecyclerAdapter = new ReviewExpansionRecyclerAdapter(context, projectReviewExpansionLayoutArrayList);
                            recyclerView.setAdapter(reviewExpansionRecyclerAdapter);
                            recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
                        }
                    }
                } else {
                    projectReviewExpansionLayoutArrayList.clear();
                    getAllInspections();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        return view;
    }

    private void getAllInspections() {
        ExecutionProjectInspectionLocalServiceUtil executionProjectInspectionLocalServiceUtil = new ExecutionProjectInspectionLocalServiceUtil(getActivity());
        List<ExecutionProjectInspection> executionProjectInspection = executionProjectInspectionLocalServiceUtil.getExecutionProjectInspection();
        if (executionProjectInspection.size() > 0) {
            for (int i = 0; i < executionProjectInspection.size(); i++) {
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
                projectReviewExpansionLayoutArrayList.add(projectReviewExpansionLayout);
                reviewExpansionRecyclerAdapter = new ReviewExpansionRecyclerAdapter(context, projectReviewExpansionLayoutArrayList);
                recyclerView.setAdapter(reviewExpansionRecyclerAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
            }
        }
    }
}