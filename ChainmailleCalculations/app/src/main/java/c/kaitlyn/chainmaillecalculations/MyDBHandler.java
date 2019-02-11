package c.kaitlyn.chainmaillecalculations;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inventoryDB.db";
    public static final String TABLE_NAME = "Inventory";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_RINGSIZE = "RINGSIZE";
    public static final String COLUMN_RINGCOUNT = "COUNT";
    //initialize the database
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                "INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT )";
        db.execSQL(CREATE_TABLE);*/
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, RINGSIZE TEXT, COUNT INTEGER )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String ringSize, String count ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RINGSIZE, ringSize);
        contentValues.put(COLUMN_RINGCOUNT, count);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


    /*public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(inventory inventory) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, inventory.getRing_size());
        values.put(COLUMN_NAME, inventory.getCount());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public inventory findHandler(String studentname) {

        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        inventory inventory = new inventory();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            inventory.setCount(Integer.parseInt(cursor.getString(0)));
            inventory.setRing_size(cursor.getString(1));
            cursor.close();
        } else {
            inventory = null;
        }
        db.close();
        return inventory;
    }
    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        inventory inventory = new inventory();
        if (cursor.moveToFirst()) {
            inventory.setCount(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {String.valueOf(inventory.getCount())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(int ID, String name) {SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }*/



}

