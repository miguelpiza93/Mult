package com.app.multiplicando;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper
{
    private static DataBase bdProductos;

    public static DataBase getInstanse( Context c )
    {
        if( bdProductos == null )
            bdProductos = new DataBase(c);
        return bdProductos;
    }

    private DataBase(Context context)
    {
        super(context, "Puntajes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Creacion tabla juego
        db.execSQL("CREATE TABLE Juego (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)");
        // Creacion tabla puntaje
        db.execSQL("CREATE TABLE Puntaje (_id_Juego INTEGER, tabla INTEGER, nivel INTEGER, puntaje INTEGER, FOREIGN KEY (_id_Juego) REFERENCES Juego(_id), PRIMARY KEY(_id_Juego, tabla, nivel) )");

        //for para los juegos
        for ( int i = 1; i <= 2; i++ )
        {
            db.execSQL("INSERT INTO Juego VALUES ( null, 'Juego " + i +"')");

            //for para las tablas
            for ( int j = -1; j <= 10; j++ )
            {
                //for para los niveles
                for ( int k = 0; k < 4; k++ )
                {
                    db.execSQL("INSERT INTO Puntaje VALUES ( " + i + ", " + j + ", " + k +", 0 )");
                }
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {

    }

    public int darMayorPuntaje( int juego_id, int tabla, int nivel )
    {
        int p = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Puntaje p WHERE p._id_Juego = '" + juego_id +"' AND p.tabla = '" + tabla +"' AND p.nivel = '" + nivel +"'", null);
        while (cursor.moveToNext()){
            p = cursor.getInt(3);
        }
        cursor.close();
        db.close();
        return p;
    }

    public void actualizarPuntaje( int juego_id, int tabla, int nivel, int puntaje )
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Puntaje p WHERE p._id_Juego = '" + juego_id +"' AND p.tabla = '" + tabla +"' AND p.nivel = '" + nivel +"'", null);

        int id = 0;
        while (cursor.moveToNext())
        {
            id = cursor.getInt(0);
        }
        db = getWritableDatabase();
        ContentValues nuevos = new ContentValues();
        nuevos.put("puntaje", puntaje);
        db.update("Puntaje", nuevos, "_id_Juego = ? AND tabla = ? AND nivel = ?", new String[]{id+"",tabla+"", nivel+""});
        db.close();
    }
}
