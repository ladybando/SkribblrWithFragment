package com.example.android.skribblr.util

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.provider.BaseColumns._ID
import com.example.android.skribblr.model.Skribble
import com.example.android.skribblr.model.Users
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.SKRIBBLE_COLUMN_ID
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.SQL_DELETE_NOTES
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.SKRIBBLES_TABLE_NAME
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.SQL_CREATE_NOTES
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.SQL_CREATE_TASKS
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.SQL_DELETE_USERS
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.USERS_TABLE_NAME
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.KEY_USER_SKRIBBLE_ID_FK
import com.example.android.skribblr.util.DBHandler.FeedReaderContract.FeedEntry.USERS_COLUMN_ID

//connects to SQLite Database
class DBHandler(
    context: Context?,
    table_name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, table_name, factory, version) {

    private var db: SQLiteDatabase? = null
    var cv = ContentValues()
    val selection = "${SKRIBBLE_COLUMN_ID} LIKE ?"
    val selectionArgs = arrayOf("skribbleEntry")

    val selectUsers = "$USERS_COLUMN_ID LIKE ?"
    val selectionUsersArgs = arrayOf("user")

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_NOTES)
        db.execSQL(SQL_CREATE_TASKS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_NOTES)
        db.execSQL(SQL_DELETE_USERS)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun openDataBase() {
        db = this.writableDatabase
    }

    fun insertTask(skribble: Skribble, users: Users) {
        //adding new tasks to table
        openDataBase()
        //create new map of values where column names are keys
        cv.apply {
            put(USERS_COLUMN_ID, users.userID)
            put(KEY_USER_SKRIBBLE_ID_FK, skribble.note)
        }
        // Insert the new row, returning the primary key value of the new row
        db?.insert(SKRIBBLES_TABLE_NAME, null, cv)
    }

    private val allSkribble: List<Long>
        get() {
            val skribIDs = mutableListOf<Long>()

            db = readableDatabase
            var skribCursor: Cursor? = null
            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            val projection = arrayOf(_ID, SKRIBBLE_COLUMN_ID, USERS_COLUMN_ID)

            //// Filter results WHERE "skribble" = 'skribbleEntry'
            val select = "$SKRIBBLE_COLUMN_ID = ?"
            val selectArgs = arrayOf("skribbleEntry")

            //how results should be sorted
            val sortOrder = "$KEY_USER_SKRIBBLE_ID_FK DESC"

            //will store state of database(db) if db closed prior to execution
            try {
                if (skribCursor!!.moveToFirst()) {
                    do {
                        skribCursor = db?.query(
                            SKRIBBLES_TABLE_NAME,
                            projection,
                            select,
                            selectArgs,
                            null,
                            null,
                            sortOrder,
                            null
                        )
                        with(skribCursor!!){
                            while (moveToNext()){
                            val skribID  = skribCursor.getLong(skribCursor.getColumnIndex(_ID))
                                skribIDs.add(skribID)
                            }
                        }
                    }while (skribCursor!!.moveToNext())
                }
            } finally {
                if(skribCursor != null && !skribCursor.isClosed) {
                    skribCursor.close()
                }
            }
            return skribIDs
        }

    //update table
    fun updateTask(id: Int, skribble: Skribble?) {
        db = writableDatabase
        cv.apply { put(SKRIBBLE_COLUMN_ID, skribble?.note) }
        db!!.update(SKRIBBLES_TABLE_NAME, cv, selection, selectionArgs)
        db!!.update(USERS_TABLE_NAME, cv, selectUsers, selectionUsersArgs)
    }

    //delete skribble from table
    fun deleteSkribble(id: Int) {
        db?.delete(SKRIBBLES_TABLE_NAME, selection, selectionArgs)
        db?.delete(USERS_TABLE_NAME, selectUsers, selectionUsersArgs)
    }

    object FeedReaderContract {
        object FeedEntry : BaseColumns {
            //Table names
            const val SKRIBBLES_TABLE_NAME = "notesTasks"
            const val USERS_TABLE_NAME = "users"

            //foreign key and username creation
            const val KEY_USER_SKRIBBLE_ID_FK = "userID"
            private const val USER_NAME = "userName"

            //Column names
            const val SKRIBBLE_COLUMN_ID = "skribbleEntry"
            const val USERS_COLUMN_ID = "user"

            internal const val SQL_CREATE_NOTES =
                "CREATE TABLE $SKRIBBLES_TABLE_NAME(" +
                        "$_ID INTEGER PRIMARY KEY," + //define primary key
                        "$SKRIBBLE_COLUMN_ID TEXT," +
                        "$KEY_USER_SKRIBBLE_ID_FK TEXT)" //define foreign key

            internal const val SQL_CREATE_TASKS =
                "CREATE TABLE $USERS_TABLE_NAME(" +
                        "$_ID INTEGER PRIMARY KEY," +
                        "$USER_NAME TEXT)"
            internal const val SQL_DELETE_NOTES = "DROP TABLE IF EXISTS $SKRIBBLE_COLUMN_ID"
            internal const val SQL_DELETE_USERS = "DROP TABLE IF EXISTS $USERS_TABLE_NAME"
        }
    }

    companion object {
        // increment the database version after change of database schema
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "SkribbleDB.db"
    }
}