package c.kaitlyn.chainmaillecalculations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RingInventoryListAdapter extends ArrayAdapter<ringInventory>{
    //private static final String TAG = "RingInventoryListAdapter";

    private Context mContext;
    int mResource;

    public RingInventoryListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ringInventory> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String ringSize = getItem(position).getRingSize();
        String count = getItem(position).getCount();
        ringInventory ringinv = new ringInventory(ringSize,count);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvRingsize = (TextView) convertView.findViewById(R.id.ringSizeinv);
        TextView tvCount = (TextView) convertView.findViewById(R.id.ringInventoryCount);

        tvRingsize.setText(ringSize);
        tvCount.setText(count);

        return convertView;

    }
}
