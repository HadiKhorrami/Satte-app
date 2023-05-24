package com.example.satte.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.satte.R;
import com.example.satte.db.service.ProjectLocalServiceUtil;
import com.example.satte.db.tables.Project;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class AddReview extends AppCompatActivity {
    ArrayList<String> arrayList;
    TextView txtProjectCode;
    EditText edtTitle,edtBahreBardar,edtProjectType,edtProjectExecuteType,edtStartYear,edtEndYear,edtTagsimatName,edtProjectCredit,edtCreditCurrentYear,edtPaidUptoDatePreviousYear,edtRialProgress,
            edtPercentageProgressFirstYear,edtPhysicalProgressInspectionTime,edtExpressingOpinionsProviding;
    Dialog dialog;
    AppCompatButton btnSave;
    Spinner spnExecutionStatus,spnPerformanceQuality,spnHowReferWork,spnContractRateBasics,spnPhysicalProgressType,spnOperationVolume,spnPractical,spnTechnicalSpecifications;
    CheckBox checkBox,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10,checkBox11;
    String causesDelay = "";
    JSONObject insertInspectionJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtBahreBardar = (EditText) findViewById(R.id.edtBahreBardar);
        edtProjectType = (EditText) findViewById(R.id.edtProjectType);
        edtProjectExecuteType = (EditText) findViewById(R.id.edtProjectExecuteType);
        edtStartYear = (EditText) findViewById(R.id.edtStartYear);
        edtEndYear = (EditText) findViewById(R.id.edtEndYear);
        edtTagsimatName = (EditText) findViewById(R.id.edtTagsimatName);
        edtProjectCredit = (EditText) findViewById(R.id.edtProjectCredit);
        edtCreditCurrentYear = (EditText) findViewById(R.id.edtCreditCurrentYear);
        edtPaidUptoDatePreviousYear = (EditText) findViewById(R.id.edtPaidUptoDatePreviousYear);
        edtRialProgress = (EditText) findViewById(R.id.edtRialProgress);
        edtPercentageProgressFirstYear = (EditText) findViewById(R.id.edtPercentageProgressFirstYear);
        edtPhysicalProgressInspectionTime = (EditText) findViewById(R.id.edtSearch);
        edtExpressingOpinionsProviding = (EditText) findViewById(R.id.edtExpressingOpinionsProviding);

        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox)findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox)findViewById(R.id.checkBox7);
        checkBox8 = (CheckBox)findViewById(R.id.checkBox8);
        checkBox9 = (CheckBox)findViewById(R.id.checkBox9);
        checkBox10 = (CheckBox)findViewById(R.id.checkBox10);
        checkBox11 = (CheckBox)findViewById(R.id.checkBox11);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

              @Override
                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                  if(isChecked==true){
                      causesDelay += checkBox.getText().toString() + ",";
                      System.out.println("sss" + causesDelay);
                  }else if(isChecked==false) {

                      String newString = causesDelay.replace(checkBox.getText().toString() + ",","");
                      causesDelay = newString;
                      System.out.println("sss" + causesDelay);
                  }
              }
                 });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox2.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox2.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox3.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox3.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox4.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox4.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox5.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox5.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox6.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox6.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox7.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox7.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox8.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox8.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox9.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox9.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox10.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox10.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });
        checkBox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked==true){
                    causesDelay += checkBox11.getText().toString() + ",";
                    System.out.println("sss" + causesDelay);
                }else if(isChecked==false) {
                   
                    String newString = causesDelay.replace(checkBox11.getText().toString() + ",","");
                    causesDelay = newString;
                    System.out.println("sss" + causesDelay);
                }
            }
        });

        spnExecutionStatus = (Spinner) findViewById(R.id.spnExecutionStatus);
        spnPerformanceQuality = (Spinner) findViewById(R.id.spnPerformanceQuality);
        spnHowReferWork = (Spinner) findViewById(R.id.spnHowReferWork);
        spnContractRateBasics = (Spinner) findViewById(R.id.spnContractRateBasics);
        spnPhysicalProgressType = (Spinner) findViewById(R.id.spnPhysicalProgressType);
        spnOperationVolume = (Spinner) findViewById(R.id.spnOperationVolume);
        spnPractical = (Spinner) findViewById(R.id.spnPractical);
        spnTechnicalSpecifications = (Spinner) findViewById(R.id.spnTechnicalSpecifications);

        txtProjectCode = findViewById(R.id.txtProjectCode);
        btnSave = (AppCompatButton) findViewById(R.id.btnSave);
        Spinner spinner = (Spinner) findViewById(R.id.spnExecutionStatus);
        arrayList = new ArrayList<>();
        insertInspectionJson = new JSONObject();
        setProjectCodeSpinner();
        setSpinners();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                if(edtRialProgress.getText().toString().equals("") || edtPercentageProgressFirstYear.getText().toString().equals("") || edtPhysicalProgressInspectionTime.getText().toString().equals("") || txtProjectCode.getText().toString().equals("")){
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    SpannableString efr = new SpannableString("موارد الزامی را وارد نمایید");
                    efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    Toast.makeText(AddReview.this, efr, Toast.LENGTH_SHORT).show();
                }else {
                    if(Double.parseDouble(edtPhysicalProgressInspectionTime.getText().toString()) < Double.parseDouble(edtPercentageProgressFirstYear.getText().toString())){
                        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                        SpannableString efr = new SpannableString("پیشرفت فیزیکی در زمان بازدید نباید کمتر از پیشرفت فیزیکی ابتدای سال باشد");
                        efr.setSpan(new TypefaceSpan(font), 0, efr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Toast.makeText(AddReview.this, efr, Toast.LENGTH_LONG).show();
                    }else {
                        setInsertInspectionJson();
                        Intent intent = new Intent(AddReview.this, AddReviewPlaceDocs.class);
                        intent.putExtra("insertInspectionJson", String.valueOf(insertInspectionJson));
                        startActivity(intent);
                    }
                }

            }
        });
    }

    public void setProjectCodeSpinner() {
        ProjectLocalServiceUtil projectLocalServiceUtil = new ProjectLocalServiceUtil(AddReview.this);
        List<Project> project = projectLocalServiceUtil.getProjects();
        for(int i = 0;i<project.size();i++) {
            if (!project.get(i).getProjectimplementationstatus().equals("متوقف شده")) {
                arrayList.add(String.valueOf(project.get(i).getProjectcode()));
            }
        }

        txtProjectCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(AddReview.this);
                //set  (our custom layout for dialog)
                dialog.setContentView(R.layout.layout_searchable_spinner);

                //set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                //show dialog
                dialog.show();

                //initialize and assign variable
                EditText editText = dialog.findViewById(R.id.editText_of_searchableSpinner);
                ListView listView = dialog.findViewById(R.id.listView_of_searchableSpinner);
                //array adapter
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddReview.this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList);
                listView.setAdapter(arrayAdapter);
                //Textwatcher for change data after every text type by user
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //filter arraylist
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });

                // listview onitem click listener
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        try {
                            insertInspectionJson.put("projectCode",arrayAdapter.getItem(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        txtProjectCode.setText(arrayAdapter.getItem(i));
                        getProjectByProjecCode(arrayAdapter.getItem(i));
                        dialog.dismiss();

                    }
                });
            }
        });
    }
    public void getProjectByProjecCode(String projectCode) {
        ProjectLocalServiceUtil projectLocalServiceUtil = new ProjectLocalServiceUtil(AddReview.this);
        List<Project> project = projectLocalServiceUtil.getProjectsByProjectCode("projectcode",projectCode);
        for (int i = 0; i < project.size(); i++) {

                edtTitle.setText(project.get(i).getTitle());
                edtBahreBardar.setText(project.get(i).getBahrebardarpersonname());
                edtProjectType.setText(project.get(i).getProjecttype());
                edtProjectExecuteType.setText(project.get(i).getProjectexecutetype());
                edtStartYear.setText(String.valueOf(project.get(i).getStartyear()));
                edtEndYear.setText(String.valueOf(project.get(i).getEndyear()));
                edtTagsimatName.setText(project.get(i).getTagsimatname());
                edtProjectCredit.setText(String.valueOf(project.get(i).getProjectcredit()));
                edtCreditCurrentYear.setText(String.valueOf(project.get(i).getCreditcurrentyear()));
                edtPaidUptoDatePreviousYear.setText(String.valueOf(project.get(i).getPaiduptodatepreviousyear()));

        }
    }
    public void setSpinners(){
        String[] executionStatusArray = getResources().getStringArray(R.array.spnExecutionStatusArray);
        if(executionStatusArray != null && executionStatusArray.length > 0) {
            String[] names = new String[executionStatusArray.length];
            String[] values = new String[executionStatusArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < executionStatusArray.length; i++) {
                String executionStatus = executionStatusArray[i];
                if(executionStatus == null || executionStatus.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = executionStatus.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnExecutionStatus.setAdapter(adapter);
            spnExecutionStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    int val = 0;
                    String name="";
                    try {
                        val = Integer.parseInt(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("performanceQuality",name);
                        insertInspectionJson.put("performanceQualityInt",val); // Here you have value as numeric type
                        insertInspectionJson.put("executionStatus",name);
                        insertInspectionJson.put("executionStatusInt",val);
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
        String[] performanceQualityArray = getResources().getStringArray(R.array.spnPerformanceQualityArray);
        if(performanceQualityArray != null && performanceQualityArray.length > 0) {
            String[] names = new String[performanceQualityArray.length];
            String[] values = new String[performanceQualityArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < performanceQualityArray.length; i++) {
                String performanceQuality = performanceQualityArray[i];
                if(performanceQuality == null || performanceQuality.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = performanceQuality.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPerformanceQuality.setAdapter(adapter);
            spnPerformanceQuality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    int val = 0;
                    String name="";
                    try {
                        val = Integer.parseInt(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("performanceQuality",name);
                        insertInspectionJson.put("performanceQualityInt",val);
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                    

//                    textView.setText(String.format("Value for %s is %d", names[position], val));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
        String[] howReferWorkArray = getResources().getStringArray(R.array.spnHowReferWorkArray);
        if(howReferWorkArray != null && howReferWorkArray.length > 0) {
            String[] names = new String[howReferWorkArray.length];
            String[] values = new String[howReferWorkArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < howReferWorkArray.length; i++) {
                String howReferWork = howReferWorkArray[i];
                if(howReferWork == null || howReferWork.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = howReferWork.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnHowReferWork.setAdapter(adapter);
            spnHowReferWork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    int val = 0;
                    String name="";
                    try {
                        val = Integer.parseInt(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("howReferWork",name);
                        insertInspectionJson.put("howReferWorkInt",val); // Here you have value as numeric type
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                    

//                    textView.setText(String.format("Value for %s is %d", names[position], val));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
        String[] contractRateBasicsArray = getResources().getStringArray(R.array.spnContractRateBasicsArray);
        if(contractRateBasicsArray != null && contractRateBasicsArray.length > 0) {
            String[] names = new String[contractRateBasicsArray.length];
            String[] values = new String[contractRateBasicsArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < contractRateBasicsArray.length; i++) {
                String contractRateBasics = contractRateBasicsArray[i];
                if(contractRateBasics == null || contractRateBasics.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = contractRateBasics.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnContractRateBasics.setAdapter(adapter);
            spnContractRateBasics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    int val = 0;
                    String name="";
                    try {
                        val = Integer.parseInt(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("contractRateBasics",name);
                        insertInspectionJson.put("contractRateBasicsInt",val); // Here you have value as numeric type
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                    

//                    textView.setText(String.format("Value for %s is %d", names[position], val));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
        String[] physicalProgressTypeArray = getResources().getStringArray(R.array.spnPhysicalProgressTypeArray);
        if(physicalProgressTypeArray != null && physicalProgressTypeArray.length > 0) {
            String[] names = new String[physicalProgressTypeArray.length];
            String[] values = new String[physicalProgressTypeArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < physicalProgressTypeArray.length; i++) {
                String physicalProgressType = physicalProgressTypeArray[i];
                if(physicalProgressType == null || physicalProgressType.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = physicalProgressType.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPhysicalProgressType.setAdapter(adapter);
            spnPhysicalProgressType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    int val = 0;
                    String name="";
                    try {
                        val = Integer.parseInt(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("physicalProgressType",name);
                        insertInspectionJson.put("physicalProgressTypeInt",val); // Here you have value as numeric type
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                    

//                    textView.setText(String.format("Value for %s is %d", names[position], val));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
        String[] operationVolumeArray = getResources().getStringArray(R.array.spnOperationVolumeArray);
        if(operationVolumeArray != null && operationVolumeArray.length > 0) {
            String[] names = new String[operationVolumeArray.length];
            String[] values = new String[operationVolumeArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < operationVolumeArray.length; i++) {
                String operationVolume = operationVolumeArray[i];
                if(operationVolume == null || operationVolume.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = operationVolume.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnOperationVolume.setAdapter(adapter);
            spnOperationVolume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    boolean val = false;
                    String name="";
                    try {
                        val = Boolean.parseBoolean(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("operationVolume",name);
                        insertInspectionJson.put("operationVolumeInt",val); // Here you have value as numeric type
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                    

//                    textView.setText(String.format("Value for %s is %d", names[position], val));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
        String[] practicalArray = getResources().getStringArray(R.array.spnPracticalArray);
        if(practicalArray != null && practicalArray.length > 0) {
            String[] names = new String[practicalArray.length];
            String[] values = new String[practicalArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < practicalArray.length; i++) {
                String practical = practicalArray[i];
                if(practical == null || practical.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = practical.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPractical.setAdapter(adapter);
            spnPractical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    boolean val = false;
                    String name="";
                    try {
                        val = Boolean.parseBoolean(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("practical",name);
                        insertInspectionJson.put("practicalInt",val); // Here you have value as numeric type
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                    

//                    textView.setText(String.format("Value for %s is %d", names[position], val));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
        String[] technicalSpecificationsArray = getResources().getStringArray(R.array.spnTechnicalSpecificationsArray);
        if(technicalSpecificationsArray != null && technicalSpecificationsArray.length > 0) {
            String[] names = new String[technicalSpecificationsArray.length];
            String[] values = new String[technicalSpecificationsArray.length];
            // Now we will parse the records and split them into name and value
            for(int i = 0; i < technicalSpecificationsArray.length; i++) {
                String technicalSpecifications = technicalSpecificationsArray[i];
                if(technicalSpecifications == null || technicalSpecifications.isEmpty()) {
                    continue;
                }
                // Split the record by "," seperator for example for choice "Choice,1"
                String[] nameValue = technicalSpecifications.split("-");
                if(nameValue.length < 2) {
                    continue;
                }
                names[i] = nameValue[0]; // first index will have the names
                values[i] = nameValue[1]; // second will have its value
            }
//            Log.d(TAG, "onViewCreated: names and values: "+ Arrays.toString(names)+" - "+Arrays.toString(values));
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(AddReview.this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnTechnicalSpecifications.setAdapter(adapter);
            spnTechnicalSpecifications.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/IRANSansWeb.ttf");
                    if (parent.getChildAt(0) != null) {
                        ((TextView) parent.getChildAt(0)).setTypeface(font);
                    }
                    boolean val = false;
                    String name="";
                    try {
                        val = Boolean.parseBoolean(values[position]); // Here you have value as numeric type
                        name = names[position]; // Here you have value as numeric type
                        insertInspectionJson.put("technicalSpecifications",name);
                        insertInspectionJson.put("technicalSpecificationsInt",val); // Here you have value as numeric type
                    } catch (NumberFormatException | JSONException nfe) {
                        nfe.printStackTrace();
                    }
                    

//                    textView.setText(String.format("Value for %s is %d", names[position], val));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else {}
        //////////////////////////////////////
    }
    public void setInsertInspectionJson(){
        try {
            SharedPreferences shared = getSharedPreferences("userInfo", MODE_PRIVATE);
            String username = shared.getString("userName", "");
            long userId = shared.getLong("userId", 0);

            PersianDate pdate = new PersianDate();
            PersianDateFormat pdformater = new PersianDateFormat("Y/m/d");
            pdformater.format(pdate);

            Random rn = new Random();
            long randomNumber = rn.nextLong() * -1;

            insertInspectionJson.put("executionProjectInspectionId",randomNumber);
            insertInspectionJson.put("companyId",20155);
            insertInspectionJson.put("createDate",pdformater.format(pdate));
            insertInspectionJson.put("modifiedDate",pdformater.format(pdate));
            insertInspectionJson.put("userName",username);
            insertInspectionJson.put("userId",userId);
            insertInspectionJson.put("groupId",0);
            insertInspectionJson.put("rialProgress",Double.parseDouble(edtRialProgress.getText().toString()));
            insertInspectionJson.put("percentageProgressFirstYear",Double.parseDouble(edtPercentageProgressFirstYear.getText().toString()));
            insertInspectionJson.put("physicalProgressInspectionTime",Double.parseDouble(edtPhysicalProgressInspectionTime.getText().toString()));
            insertInspectionJson.put("causesDelay",causesDelay);
            insertInspectionJson.put("expressingOpinionsProviding",edtExpressingOpinionsProviding.getText().toString());
            insertInspectionJson.put("geometry","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(insertInspectionJson);
    }

}