package c.kaitlyn.chainmaillecalculations.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import c.kaitlyn.chainmaillecalculations.R;
import c.kaitlyn.chainmaillecalculations.ringInventory;

import static android.content.ContentValues.TAG;

public class calc_tab extends Fragment {
    View view;
    //Code for calculate page
    private TextView mTextMessage;

    double length;
    int open, closed;
    String gauge, ringSize,ringSizeStr, weave, errortight = "Too tight, choose another size";
    String errorloose = "Too loose, choose another size";
    boolean isTight = false, isLoose = false;
    String dialog_title = "Error - improper size";
    String dialog_message = "Choose a different ring size, this size is not appropriate for this weave";

    ArrayList<ringInventory> ringInvList;
    public calc_tab() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calc_tab, container, false);
        //final ArrayList<ringInventory> ringInvList = (ArrayList<ringInventory>)getArguments().getSerializable("key");

        loadData();
        //Code for calculate
        //Weave types
        Spinner weavespinner = view.findViewById(R.id.weavenames);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.weaves, android.R.layout.simple_spinner_item);
        /*ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this.getActivity(),
                android.R.layout.simple_spinner_item, R.id.weavenames );*/
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        weavespinner.setAdapter(adapter);
        weavespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedweave = parent.getItemAtPosition(position).toString();
                switch(selectedweave){
                    case "Spiral":
                        weave = "Spiral";
                        break;
                    case "Box":
                        weave = "Box";
                        break;
                    case "Shaggy Loops":
                        weave = "Shaggy Loops";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Ring Gauge Size
        Spinner gaugespinner = view.findViewById(R.id.ring_gaugearray);

        //Ring Size(inch)
        final Spinner sizespinner = view.findViewById(R.id.ringSizespinner);

        //when item selected
        gaugespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //makes size spinner visible and have the correct sizes

                String selectedGauge = parent.getItemAtPosition(position).toString();
                switch (selectedGauge){
                    case "16":
                        sizespinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.ring_gauge16)));
                        // Specify the layout to use when the list of choices appears
                        // Create an ArrayAdapter using the string array and a default spinner layout

                        gauge = "16";
                        break;
                    case "18":
                        sizespinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.ring_gauge18)));
                        // Create an ArrayAdapter using the string array and a default spinner layout

                        gauge = "18";
                        break;
                    case "19":
                        sizespinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.ring_gauge19)));
                        // Create an ArrayAdapter using the string array and a default spinner layout

                        gauge = "19";
                        break;
                    case "20":
                        sizespinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.ring_gauge20)));
                        // Create an ArrayAdapter using the string array and a default spinner layout

                        gauge = "20";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing happens if nothing is selected
                TextView ringSizetext =  view.findViewById(R.id.ring_size);
                Spinner ringsizespinner = view.findViewById(R.id.ringSizespinner);

                ringSizetext.setVisibility(View.GONE);
                ringsizespinner.setVisibility(View.GONE);
            }
        });




        sizespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = parent.getItemAtPosition(position).toString();
                switch(selectedSize) {
                    case "1/4":
                        ringSize = "1/4";
                        ringSizeStr = "14";
                        break;
                    case "1/8":
                        ringSize = "1/8";
                        ringSizeStr = "18";
                        break;
                    case "3/8":
                        ringSize = "3/8";
                        ringSizeStr = "38";
                        break;
                    case "3/16":
                        ringSize = "3/16";
                        ringSizeStr = "316";
                        break;
                    case "5/16":
                        ringSize = "5/16";
                        ringSizeStr = "516";
                        break;
                    case "7/16":
                        ringSize = "7/16";
                        ringSizeStr = "716";
                        break;
                    case "5/32":
                        ringSize = "5/32";
                        ringSizeStr = "532";
                        break;
                    case "7/32":
                        ringSize = "7/32";
                        ringSizeStr = "732";
                        break;
                    case "3/32":
                        ringSize = "3/32";
                        ringSizeStr = "332";
                        break;
                    case "5/64":
                        ringSize = "5/64";
                        ringSizeStr = "564";
                        break;
                    case "7/64":
                        ringSize = "7/64";
                        ringSizeStr = "764";
                        break;
                    case "9/64":
                        ringSize = "9/64";
                        ringSizeStr = "964";
                        break;
                    case "11/64":
                        ringSize = "11/64";
                        ringSizeStr = "1164";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //number box for length
        int lenNum;
        EditText lengthInput = (EditText) view.findViewById(R.id.lengthNum);

        final TextView customText = view.findViewById(R.id.customtext);
        final EditText lenCustom = view.findViewById(R.id.lengthNum);

        //Length Desired
        Spinner lengthSpinner = view.findViewById(R.id.lengthSpinner);

        ArrayAdapter<CharSequence> lenAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.len_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        lengthSpinner.setAdapter(lenAdapter);

        //selection listener
        lengthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedlength = parent.getItemAtPosition(position).toString();
                /*if(selectedlength.equals("Custom")){
                    customText.setVisibility(View.VISIBLE);
                    lenCustom.setVisibility(View.VISIBLE);
                    String value= lenCustom.getText().toString();
                    length = Integer.parseInt(value);
                    //restartActivity();
                }else{
                    customText.setVisibility(View.GONE);
                    lenCustom.setVisibility(View.GONE);
                }*/
                switch(selectedlength){
                    case "1":
                        length = 1;
                        break;
                    case "6":
                        length = 6;
                        break;
                    case "6.5":
                        length = 6.5;
                        break;
                    case "7":
                        length = 7;
                        break;
                    case "8":
                        length = 8;
                        break;
                    case "7.5":
                        length = 7.5;
                        break;
                    case "8.5":
                        length = 8.5;
                        break;
                    case "24":
                        length = 24;
                        break;
                    case "28":
                        length = 28;
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button calbutton = view.findViewById(R.id.calculatebutton);
        calbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                if (weave.equals("Spiral")) {
                    if (gauge.equals("16")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 7);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/8")) {
                            open = (int) (length * 4);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/16")) {
                            open = (int) (length * 5);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/16")) {
                            open = (int) (length * 3);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                    }
                    if (gauge.equals("18")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/16")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/32")) {
                            open = (int) (length * 7);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("1/8")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 9);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }

                    }
                    if (gauge.equals("19")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 7);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 8);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 10);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("9/64")) {
                            open = (int) (length * 12);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                    }
                    if (gauge.equals("20")) {

                        if (ringSize.equals("1/8")) {
                            open = (int) (length * 15);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 9);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 12);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("9/64")) {
                            open = (int) (length * 13);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/64")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/64")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("11/64")) {
                            open = (int) (length * 9);
                            closed = (int) (length * 0);
                            isTight = false;
                            isLoose = false;
                        }
                    }
                }
                if (weave == "Box") {
                    if (gauge.equals("16")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 10);
                            closed = (int) (length * 12);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/8")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = true;
                        }
                        if (ringSize.equals("5/16")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/16")) {
                            open = (int) (length * 4);
                            closed = (int) (length * 6);
                            isTight = false;
                            isLoose = true;
                        }
                        if (ringSize.equals("7/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                    }
                    if (gauge.equals("18")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 8);
                            closed = (int) (length * 10);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/16")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = true;
                        }
                        if (ringSize.equals("7/32")) {
                            open = (int) (length * 12);
                            closed = (int) (length * 14);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("1/8")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 14);
                            closed = (int) (length * 16);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }

                    }
                    if (gauge.equals("19")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 8);
                            closed = (int) (length * 10);
                            isTight = false;
                            isLoose = true;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 12);
                            closed = (int) (length * 14);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 16);
                            closed = (int) (length * 18);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("9/64")) {
                            open = (int) (length * 14);
                            closed = (int) (length * 16);
                            isTight = false;
                            isLoose = false;
                        }
                    }
                    if (gauge.equals("20")) {

                        if (ringSize.equals("1/8")) {
                            open = (int) (length * 24);
                            closed = (int) (length * 26);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 14);
                            closed = (int) (length * 16);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("9/64")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/64")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/64")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("11/64")) {
                            open = (int) (length * 10);
                            closed = (int) (length * 12);
                            isTight = false;
                            isLoose = true;
                        }
                    }
                }
                if (weave == "Shaggy Loops") {
                    if (gauge.equals("16")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 4);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/8")) {
                            open = (int) (length * 3);
                            closed = (int) (length * 6);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/16")) {
                            open = (int) (length * 3);
                            closed = (int) (length * 6);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/16")) {
                            open = (int) (length * 2);
                            closed = (int) (length * 4);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/32")) {
                            open = (int) (length * 4);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = false;
                        }
                    }
                    if (gauge.equals("18")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 4);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/16")) {
                            open = (int) (length * 3);
                            closed = (int) (length * 6);
                            isTight = false;
                            isLoose = true;
                        }
                        if (ringSize.equals("7/32")) {
                            open = (int) (length * 4);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("1/8")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 5);
                            closed = (int) (length * 10);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 12);
                            isTight = false;
                            isLoose = false;
                        }

                    }
                    if (gauge.equals("19")) {
                        if (ringSize.equals("1/4")) {
                            open = (int) (length * 4);
                            closed = (int) (length * 8);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 5);
                            closed = (int) (length * 10);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 12);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("9/64")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 12);
                            isTight = false;
                            isLoose = false;
                        }
                    }
                    if (gauge.equals("20")) {

                        if (ringSize.equals("1/8")) {
                            open = (int) (length * 8);
                            closed = (int) (length * 16);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/16")) {
                            open = (int) (length * 5);
                            closed = (int) (length * 10);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/32")) {
                            open = (int) (length * 6);
                            closed = (int) (length * 12);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("9/64")) {
                            open = (int) (length * 7);
                            closed = (int) (length * 14);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("3/32")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("5/64")) {
                            open = (int) (length * 0);
                            closed = (int) (length * 0);
                            isTight = true;
                            isLoose = false;
                        }
                        if (ringSize.equals("7/64")) {
                            open = (int) (length * 8);
                            closed = (int) (length * 16);
                            isTight = false;
                            isLoose = false;
                        }
                        if (ringSize.equals("11/64")) {
                            open = (int) (length * 5);
                            closed = (int) (length * 10);
                            isTight = false;
                            isLoose = false;
                        }
                    }
                }

                if (!isTight && !isLoose   ) {
                    TextView openText = view.findViewById(R.id.openCal);
                    openText.setText(" " + Integer.toString(open) + " rings");
                    openText.setTextColor(Color.BLACK);
                    TextView closedText = view.findViewById(R.id.closedCal);
                    closedText.setText(" " + Integer.toString(closed) + " rings");
                    closedText.setTextColor(Color.BLACK);

                } else if (isTight) {
                    TextView openText = view.findViewById(R.id.openCal);
                    openText.setText(" " + errortight);
                    openText.setTextColor(Color.RED);
                    TextView closedText = view.findViewById(R.id.closedCal);
                    closedText.setText(" " + errortight);
                    closedText.setTextColor(Color.RED);
                } else if (isLoose){
                    TextView openText = view.findViewById(R.id.openCal);
                    openText.setText(" " + errorloose);
                    openText.setTextColor(Color.RED);
                    TextView closedText = view.findViewById(R.id.closedCal);
                    closedText.setText(" " + errorloose);
                    closedText.setTextColor(Color.RED);
                }

            }
        });


        final Button subbutton = view.findViewById(R.id.subtractButton);
        subbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int total = open + closed;

                //String i;
                String ring = gauge+"g "+ ringSize + "in";
                String tempSize="", tempCount="";
                Integer i=0,pos=0;

                //loadData();

                //ringInventory ringTemp = new ringInventory();
                for(ringInventory ringInv : ringInvList){
                    if(ringInv.getRingSize().equals(ring)){
                        ringInventory temp = ringInv;
                        tempSize = ringInv.getRingSize();
                        tempCount = ringInv.getCount();
                        Log.d(TAG, "tempVal: ring count of "+tempSize+ " is " + tempCount);
                        pos = i;
                    }
                    else{
                        Log.d(TAG, "tempVal: Can't find");
                    }
                    i++;
                }

                /*if(ringInvList.contains(ring)){
                    Log.d(TAG, "onClick: "+ ring + " true");
                }else{
                    Log.d(TAG, "onClick: "+ ring + " false");
                }*/

                Integer sub = Integer.parseInt(tempCount);
                Integer fin = sub - total;
                //int i = ringInvList.indexOf(ring);
               /* TextView test = view.findViewById(R.id.testtext);
                test.setText("(testing) Subtract: "+ total + " from " + ring + " index: ");*/
                ringInventory tempRing = new ringInventory(tempSize,Integer.toString(fin));
                ringInvList.set(pos,tempRing);
                Log.d(TAG, "SETNEW: total to subtract"+ Integer.toString(total));
                Log.d(TAG, "SETNEW: setting the new ring " + tempRing.getRingSize()+ " to "+ Integer.toString(fin));


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Updating the value for "+tempSize +" to " + fin);
                builder.setTitle("Update Value");
                AlertDialog dialog = builder.create();

                saveData();

            }
        });

        return view;
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ringInvList);
        editor.putString("inventory", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("inventory", null);

        Type type = new TypeToken<ArrayList<ringInventory>>() {}.getType();
        ringInvList = gson.fromJson(json, type);

        if (ringInvList == null){
            ringInvList = new ArrayList<>();
            //populateList();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
