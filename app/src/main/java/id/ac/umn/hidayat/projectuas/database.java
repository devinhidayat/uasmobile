package id.ac.umn.hidayat.projectuas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

//    private static final String nama_db = "smart_parking.db";
//    private static final String nama_tabel = "payment";

    private static final String DATABASE_DB = "smart_parking.db";
    private static final int VERSION = 1;

    public static final String TABLE_PAYMENT = "Payment";
    public static final String ID = "_id";
    public static final String FIELD_CHECKIN = "jam_checkin";
    public static final String FIELD_CHECKOUT = "jam_checkout";
    public static final String FIELD_TOTAL = "total";

    public database(@Nullable Context context) {
        super(context, DATABASE_DB, null, VERSION);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PAYMENT +
                "(" + ID + " integer PRIMARY KEY," +
                FIELD_CHECKIN + " text," +
                FIELD_CHECKOUT + " text," +
                FIELD_TOTAL + " integer)" );

//        sqLiteDatabase.execSQL("create table "+nama_tabel+"(id_transaction integer primary key autoincrement, jam_checkin text, jam_checkout text, total integer)");
//        sqLiteDatabase.execSQL("create table user (_id integer primary key autoincrement, nama text, email text, password text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXIST"+nama_tabel);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXIST user");
        onCreate(sqLiteDatabase);
    }
}
