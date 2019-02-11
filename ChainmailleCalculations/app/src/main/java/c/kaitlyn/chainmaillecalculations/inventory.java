package c.kaitlyn.chainmaillecalculations;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

public class inventory extends AppCompatActivity {


    /*private static final String TAG = "inventory";

    ListView mListView = (ListView) findViewById(R.id.RingInventory);
*/
    private String ring_size;
    private int count;

    public inventory(){}

    public inventory(String ringSize, int numCount){
        this.count = numCount;
        this.ring_size = ringSize;
    }
    public void setRing_size(String ringSize){
        this.ring_size = ringSize;
    }

    public void setCount(int num){
        this.count = num;
    }

    public String getRing_size(){
        return this.ring_size;
    }

    public int getCount(){
        return this.count;
    }


}
