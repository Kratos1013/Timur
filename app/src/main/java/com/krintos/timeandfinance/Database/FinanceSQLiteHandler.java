package com.krintos.timeandfinance.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Toast;

import com.krintos.timeandfinance.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;



public class FinanceSQLiteHandler extends SQLiteOpenHelper {
    public static final String DataBase_Name = "finance.db";
    public static final String Table_Name_spent = "categoriesforspent";
    public static final String Table_Name_income= "categoriesforincome";
    public static final String KEY_ID = "id";
    public static final String KEY_Category = "category";
    public static final String KEY_Trans_Name = "trans_name";
    public static final String KEY_Date = "date";
    public static final String KEY_Amount = "amount";
    public static final String TABLE_CATEGORIES_Spent = "categorynamesforspent";
    public static final String TABLE_CATEGORIES_Income = "categorynamesforincome";
    public static final String KEY_CATEGORY_ICONS = "icons";
    public static final String TABLE_NEW_ICON = "newicon";
    public static final String TABLE_NAME_ACTIVIES = "activities";
    public static final String KEY_ACTIVITY_NAME = "activityname";
    public static final String KEY_ACTIVITY_DESCRIPTION = "activitydescription";
    public static final String KEY_ACTIVITY_PRICE = "price";
    public static final String KEY_ACTIVITY_DATE = "date";
    public static final String KEY_ACTIVITY_PRICE_TODAY = "pricetoday";
    public static final String TABLE_NAME_PASSIVES = "passives";
    public static final String KEY_PASSIVE_NAME = "passivename";
    public static final String KEY_PASSIVE_DESCRIPTION = "passivedescription";
    public static final String KEY_PASSIVE_PRICE = "passiveprice";
    public static final String KEY_PASSIVE_PRICE_PER_MONTH = "passivepricepermonth";
    public static final String KEY_PASSIVE_DATE_END = "passivedateend";
    public static final String TABLE_NAME_COLLECTIONS = "collections";
    public static final String KEY_COLLECTIONS_AIM = "aim";
    public static final String KEY_COLLECTIONS_FULL_AMOUNT = "fullamount";
    public static final String KEY_COLLECTIONS_AMOUNT_NOW = "amountnow";
    public static final String KEY_COLLECTIONS_AMOUNT_PER_MONTH = "amountpermonth";


