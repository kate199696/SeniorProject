package c.kaitlyn.chainmaillecalculations.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import c.kaitlyn.chainmaillecalculations.ListViewAdapter;
import c.kaitlyn.chainmaillecalculations.MyDBHandler;
import c.kaitlyn.chainmaillecalculations.R;
import c.kaitlyn.chainmaillecalculations.RingInventoryListAdapter;
import c.kaitlyn.chainmaillecalculations.invDBHandler;
import c.kaitlyn.chainmaillecalculations.inventory;
import c.kaitlyn.chainmaillecalculations.ringInventory;
import c.kaitlyn.chainmaillecalculations.updateActivity;

public class inventory_tab extends Fragment {

    View view;
    private static final String TAG = "inventory";

    //ListView mListView = (ListView) findViewById(R.id.RingInventory);
    ArrayList<ringInventory> ringInvList;

    //invDBHandler minvDBHandler;
    String gauge, ringSizeFinal, ringSize;

    private Button btnAdd, btnSub, btnviewAll;

    private EditText editCount;
    String result;
    Integer index;

    //RingInventoryListAdapter adapter = new RingInventoryListAdapter(getActivity(), R.layout.adapter_view_layout, ringInvList);

    /*private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="Ring Size";
    public static final String SECOND_COLUMN="Count";
*/
    MyDBHandler myDb;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

 @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.inventory_tab, container, false);
        //ringInvList = null;
       loadData();
       //Arraylist trial

       final ListView mListView = (ListView) view.findViewById(R.id.RingInventory);
     //loadData();
     final RingInventoryListAdapter adapter = new RingInventoryListAdapter(getActivity(), R.layout.adapter_view_layout, ringInvList);
     mListView.setAdapter(adapter);
     //editList(mListView);
     mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
         public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            loadData();
            ringInventory upd = ringInvList.get(position);
             final Dialog dialog=new Dialog(getContext());
             dialog.setTitle("Update inventory");
             dialog.setContentView(R.layout.activity_update);
             final String rs = upd.getRingSize();
             String count = upd.getCount();
             TextView txtRS = dialog.findViewById(R.id.ringSize_result);
             txtRS.setText(rs);
             final EditText txtCount = dialog.findViewById(R.id.count_result);
             txtCount.setText(count);

             Button bt = dialog.findViewById(R.id.updateButton);
             bt.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     final ringInventory up = new ringInventory(rs, txtCount.getText().toString());
                     ringInvList.set(position, up);
                     ringInventory test =  ringInvList.get(position);

                     Log.d(TAG, "STATE: up = " + up.getCount());
                     Log.d("STATE", test.getCount());
                     dialog.dismiss();
                     saveData();
                     adapter.notifyDataSetChanged();
                    // mListView.setAdapter(new RingInventoryListAdapter(getActivity(), R.layout.adapter_view_layout, ringInvList));

                 }
             });
                loadData();
             dialog.show();

         }

     });

     //mListView.invalidate();
     //((RingInventoryListAdapter) mListView.getAdapter()).notifyDataSetChanged();
     //mListView.setAdapter(new RingInventoryListAdapter(getActivity(), R.layout.adapter_view_layout, ringInvList));

     //populateList();

       //Database tutorial
     /* editCount = getActivity().findViewById(R.id.editText_count);
        btnAdd = getActivity().findViewById(R.id.addButton);
        btnviewAll = getActivity().findViewById(R.id.viewButton);*/

     //Hashmap trial
     //populateList();
        /*ListViewAdapter adapter=new ListViewAdapter(getActivity(), list);
        mListView.setAdapter(adapter);*/

        //ListView mListView =  view.findViewById(R.id.RingInventory);


     //Database trial
       // minvDBHandler.getData();

        //Ringsize choices for adding to database
