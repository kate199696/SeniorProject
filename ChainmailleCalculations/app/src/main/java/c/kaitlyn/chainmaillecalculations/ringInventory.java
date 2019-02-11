package c.kaitlyn.chainmaillecalculations;

import android.os.Parcel;
import android.os.Parcelable;

public class ringInventory implements Parcelable {
    private String ringSize;
    private String count;

    public ringInventory(String ringSize, String count) {
        this.ringSize = ringSize;
        this.count = count;
    }


    public String getRingSize() {
        return ringSize;
    }

    public void setRingSize(String ringSize) {
        this.ringSize = ringSize;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


    public ringInventory(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        this.ringSize = data[0];
        this.count = data[1];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.ringSize, this.count});
    }

    public static final Parcelable.Creator<ringInventory> CREATOR = new Parcelable.Creator<ringInventory>() {

        @Override
        public ringInventory createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new ringInventory(source);  //using parcelable constructor
        }

        @Override
        public ringInventory[] newArray(int size) {
// TODO Auto-generated method stub
            return new ringInventory[size];
        }

    };
}