package c.kaitlyn.chainmaillecalculations.fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import c.kaitlyn.chainmaillecalculations.R;
import c.kaitlyn.chainmaillecalculations.boxchain;
import c.kaitlyn.chainmaillecalculations.shaggyclass;
import c.kaitlyn.chainmaillecalculations.spiralclass;

public class instruction_tab extends Fragment {
    View view;

    private Button box, spiral, shaggy;
    public instruction_tab() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.instruction_tab, container, false);
        box =  view.findViewById(R.id.boxbutton);
        spiral = view.findViewById(R.id.spiralbutton);
        shaggy = view.findViewById(R.id.shaggybutton);

        box.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openBoxinst();
            }
        });

        spiral.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openSpiralinst();
            }
        });
        shaggy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openShaggyinst();
            }
        });
        return view;
    }


    public void openBoxinst(){
        Intent intent = new Intent(getContext(), boxchain.class);
        startActivity(intent);
    }
    public void openSpiralinst(){
        Intent intent2 = new Intent(getContext(), spiralclass.class);
        startActivity(intent2);
    }
    public void openShaggyinst(){
        Intent intent3 = new Intent(getContext(), shaggyclass.class);
        startActivity(intent3);
    }
}
