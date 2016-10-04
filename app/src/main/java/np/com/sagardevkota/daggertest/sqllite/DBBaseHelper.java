package np.com.sagardevkota.daggertest.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import np.com.sagardevkota.daggertest.common.Const;


public class DBBaseHelper extends SQLiteOpenHelper{

	public DBBaseHelper(Context context) {
	//super(context, Environment.getExternalStorageDirectory()+"/tealdb/" +Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
    super(context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		String cmd;

        cmd= "CREATE TABLE IF NOT EXISTS " + Const.TABLE_REPO + "(id INTEGER PRIMARY KEY, name TEXT,full_name TEXT,description TEXT)";
        db.execSQL(cmd);


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE IF EXISTS " + Const.TABLE_REPO);

        // Create tables again
        onCreate(db);

	}
	
}