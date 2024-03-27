package com.cadastraprofessores.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;

import com.cadastraprofessores.Beans.Professor;
/*
 * Classe responsável pelo acesso ao banco de dados
 * Class responsable for the database
 * */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "professores_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABELA = "professores";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String MATERIA = "materia";
    private static final String EMAIL = "email";
    private static final String TELEFONE = "telefone";
    //Query que cria o banco e a tabela
    private static final String CREATE_TABLE_PROFESSORES = "CREATE TABLE "
            + TABELA + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NOME + " TEXT NOT NULL, "+
            MATERIA + " TEXT NOT NULL, "+
            EMAIL + " TEXT NOT NULL, "+
            TELEFONE + " VARCHAR " +
            "); ";
    /*
    * Contrutor
    */       
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
  /*
    * OnCreate
    */ 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROFESSORES);
    }
  /*
    * OnUpgrade
    */ 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABELA + "'");
        onCreate(db);
    }
    /*
    * Método que adiciona um professor ao bando de dados
    * Methos that add a professor in the database
    */ 
    public long addProfessor(String nome, String materia, String email, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Criar content values
        ContentValues values = new ContentValues();
        values.put(NOME, nome);
        values.put(MATERIA, materia);
        values.put(EMAIL, email);
        values.put(TELEFONE, telefone);
        //inserir na tabela
        long insert = db.insert(TABELA, null, values);

        return insert;
    }
    /*
    * Método que retorna todos os professores cadastrados no banco de dados
    * Method that returns all professors in the database
    */ 
    @SuppressLint("Range")
    public ArrayList<Professor> getProfessores() {
        ArrayList<Professor> professores = new ArrayList<Professor>();

        String selectQuery = "SELECT  * FROM " + TABELA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // loop nas colunas da tabela....loop in table coluns
        if (c.moveToFirst()) {
            do {
                Professor professor = new Professor();

                    professor.setId(c.getInt(c.getColumnIndex(ID)));
                    professor.setNome(c.getString(c.getColumnIndex(NOME)));
                    professor.setMateria(c.getString(c.getColumnIndex(MATERIA)));
                    professor.setEmail(c.getString(c.getColumnIndex(EMAIL)));
                    professor.setTelefone(c.getString(c.getColumnIndex(TELEFONE)));

                // adiciona a lista
                professores.add(professor);
            } while (c.moveToNext());
        }
        c.close();
        return professores;
    }
    /*
    * Método que edita um professor no banco de dados
    * Method edits a professor in the data base
    */ 
    public int editarProfessor(int id, String nome, String materia, String email, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Cria content
        ContentValues values = new ContentValues();
        values.put(NOME, nome);
        values.put(MATERIA, materia);
        values.put(EMAIL, email);
        values.put(TELEFONE, telefone);
        // edita linha, edit line
        return db.update(TABELA, values, ID + " = ?",
                new String[]{String.valueOf(id)});
    }
    /*
    * Método que recebe um id e deleta o professor correspondente
    * Method recives an id and deletes the professor with the id
    */ 
    public void deletarProfessor(int id) {
        // deleta pelo id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA, ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
