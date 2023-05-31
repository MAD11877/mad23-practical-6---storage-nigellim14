package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

// Create db with extending SQLiteOpenHelper
public class DBHandler extends SQLiteOpenHelper {
    private static final int VERSION =1;
    private static final String DATABASE = "users.db";
    public DBHandler(@Nullable Context context)
    {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    // Create database (Name, Description, ID, Followed)
    public void onCreate(SQLiteDatabase db) {

        //Create a new database
        String cmdText = "CREATE TABLE user (name TEXT, description TEXT, id INTEGER, followed INTEGER)";
        db.execSQL(cmdText);

        // Generating and inserting 20 random users into table
        for(int i=0; i<20; i++)
        {
            ContentValues values = new ContentValues();
            values.put("name", "Name" + new Random().nextInt());
            values.put("description","Description " + new Random().nextInt());
            values.put("followed", new Random().nextInt()%2 == 0);
            db.insert("user", null, values);
        }
    }

    @Override
    // Check if users is exists in the db table
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);
    }


    // Retrieving of user through db
    public ArrayList<User> getUsers()
    {
        ArrayList<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM user", null);

        if(cursor.moveToNext())
        {
            User user = new User();
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(cursor.getInt(2));
            user.setFollowed(cursor.getInt(3)==0?false:true);

            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    // Update corresponding value in db
    public void updateUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("followed", user.isFollowed());

        int count = db.update("user", value, "id = ?", new String[]{ "" + user.getId()} );
        db.close();
    }
}