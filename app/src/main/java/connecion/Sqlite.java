package connecion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by viguerjhok on 28/09/2015.
 */
public class Sqlite extends SQLiteOpenHelper {
    String table_usuario="CREATE TABLE usuario(id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, campo1 text, campo2 text);";

    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS usuario");
        this.onCreate(db);
    }
}
