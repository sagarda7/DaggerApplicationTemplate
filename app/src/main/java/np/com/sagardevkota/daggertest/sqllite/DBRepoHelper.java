package np.com.sagardevkota.daggertest.sqllite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import np.com.sagardevkota.daggertest.common.Const;
import np.com.sagardevkota.daggertest.models.Repository;

public class DBRepoHelper extends DBBaseHelper {
 
 
   
   // private ServerSyncListener<JSONObject> listener;
    public DBRepoHelper(Context context) {
        super(context);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
       super.onUpgrade(db, oldVersion, newVersion);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    public void addItem(Repository item) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        //values.put("id", "NULL"); // Contact Phone
        values.put("name", item.getName()); // Contact Name
        values.put("full_name", item.getFullName());
        values.put("description", item.getDescription());


        // Inserting Row
        db.insert(Const.TABLE_REPO, null, values);
        db.close(); // Closing database connection
        Log.d("sagarda7","added added");
    }

    public List<Repository> getAllItems() {
        List<Repository> itemlist = new ArrayList<Repository>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Const.TABLE_REPO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Repository item = new Repository();
                item.setFullName(cursor.getString(1));
                item.setFullName(cursor.getString(2));
                item.setDescription(cursor.getString(3));
                // Adding contact to list
                itemlist.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return itemlist;
    }
 


}