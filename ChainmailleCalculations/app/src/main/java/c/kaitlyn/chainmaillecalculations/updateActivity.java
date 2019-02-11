package c.kaitlyn.chainmaillecalculations;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class updateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final ringInventory result = getIntent().getParcelableExtra("inv");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(.8*width), (int)(.5*height));


        //get info to update
        final String ringSize, count;
        ringSize = result.getRingSize();
        count = result.getCount();

        TextView rs_update = findViewById(R.id.ringSize_result);
        TextView count_update = findViewById(R.id.count_result);
        final EditText editText = findViewById(R.id.count_result);

        rs_update.setText(ringSize);
        count_update.setText(count);

        Button btnUpd;
        ringInventory  newResult;

        btnUpd = this.findViewById(R.id.updateButton);

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countUp = editText.getText().toString();
                result.setCount(countUp);

            }
        });
        String countUpdate = result.getCount();
        //newResult = new ringInventory(ringSize, countUpdate);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", result);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }
}
