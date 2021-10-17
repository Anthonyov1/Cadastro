package com.example.cadastro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CadastroP {

    public static void inserir(Context context, Cadastro cadastro){
        ContentValues values = new ContentValues();
        values.put("nome", cadastro.getNome());
        values.put("sobrenome", cadastro.getSobrenome());
        values.put("idade", cadastro.getIdade());
        values.put("estados", cadastro.getEstados());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("cadastros", null, values);

    }

    public static void editar(Context context, Cadastro cadastro){
        ContentValues values = new ContentValues();
        values.put("nome", cadastro.getNome());
        values.put("sobrenome", cadastro.getSobrenome());
        values.put("idade", cadastro.getIdade());
        values.put("estados", cadastro.getEstados());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("cadastros", values, "id = " + cadastro.getId(), null);

    }

    public static void excluir(Context context, int idCadastro){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("cadastros", "id = " + idCadastro, null);
    }


    public static List<Cadastro> getCadastro(Context context){
        List<Cadastro> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cadastros ORDER BY nome" , null);
        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Cadastro prod = new Cadastro();
                prod.setId(cursor.getInt(0));
                prod.setNome(cursor.getString(1));
                prod.setSobrenome(cursor.getString(2));
                prod.setIdade(cursor.getString(3));
                prod.setEstados(cursor.getString(4));
                lista.add(prod);

            }while(cursor.moveToNext() );
        }

        return lista;
    }

    public static Cadastro getCadastrosPessoasById(Context context, int idCadastro){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cadastros WHERE id = " + idCadastro , null);
        if (cursor.getCount() > 0){

            cursor.moveToFirst();

            Cadastro prod = new Cadastro();
            prod.setId(cursor.getInt(0));
            prod.setNome(cursor.getString(1));
            prod.setSobrenome(cursor.getString(2));
            prod.setIdade(cursor.getString(3));
            prod.setEstados(cursor.getString(4));

            return prod;
        }else{
            return null;
        }
    }
}
