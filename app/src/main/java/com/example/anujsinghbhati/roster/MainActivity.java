package com.example.anujsinghbhati.roster;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,
        SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener
{

    public EditText name;
    public CheckBox check_yes, check_may_be;
    public Spinner spinner_eye_color;
    public SeekBar skirt_size, pant_size, shoe_size;
    public TextView title, skirt_size_view, pant_size_view, shoe_size_view, dob_view;
    public RadioGroup radioGroup;
    public Button save_data, reset_data;
    /* Defining Keys for storing data in SharedPreferences */
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name_key = "NameKey";
    public static final String arrival_key = "ArrivalKey";
    public static final String eye_color_key = "EyeColorKey";
    public static final String skirt_size_key = "SkirtSizeKey";
    public static final String pant_size_key = "PantSizeKey";
    public static final String shoe_size_key = "ShoeSizeKey";
    public static final String shirt_size_key = "ShirtSizeKey";
    public static final String dob_key = "DOBKey";

    String result_name, result_arrival, result_eyecolor, result_skirtSize, result_pantSize,
            result_shirtSize, result_shoeSize, result_dob, storedData;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                /* Assigning UI controls values from xml files*/
        title = (TextView)findViewById(R.id.app_title);
        name = (EditText)findViewById(R.id.name);
        check_yes = (CheckBox)findViewById(R.id.check_yes);
        check_may_be = (CheckBox)findViewById(R.id.check_may_be);
        spinner_eye_color = (Spinner)findViewById(R.id.spinner_eye_color);
        pant_size = (SeekBar)findViewById(R.id.seekBar2_pantsize);
        shoe_size = (SeekBar)findViewById(R.id.seekBar3_shoe);
        skirt_size = (SeekBar)findViewById(R.id.seekBar2_skirtsize);
        skirt_size_view = (TextView)findViewById(R.id.skirt_size_TV);
        pant_size_view = (TextView)findViewById(R.id.pant_size_TV);
        shoe_size_view = (TextView)findViewById(R.id.shoe_size_tv);
        save_data = (Button)findViewById(R.id.save_data);
        reset_data = (Button)findViewById(R.id.clear_data);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        dob_view = (TextView)findViewById(R.id.date_tv);
        /*Implementing listeners*/
        spinner_eye_color.setOnItemSelectedListener(this);
        skirt_size.setOnSeekBarChangeListener(this);
        pant_size.setOnSeekBarChangeListener(this);
        shoe_size.setOnSeekBarChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        save_data.setOnClickListener(this);
        reset_data.setOnClickListener(this);
        /* Shared preference defining */
        sharedPreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        /* Auto populating shared preference data when app relaunches by checking whether there is data or not */
        String data = "Name: "+sharedPreferences.getString(name_key, result_name)+"\n"
                +"Arrival: "+sharedPreferences.getString(arrival_key, result_arrival)+"\n"
                +"Eye Colour: "+sharedPreferences.getString(eye_color_key, result_eyecolor)+"\n"
                +"Skirt size: "+sharedPreferences.getString(skirt_size_key, skirt_size_key)+"\n"
                +"Pant size: "+sharedPreferences.getString(pant_size_key, result_pantSize)+"\n"
                +"Arrival: "+sharedPreferences.getString(shoe_size_key, result_shoeSize)+"\n"
                +"Eye Colour: "+sharedPreferences.getString(shirt_size_key, result_shirtSize)+"\n"
                +"Date Of Birth: "+sharedPreferences.getString(dob_key, result_dob);

        /* Checking whether the shared preference is null or not */
        if(sharedPreferences.getString(name_key, result_name) != null &&
                !sharedPreferences.getString(name_key, result_name).equals(""))
        {
            showData("Stored Data",data);   // Auto populating data on activity launch
            clearFieldsData();     // Reset UI element states after populating data
        }else{
            /*First time when app launches / After re-installation */
            showData("No data avalibale.......!", "");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        result_eyecolor = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        /* Assigning seekbar values */
        switch (seekBar.getId()){
            case R.id.seekBar2_skirtsize:
                skirt_size_view.setText(String.valueOf(progress));
                break;
            case R.id.seekBar2_pantsize:
                pant_size_view.setText(String.valueOf(progress));
                break;
            case R.id.seekBar3_shoe:
                shoe_size_view.setText(String.valueOf(progress+4));
                break;
        }}

    /* Assigning radio buttons values */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.xs) {
            result_shirtSize = "XS";
        } else if(checkedId == R.id.small) {
            result_shirtSize = "Small";
        } else if(checkedId == R.id.medium) {
            result_shirtSize = "Medium";
        } else if(checkedId == R.id.large) {
            result_shirtSize = "Large";
        } else if(checkedId == R.id.extra_large){
            result_shirtSize = "XL";
        } else {
            result_shirtSize = "XXL";
        }
    }
    /* Assigning Checkbox values - By checking whether it is selected or not */
    @Override
    public void onClick(View v) {
        boolean atLeastOneChecked = false;
        if(check_yes.isChecked()==true){
            result_arrival = "Very Strong";
            atLeastOneChecked = true;
        }
        if(check_may_be.isChecked()==true){
            result_arrival = "Light";
            atLeastOneChecked = true;
        }
        if (!atLeastOneChecked)
        {
            Toast.makeText(this,"Please check the connection!",Toast.LENGTH_SHORT).show();
        }
        else {
        }
        /* Handling save and clear buttons onclick events */
        switch (v.getId()){
            case R.id.save_data:
                saveDataToPreference();
                break;
            case R.id.clear_data:
                clearFieldsData();
                break;
        }
    }
    /* Save data in Shared Preference on "Save Data" button click */
    public void saveDataToPreference(){
        /* Saving the UI elements data in strings */
        result_name = name.getText().toString();
        result_dob = dob_view.getText().toString();
        result_skirtSize = skirt_size_view.getText().toString();
        result_pantSize = pant_size_view.getText().toString();
        result_shoeSize = shoe_size_view.getText().toString();

        /* Saving the data in shared preferences */
        editor.putString(name_key,result_name);
        editor.putString(arrival_key,result_arrival);
        editor.putString(eye_color_key, result_eyecolor);
        editor.putString(dob_key,result_dob);
        editor.putString(skirt_size_key,result_skirtSize);
        editor.putString(pant_size_key,result_pantSize);
        editor.putString(shoe_size_key,result_shoeSize);
        editor.putString(shirt_size_key,result_shirtSize);

        editor.commit();
        /* Getting shared preference data */
        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        showData("Last Stored Data","Name: "+sharedPreferences.getString(name_key, result_name)+"\n"
                +"Connection: "+sharedPreferences.getString(arrival_key, result_arrival)+"\n"
                +"Eye Colour: "+sharedPreferences.getString(eye_color_key, result_eyecolor)+"\n"
                +"Skirt size: "+sharedPreferences.getString(skirt_size_key, skirt_size_key)+"\n"
                +"Pant size: "+sharedPreferences.getString(pant_size_key, result_pantSize)+"\n"
                +"Shoe Size: "+sharedPreferences.getString(shoe_size_key, result_shoeSize)+"\n"
                +"Eye Colour: "+sharedPreferences.getString(shirt_size_key, result_shirtSize)+"\n");
        clearFieldsData();
    }
    /* To show alert dialog after performing operation */
    public void showData(String title, String storedData)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(storedData);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /* Clear UI elements on "Clear Data" button click */
    public void clearFieldsData(){
        name.setText("");
        check_yes.setChecked(false);
        check_may_be.setChecked(false);
        skirt_size.setProgress(0);
        pant_size.setProgress(0);
        shoe_size.setProgress(0);
        dob_view.setText("");
        radioGroup.clearCheck();
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
