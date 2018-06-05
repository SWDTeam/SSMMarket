package test.kietpt.smartmarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ssmarket";
    public static final String TABLE_NAME_ACCOUNT = "Account";
    public static final int DATABASE_VERSION = 2;

    // CursorFactory la con tro dung de duyet database
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    // truy van ko tra ket qua nhu : create,insert,update,delete
//    public void queryData(String sql) {
//        SQLiteDatabase database = getWritableDatabase(); // getWrite dung de ghi gia tri xuong db
//        database.execSQL(sql);
//    }

    //truy van tra ve ket qua : select * from where
//    public Cursor getData(String sql) {
//        SQLiteDatabase database = getReadableDatabase(); // getRead dung de doc du  lieu tu db
//        return database.rawQuery(sql, null);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ACCOUNT + "(" +
                "userId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email VARCHAR(50) UNIQUE," +
                "userName VARCHAR (50), " +
                "gender VARCHAR (10)," +
                "phone VARCHAR (12), " +
                "password VARCHAR (20)," +
                "address VARCHAR (500)," +
                "status VARCHAR (20) ) "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCustomer(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", account.getEmail());
        values.put("userName", account.getUsername());
        values.put("gender", account.getGender());
        values.put("phone", account.getPhone());
        values.put("password", account.getPassword());
        values.put("address", account.getAddress());
        values.put("status", account.getStatus());
        db.insert(TABLE_NAME_ACCOUNT, null, values);
        Log.e("DB INSERT + ", "them thanh cong trong Database ");
        db.close();
    }

    public Account getCustomerInfo(String email) {
        Account account = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Account where email = '" + email + "' ", null);
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String mail = cursor.getString(1);
            String username = cursor.getString(2);
            String gender = cursor.getString(3);
            String phone = cursor.getString(4);
            String pass = cursor.getString(5);
            String address = cursor.getString(6);
            String status = cursor.getString(7);
            account = new Account(id, mail, username, gender, phone, pass, address, status);
        }
        return account;
    }

    public void getAllCustomer() {
        Log.e("123456", "da vao getCustomer() ");
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor dataCustomer = sqlDB.rawQuery("Select * From Account", null);
        while (dataCustomer.moveToNext()) {
            int userId = dataCustomer.getInt(0);
            String email = dataCustomer.getString(1);
            String username = dataCustomer.getString(2);
            String gender = dataCustomer.getString(3);
            String phone = dataCustomer.getString(4);
            String passsword = dataCustomer.getString(5);
            String address = dataCustomer.getString(6);
            String status = dataCustomer.getString(7);
        }
    }

    public boolean checkEmail(String mail) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From Account Where email = '" + mail + "'", null);
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }

    public void updateCustomer(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", account.getEmail());
        values.put("userName", account.getUsername());
        values.put("gender", account.getGender());
        values.put("phone", account.getPhone());
        values.put("address", account.getAddress());
        db.update(TABLE_NAME_ACCOUNT,values,"email = '"+account.getEmail().toString()+"'",null);

        Log.e("DB UPDATE + ", "UPDATE thanh cong trong Database ");
        db.close();
    }
    public void updatePassword(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email",account.getEmail());
        values.put("password",account.getPassword());
        db.update(TABLE_NAME_ACCOUNT,values,"email = '"+account.getEmail().toString()+"'",null);
        Log.e("UPDATE PASSOWORD + ","Update password thanh cong");
        db.close();
    }
}