//Ring Gauge Size
     /*Spinner gaugespinner = view.findViewById(R.id.ring_gaugearray);

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
                     break;
                 case "1/8":
                     ringSize = "1/8";
                     break;
                 case "3/8":
                     ringSize = "3/8";
                     break;
                 case "3/16":
                     ringSize = "3/16";
                     break;
                 case "5/16":
                     ringSize = "5/16";
                     break;
                 case "7/16":
                     ringSize = "7/16";
                     break;
                 case "5/32":
                     ringSize = "5/32";
                     break;
                 case "7/32":
                     ringSize = "7/32";
                     break;
                 case "3/32":
                     ringSize = "3/32";
                     break;
                 case "5/64":
                     ringSize = "5/64";
                     break;
                 case "7/64":
                     ringSize = "7/64";
                     break;
                 case "9/64":
                     ringSize = "9/64";
                     break;
                 case "11/64":
                     ringSize = "11/64";
                     break;

             }

         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });
        ringSizeFinal = gauge + "g " + ringSize + " inch";

        myDb = new MyDBHandler(getContext());
        /*btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );*/
        //addData();
        //viewAll();
        //saveData();

        return view;
    }


    private void editList(final ListView listView){
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 editBox(ringInvList.get(position), position);
                 /*ringInventory sel = ringInvList.get(position);
                 index = position;
                 Intent intent =  new Intent(getActivity(), updateActivity.class);
                 intent.putExtra("inv", sel);
                    startActivityForResult(intent,0);*/
             }

         });

        }

        public void editBox(ringInventory inventory, final int pos){
            final Dialog dialog=new Dialog(getContext());
            dialog.setTitle("Update inventory");
            dialog.setContentView(R.layout.activity_update);
            final String rs = inventory.getRingSize();
            String count = inventory.getCount();
            TextView txtRS = dialog.findViewById(R.id.ringSize_result);
            txtRS.setText(rs);
            EditText txtCount = dialog.findViewById(R.id.count_result);
            txtCount.setText(count);
            final ringInventory up = new ringInventory(rs, txtCount.getText().toString());
            Button bt = dialog.findViewById(R.id.updateButton);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ringInvList.set(pos, up);
                    //adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                 result = data.getStringExtra("result");
                 ringInventory inv = ringInvList.get(index);
                 String rs = inv.getRingSize();

                 ringInventory newAdd = new ringInventory(rs, result);
                 ringInvList.set(index, newAdd);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }*/
    }

    private void saveData(){
     SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
     SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ringInvList);
        Log.d(TAG,"STATE: save json = "+json);
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
            populateList();
        }
    }

    public void addData(){
     /*btnAdd.setOnClickListener(
             new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    boolean isInserted =  myDb.insertData(ringSizeFinal, editCount.getText().toString());
                    if (isInserted){
                        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity(), "Data NOT Inserted", Toast.LENGTH_LONG).show();
                    }
                     //Toast.makeText(getActivity(), "HELLO WORLD", Toast.LENGTH_SHORT).show();
                 }
             });*/
    }
    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                            If the database does not contain any data, a message is displayed
                         */
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            //show error
                            showMessage("Error", "No Inventory");
                            return;
                        }else{
                            //string buffer then display
                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("Id :"+ res.getString(0) + "\n");
                                buffer.append("RingSize :"+ res.getString(1) + "\n");
                                buffer.append("Count :"+ res.getString(2) + "\n\n");
                            }
                            //show all data
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    /*public void AddData(String ringSize, String count){
        minvDBHandler.addData(ringSize, count);
    }
    private void returnMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }*/


    private void populateList() {

     ringInventory gauge1614 = new ringInventory("16g 1/4in", "2400");
     ringInventory gauge1638 = new ringInventory("16g 3/8in", "650");
     ringInventory gauge16516 = new ringInventory("16g 5/16in", "750");
     ringInventory gauge16716 = new ringInventory("16g 7/16in", "650");
     ringInventory gauge16732 = new ringInventory("16g 7/32in", "300");
     ringInventory gauge1814 = new ringInventory("18g 1/4in", "200");
     ringInventory gauge1818 = new ringInventory("18g 1/8in", "600");
     ringInventory gauge18316 = new ringInventory("18g 3/16in", "5000");
     ringInventory gauge18516 = new ringInventory("18g 5/16in", "1000");
     ringInventory gauge18532 = new ringInventory("18g 5/32in", "500");
     ringInventory gauge18732 = new ringInventory("18g 7/32in", "700");
     ringInventory gauge1914 = new ringInventory("19g 1/4in", "200");
     ringInventory gauge19316 = new ringInventory("19g 3/16in", "100");
     ringInventory gauge19532 = new ringInventory("19g 5/32in", "300");
     ringInventory gauge19964 = new ringInventory("19g 9/64in", "100");
     ringInventory gauge2018 = new ringInventory("20g 1/8in", "2100");
     ringInventory gauge20316 = new ringInventory("20g 3/16in", "2400");
     ringInventory gauge20332 = new ringInventory("20g 3/32in", "3950");
     ringInventory gauge20532 = new ringInventory("20g 5/32in", "930");
     ringInventory gauge20564 = new ringInventory("20g 5/64in", "300");
     ringInventory gauge20764 = new ringInventory("20g 7/64in", "1600");
     ringInventory gauge20964 = new ringInventory("20g 9/64in", "2950");
     ringInventory gauge201164 = new ringInventory("20g 11/64in", "2400");


     /*AddData(gauge1614.getRingSize(), gauge1614.getCount());
     AddData(gauge1638.getRingSize(), gauge1638.getCount());
     AddData(gauge16516.getRingSize(), gauge16516.getCount());
     AddData(gauge16716.getRingSize(), gauge16716.getCount());
     AddData(gauge16732.getRingSize(), gauge16732.getCount());
     AddData(gauge1814.getRingSize(), gauge1814.getCount());
     AddData(gauge1818.getRingSize(), gauge1818.getCount());
     AddData(gauge18316.getRingSize(), gauge18316.getCount());
     AddData(gauge18516.getRingSize(), gauge18516.getCount());
     AddData(gauge18532.getRingSize(), gauge18532.getCount());
     AddData(gauge18732.getRingSize(), gauge18732.getCount());
     AddData(gauge1914.getRingSize(), gauge1914.getCount());
     AddData(gauge19316.getRingSize(), gauge19316.getCount());
     AddData(gauge19532.getRingSize(), gauge19532.getCount());
     AddData(gauge19964.getRingSize(), gauge19964.getCount());
     AddData(gauge2018.getRingSize(), gauge2018.getCount());
     AddData(gauge20316.getRingSize(), gauge20316.getCount());
     AddData(gauge20332.getRingSize(), gauge20332.getCount());
     AddData(gauge20532.getRingSize(), gauge20532.getCount());
     AddData(gauge20564.getRingSize(), gauge20564.getCount());
     AddData(gauge20764.getRingSize(), gauge20764.getCount());
     AddData(gauge20964.getRingSize(), gauge20964.getCount());
     AddData(gauge201164.getRingSize(), gauge201164.getCount());*/

     ringInvList.add(gauge1614);
     ringInvList.add(gauge1638);
     ringInvList.add(gauge16516);
     ringInvList.add(gauge16716);
     ringInvList.add(gauge16732);
     ringInvList.add(gauge1814);
     ringInvList.add(gauge1818);
     ringInvList.add(gauge18316);
     ringInvList.add(gauge18516);
     ringInvList.add(gauge18532);
     ringInvList.add(gauge18732);
     ringInvList.add(gauge1914);
     ringInvList.add(gauge19316);
     ringInvList.add(gauge19532);
     ringInvList.add(gauge19964);
     ringInvList.add(gauge2018);
     ringInvList.add(gauge20316);
     ringInvList.add(gauge20332);
     ringInvList.add(gauge20532);
     ringInvList.add(gauge20564);
     ringInvList.add(gauge20764);
     ringInvList.add(gauge20964);
     ringInvList.add(gauge201164);



        /*list=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> hashmaptitle=new HashMap<String, String>();
        hashmaptitle.put(FIRST_COLUMN, "Rings Sizes (inch)");
        hashmaptitle.put(SECOND_COLUMN, "Count");
        list.add(hashmaptitle);

        HashMap<String,String> hashmap=new HashMap<String, String>();
        hashmap.put(FIRST_COLUMN, "18g 3/16");
        hashmap.put(SECOND_COLUMN, "4000");
        list.add(hashmap);

        HashMap<String,String> hashmap2=new HashMap<String, String>();
        hashmap2.put(FIRST_COLUMN, "16g 1/4");
        hashmap2.put(SECOND_COLUMN, "2500");
        list.add(hashmap2);

        HashMap<String,String> hashmap3=new HashMap<String, String>();
        hashmap3.put(FIRST_COLUMN, "18g 1/4");
        hashmap3.put(SECOND_COLUMN, "2500");
        list.add(hashmap3);

        HashMap<String,String> hashmap4=new HashMap<String, String>();
        hashmap4.put(FIRST_COLUMN, "Allo messaging");
        hashmap4.put(SECOND_COLUMN, "google");
        list.add(hashmap4);

        HashMap<String,String> hashmap5=new HashMap<String, String>();
        hashmap5.put(FIRST_COLUMN, "Allo messaging");
        hashmap5.put(SECOND_COLUMN, "google");
        list.add(hashmap5);

        HashMap<String,String> hashmap6=new HashMap<String, String>();
        hashmap6.put(FIRST_COLUMN, "Allo messaging");
        hashmap6.put(SECOND_COLUMN, "google");
        list.add(hashmap6);

        HashMap<String,String> hashmap7=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "Allo messaging");
        hashmap7.put(SECOND_COLUMN, "google");
        list.add(hashmap7);
*/
    //}


}

}
