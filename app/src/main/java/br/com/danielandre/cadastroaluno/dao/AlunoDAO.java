package br.com.danielandre.cadastroaluno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.danielandre.cadastroaluno.modelo.Aluno;

/**
 * Created by danielandre on 19/03/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    //constates para auxiliar no controle de versao
    private static final int VERSAO = 1;
    private static final String TABELA = "Aluno";
    private static final String DATABASE = "MPAlunos";

    //Constate para log no Logcat
    private static final String TAG = "CADASTRO_ALUNO";

    public AlunoDAO(Context context){

        super(context,DATABASE,null,VERSAO);
    }

    public AlunoDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Definicao do comando DDl a ser executado
        String ddl = "CREATE TABLE " + TABELA  + "( "
                     + "id INTEGER PRIMARY KEY, "
                     + "nome TEXT, telefone TEXT, endereco TEXT, "
                     + "site TEXT, email TEXT, foto TEXT, "
                     + "nota REAL)";

        //Execucao do comando no SQLite
        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Definicao do comando para destruir a tabela ALUNO
        String sql = "DROP TABLE IF EXISTS " + TABELA;

        //Execucao do comando de destricao
        db.execSQL(sql);

        //Chamada ao metodo de construcao da bade de dados
        onCreate(db);

    }

    public void cadastrar(Aluno aluno){

        //Objeto para armazenar os valores deos campos
        ContentValues values = new ContentValues();

        //Definicao de valores dos campos da tabela
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEnderecao());
        values.put("site", aluno.getSite());
        values.put("email", aluno.getEmail());
        values.put("foto", aluno.getFoto());
        values.put("nota", aluno.getNotas());

        //Inserir os dados do aluno no BD
        getWritableDatabase().insert(TABELA, null, values);
        Log.i(TAG, "Aluno cadastrado: " + aluno.getNome());

    }

    //TODO: Metodo para recuperar os dados do Aluno no BD
    public List<Aluno> listar() throws SQLException {

        //Definicao da colecao de alunos
        List<Aluno> lista = new ArrayList<Aluno>();

        //Define a introducao do SQL para buscar todos os alunos
        String sql = "SELECT * FROM Aluno order by nome";

        // Objeto que recebe os registro do BD
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()){

            Aluno aluno = new Aluno();

            // Carrega os atributos do aluno com os dados do BD
            aluno.setId(cursor.getLong(0));
            aluno.setNome(cursor.getString(1));
            aluno.setTelefone(cursor.getString(2));
            aluno.setEnderecao(cursor.getString(3));
            aluno.setSite(cursor.getString(4));
            aluno.setEmail(cursor.getString(5));
            aluno.setFoto(cursor.getString(6));
            aluno.setNotas(cursor.getFloat(7));

            lista.add(aluno);
        }

        cursor.close();

        return lista;

        /*
         try{

            while (cursor.moveToNext()){

                Aluno aluno = new Aluno();

                // Carrega os atributos do aluno com os dados do BD
                aluno.setId(cursor.getLong(0));
                aluno.setNome(cursor.getString(1));
                aluno.setTelefone(cursor.getString(2));
                aluno.setEnderecao(cursor.getString(3));
                aluno.setSite(cursor.getString(4));
                aluno.setEmail(cursor.getString(5));
                aluno.setFoto(cursor.getString(6));
                aluno.setNotas(cursor.getFloat(7));

                lista.add(aluno);
            }

        }catch (SQLException e){
            Log.e(TAG, e.getMessage());
        }finally {
            cursor.close();
        }
         */
    }

    public void deletar(Aluno aluno){

        //Definição de array de parametros
        String[] args = {aluno.getId().toString()};

        getWritableDatabase().delete(TABELA, "id=?", args);

        Log.i(TAG, "Aluno deletado: " + aluno.getNome());

    }
}
