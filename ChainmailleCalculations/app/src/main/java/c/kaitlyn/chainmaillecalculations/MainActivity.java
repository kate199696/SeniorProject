package c.kaitlyn.chainmaillecalculations;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import c.kaitlyn.chainmaillecalculations.fragments.calc_tab;
import c.kaitlyn.chainmaillecalculations.fragments.instruction_tab;
import c.kaitlyn.chainmaillecalculations.fragments.inventory_tab;

public class MainActivity extends FragmentActivity {

    //Code  for tabs
    private TabLayout tabLayout;

    private ViewPager viewPager;

    //Arraylist for inventory page
    ArrayList<ringInventory> ringInvList = new ArrayList<>();



    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populatelist();
        loadData();

        Bundle bundle = new Bundle();

        //Code for tab
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Adding Fragments
        adapter.AddFragment(new calc_tab(), "Calculate");
        adapter.AddFragment(new inventory_tab(), "Inventory");
        adapter.AddFragment(new instruction_tab(), "Instructions");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        saveData();

}
    private void saveData(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ringInvList);
        editor.putString("inventory", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("inventory", null);

        Type type = new TypeToken<ArrayList<ringInventory>>() {}.getType();
        ringInvList = gson.fromJson(json, type);

        if (ringInvList == null){
            ringInvList = new ArrayList<>();
            populatelist();
        }
    }
private void populatelist(){
    ringInventory gauge1614 = new ringInventory("16g 1/4in", "2400");
    ringInventory gauge1638 = new ringInventory("16g 3/8in", "1000");
    ringInventory gauge16516 = new ringInventory("16g 5/16in", "2000");
    ringInventory gauge16716 = new ringInventory("16g 7/16in", "100");
    ringInventory gauge16732 = new ringInventory("16g 7/32in", "300");
    ringInventory gauge1814 = new ringInventory("18g 1/4in", "200");
    ringInventory gauge1818 = new ringInventory("18g 1/8in", "600");
    ringInventory gauge18316 = new ringInventory("18g 1/8in", "5000");
    ringInventory gauge18516 = new ringInventory("18g 5/16in", "1000");
    ringInventory gauge18532 = new ringInventory("18g 5/32in", "500");
    ringInventory gauge18732 = new ringInventory("18g 7/32in", "700");
    ringInventory gauge1914 = new ringInventory("19g 1/4in", "200");
    ringInventory gauge19316 = new ringInventory("19g 3/16in", "100");
    ringInventory gauge19532 = new ringInventory("19g 5/32in", "300");
    ringInventory gauge19964 = new ringInventory("19g 9/64in", "100");
    ringInventory gauge2018 = new ringInventory("20g 1/8in", "1600");
    ringInventory gauge20316 = new ringInventory("20g 3/16in", "1200");
    ringInventory gauge20332 = new ringInventory("20g 3/32in", "1000");
    ringInventory gauge20532 = new ringInventory("20g 5/32in", "750");
    ringInventory gauge20564 = new ringInventory("20g 5/64in", "300");
    ringInventory gauge20764 = new ringInventory("20g 7/64in", "400");
    ringInventory gauge20964 = new ringInventory("20g 9/64in", "900");
    ringInventory gauge201164 = new ringInventory("20g 11/64in", "300");


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
}
}