package c.kaitlyn.chainmaillecalculations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class invDBHandler extends SQLiteOpenHelper{

    private static final String TAG = "invDBHandler";

    private static final String TABLE_NAME = "Inventory";
    private static final String COLUMN_NAME_RINGSIZE = "RingSize";
    private static final String COLUMN_NAME_RINGCOUNT = "RingCount";

    public invDBHandler(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NAME_RINGSIZE + " STRING PRIMARY KEY, " +
                COLUMN_NAME_RINGCOUNT + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String ringSize, String count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_RINGSIZE, ringSize);
        contentValues.put(COLUMN_NAME_RINGCOUNT, count);

        //Log.d(TAG, "addData: Adding " + ringSize + " to " + TABLE_NAME);

        long res = db.insert(TABLE_NAME, null, contentValues);

        if (res == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean insertData(String ringSize, String count ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_RINGSIZE, ringSize);
        contentValues.put(COLUMN_NAME_RINGCOUNT, count);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
}