    public Context context;
    public FinanceSQLiteHandler(Context context) {
        super(context, DataBase_Name, null, 1);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Categories for Finance

        String CREATE_CATEGORY_TABLE_FOR_SPENT = "CREATE TABLE " + Table_Name_spent + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Category + " TEXT,"
                + KEY_Trans_Name + " TEXT," + KEY_Date + " TEXT,"
                + KEY_Amount + " TEXT, " + KEY_CATEGORY_ICONS +" VARCHAR(255)" + ");";
        db.execSQL(CREATE_CATEGORY_TABLE_FOR_SPENT);

        String CREATE_CATEGORY_TABLE_FOR_INCOME = "CREATE TABLE " + Table_Name_income + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Category + " TEXT,"
                + KEY_Trans_Name + " TEXT," + KEY_Date + " TEXT,"
                + KEY_Amount + " TEXT,"+ KEY_CATEGORY_ICONS +" VARCHAR(255)" + ");";
        db.execSQL(CREATE_CATEGORY_TABLE_FOR_INCOME);

        String  CREATE_CATEGORY_NAMES_SPENT = "CREATE TABLE " + TABLE_CATEGORIES_Spent + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Category + " TEXT," + KEY_CATEGORY_ICONS + " VARCHAR(255)"+");";
        db.execSQL(CREATE_CATEGORY_NAMES_SPENT);

        String  CREATE_CATEGORY_NAMES_INCOME = "CREATE TABLE " + TABLE_CATEGORIES_Income + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Category + " TEXT," + KEY_CATEGORY_ICONS +" VARCHAR(255)"+ ");";
        db.execSQL(CREATE_CATEGORY_NAMES_INCOME);
        String  CREATE_NEW = "CREATE TABLE " + TABLE_NEW_ICON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Category + " TEXT," + KEY_CATEGORY_ICONS +" VARCHAR(255)"+ ");";
        db.execSQL(CREATE_NEW);

        // Tables for Activites

        String CREATE_ACTIVITIES = "CREATE TABLE " + TABLE_NAME_ACTIVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ACTIVITY_NAME + " TEXT,"
                + KEY_ACTIVITY_DESCRIPTION + " TEXT," + KEY_ACTIVITY_DATE + " TEXT,"
                + KEY_ACTIVITY_PRICE + " TEXT, " + KEY_ACTIVITY_PRICE_TODAY + " TEXT" + ");";
        db.execSQL(CREATE_ACTIVITIES);

        // Table for Passives

        String CREATE_PASSIVES = "CREATE TABLE " + TABLE_NAME_PASSIVES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PASSIVE_NAME + " TEXT,"
                + KEY_PASSIVE_DESCRIPTION + " TEXT," + KEY_PASSIVE_PRICE + " TEXT,"
                + KEY_PASSIVE_PRICE_PER_MONTH + " TEXT, " + KEY_PASSIVE_DATE_END+ " TEXT" + ");";
        db.execSQL(CREATE_PASSIVES);

        // Tables for Collections

        String CREATE_COLLECTIONS = "CREATE TABLE " + TABLE_NAME_COLLECTIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COLLECTIONS_AIM + " TEXT,"
                + KEY_COLLECTIONS_FULL_AMOUNT + " TEXT," + KEY_COLLECTIONS_AMOUNT_NOW + " TEXT,"
                + KEY_COLLECTIONS_AMOUNT_PER_MONTH + " TEXT " + ");";
        db.execSQL(CREATE_COLLECTIONS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name_spent);
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name_income);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORIES_Spent);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORIES_Income);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NEW_ICON);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_ACTIVIES);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_PASSIVES);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_COLLECTIONS);

        // Create tables again
        onCreate(db);
    }
    public boolean inserttocategory(String categoryName, String trans_name, String date,String amount,String table_name,String icon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Category,categoryName);
        values.put(KEY_Trans_Name,trans_name);
        values.put(KEY_Date,date);
        values.put(KEY_Amount, amount);
        values.put(KEY_CATEGORY_ICONS,icon);
        long result = db.insert(table_name, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean inserttoactivity(String Name, String description, String date,String price,String pricetoday){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACTIVITY_NAME,Name);
        values.put(KEY_ACTIVITY_DESCRIPTION,description);
        values.put(KEY_ACTIVITY_DATE,date);
        values.put(KEY_ACTIVITY_PRICE, price);
        values.put(KEY_ACTIVITY_PRICE_TODAY,pricetoday);
        long result = db.insert(TABLE_NAME_ACTIVIES, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean inserttopassive(String Name, String description,String price, String price_per_month,String date_end){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSIVE_NAME,Name);
        values.put(KEY_PASSIVE_DESCRIPTION,description);
        values.put(KEY_PASSIVE_PRICE,price);
        values.put(KEY_PASSIVE_PRICE_PER_MONTH, price_per_month);
        values.put(KEY_PASSIVE_DATE_END,date_end);
        long result = db.insert(TABLE_NAME_PASSIVES, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean inserttocollections(String aim, String full_amount,String amount_now, String amount_per_month){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COLLECTIONS_AIM,aim);
        values.put(KEY_COLLECTIONS_FULL_AMOUNT,full_amount);
        values.put(KEY_COLLECTIONS_AMOUNT_NOW,amount_now);
        values.put(KEY_COLLECTIONS_AMOUNT_PER_MONTH, amount_per_month);
        long result = db.insert(TABLE_NAME_COLLECTIONS, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean addcategoryname(String categoryName, String categoryType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Category,categoryName);
        long result =db.insert(categoryType, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean addcategorynamewithicon(String categoryName, String  icon, String categoryType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Category,categoryName);
        values.put(KEY_CATEGORY_ICONS, icon);
        long result =db.insert(categoryType, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAlldatas(String Table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+Table_name, null);
        return result;
    }

    public Cursor getAlldatasbydate(String table,String category, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+table+" where "+KEY_Category+" = \"" + category + "\" and "+KEY_Date+" like "+"\"%"+date+"\";",null);
        return result;
    }
    public Cursor getCategoryNames(String Table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+Table_name, null);
        return result;
    }
    public Cursor IsCategoryExist(String name,String categoryType){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+categoryType+" where "+KEY_Category+" = \"" + name + "\";",null);
        return result;
    }
    public Cursor IsDataExist(String trans_name,String time, String amount,String table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+table_name+" where "+KEY_Trans_Name+" = \"" + trans_name + "\""
                +" and "+ KEY_Date+" = \"" + time +"\""+" and "+KEY_Amount+" = \"" + amount +"\";",null);
        return result;
    }
    public Cursor geticon(String table,String category){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+table+" where "+KEY_Category+" = \"" + category + "\";",null);
        return result;
    }

    /*public void addfinancecategoryimages() {
        ArrayList<String> names = new ArrayList<String >();
        ArrayList<byte[]> icons = new ArrayList<byte[]>();
        String food = "Food";
        names.add(food);
        String transport ="Transport";
        names.add(transport);
        names.add("Electronics");

        Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.food);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

        byte[] img=bos.toByteArray();
        Bitmap bitmap1=BitmapFactory.decodeResource(context.getResources(), R.drawable.transport);

        ByteArrayOutputStream bos1=new ByteArrayOutputStream();

        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, bos1);

        byte[] img1=bos1.toByteArray();
        Bitmap bitmap2=BitmapFactory.decodeResource(context.getResources(), R.drawable.electronics);

        ByteArrayOutputStream bos2=new ByteArrayOutputStream();

        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, bos2);
        byte[] img2=bos2.toByteArray();

        icons.add(img);
        icons.add(img1);
        icons.add(img2);

        for (int i=0;i<=2;i++){
            String got = names.get(i);
            byte[] icgot = icons.get(i);
            addcategorynamewithicon(got,icgot,TABLE_CATEGORIES_Spent);
        }


    }*/
    public Integer deletecategory(String table,String table2,String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = getAlldatas(table2);
        while (result.moveToNext()){
            db.delete(table2, KEY_Category+" = ?",new String[]{name});
        }
        return db.delete(table, KEY_Category+" =  ?", new String[] {name});

    }
    public Integer deletedata(String table,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table, KEY_ID+" = ?", new String[]{id});

    }
    public boolean updatecategoryname(String name, String newname,String icon, String tablename){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Category, newname);
        values.put(KEY_CATEGORY_ICONS,icon);
        db.update(tablename, values, KEY_Category+" = ?",new String[]{name});
        return true;
    }
    public boolean updatedatas(String name, String newname,String tablename){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Category, newname);
        db.update(tablename, values, KEY_Category+" = ?", new String[]{name});
        return true;
    }
    public boolean updateactivities(String id, String name, String desc,String date, String init, String todayprice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACTIVITY_NAME, name);
        values.put(KEY_ACTIVITY_DESCRIPTION, desc);
        values.put(KEY_ACTIVITY_DATE, date);
        values.put(KEY_ACTIVITY_PRICE, init);
        values.put(KEY_ACTIVITY_PRICE_TODAY, todayprice);
        db.update(TABLE_NAME_ACTIVIES, values, KEY_ID+" = ?", new String[]{id});
        return true;
    }
    public boolean updatepassives(String id, String name, String desc,String price, String pricepermonth, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSIVE_NAME, name);
        values.put(KEY_PASSIVE_DESCRIPTION, desc);
        values.put(KEY_PASSIVE_PRICE, price);
        values.put(KEY_PASSIVE_PRICE_PER_MONTH, pricepermonth);
        values.put(KEY_PASSIVE_DATE_END, date);
        db.update(TABLE_NAME_PASSIVES, values, KEY_ID+" = ?", new String[]{id});
        return true;
    }
    public boolean updatecollections(String id, String aim, String amount,String amountpermonth, String amountnow){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COLLECTIONS_AIM, aim);
        values.put(KEY_COLLECTIONS_FULL_AMOUNT, amount);
        values.put(KEY_COLLECTIONS_AMOUNT_PER_MONTH, amountpermonth);
        values.put(KEY_COLLECTIONS_AMOUNT_NOW, amountnow);
        db.update(TABLE_NAME_COLLECTIONS, values, KEY_ID+" = ?", new String[]{id});
        return true;
    }
    public boolean insertnew(String link, String name){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( KEY_Category,name);
        values.put( KEY_CATEGORY_ICONS, link);
        long result =db.insert(TABLE_NEW_ICON, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getnewicon(String Table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+Table_name, null);
        return result;
    }
    public boolean update(String table,String desc,String category, String date, String amount,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Category,category);
        values.put(KEY_Trans_Name, desc);
        values.put(KEY_Date, date);
        values.put(KEY_Amount, amount);
        db.update(table,values, KEY_ID+" = ?", new String[]{id});
        return true;
    }


}
