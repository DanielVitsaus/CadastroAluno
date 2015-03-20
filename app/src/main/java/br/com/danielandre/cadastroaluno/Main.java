package br.com.danielandre.cadastroaluno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.com.danielandre.cadastroaluno.dao.AlunoDAO;
import br.com.danielandre.cadastroaluno.modelo.Aluno;


public class Main extends ActionBarActivity {

    //Atributos da tela
    //private EditText edNome;
    //private EditText tel;
    //private Button bt;
    private ListView listagemAluno;


    //TODO:Constante usada para logs no Logcat
    private final String TAG = "CADASTRO_ALUNO";
    //TODO:Constante usada como chave para o Map do objeto Bundle
    private final String ALUNOS_KEY = "LISTA";

    //TODO:Lista de aluno a ser exibidana tela
    //private LinkedList<String> listaAluno;
    private List<Aluno> listaAluno;

    //ArrayAdapter converte listas ou vetores em View
    private ArrayAdapter<Aluno> adapter;

    //TODO:Definicao do layout de exibicao da listagem
    private int adapterLayout = android.R.layout.simple_list_item_1;


    /*
    /**
     * TODO:Metodo de Callback para armazenar o estado da Activity
     * Chamado quando o Android vai destruir a Activity

    @Override
    protected void onSaveInstanceState(Bundle outState){

        //Inclui a lista de alunos no objeto Bundle.Map
        outState.putStringArrayList(ALUNOS_KEY, (ArrayList<String>)listaAluno);
        //Persistencia do objeto Bundle
        super.onSaveInstanceState(outState);

        //Lancemento da mensagem de log
        Log.i(TAG, "onSaveInstanceState(): " + listaAluno);
    }

    /**
     * TODO:Metodo de Callback para restaurar o estado da Activity.
     * Chamada antes da Activity destruida ser chamada pelo usuario
     *
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        //Recupera o estado do objeto Bundle
        super.onRestoreInstanceState(savedInstanceState);

        //Carrega a lista de alunos do Bundle.Map
        listaAluno = savedInstanceState.getStringArrayList(ALUNOS_KEY);

        adapter = new ArrayAdapter<String>(this, adapterLayout,listaAluno);

        if(listagemAluno == null){
            listagemAluno = (ListView) findViewById(R.id.listagemAluno);
         }
        listagemAluno.setAdapter(adapter);

        //Lancemento da mensagem de log
        Log.i(TAG, "onSaveInstanceState(): " + listaAluno);
    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //edNome = (EditText) findViewById(R.id.edNomeListagem);
        //bt = (Button) findViewById(R.id.btAddListagem);
        listagemAluno = (ListView) findViewById(R.id.listagemAluno);


        /* Verifica se há alguma informação salva, se sim add as informaçoes no array de alunos
        if (savedInstanceState != null) {
            listaAluno = savedInstanceState.getStringArrayList(ALUNOS_KEY);

        } else {
            listaAluno = new ArrayList<String>();
        }*/

        //listaAluno = new ArrayList<Aluno>();

        //adapter = new ArrayAdapter<Aluno>(this, adapterLayout,listaAluno);

        //listagemAluno.setAdapter(adapter);

        /*
        //TODO:Metodo para evento de clique do botao
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                listaAluno.add(edNome.getText().toString());

                edNome.setText("");

                adapter.notifyDataSetChanged();

            }
        });*/

        //TODO:Metodo para clique simple na ListView
        listagemAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Toast.makeText(Main.this, "Aluno: " + listaAluno.get(position), Toast.LENGTH_LONG).show();
            }
        });

        //TODO:Metodo para um clique e segurar na ListView
        listagemAluno.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Main.this, "Aluno: " + listaAluno.get(position) + " Longo", Toast.LENGTH_LONG).show();

                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /**
         * Outra forma de inflar o menu
         MenuInflater inflater = this.getMenuInflater();
         inflater.inflate(R.menu.menu_main,menu);
         */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_novo:
                Intent intent = new Intent(Main.this, Formulario.class);
                startActivity(intent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * TODO: Metodo para carregar a lista de aluno com dados do BD
     */
    private void carregaLista() throws SQLException {

        ///////// Acesso a camada de modelo //////////////////////////////////
        //Cria o objeto DAO - inicio da conexao com BD
        AlunoDAO dao = new AlunoDAO(this);

        this.listaAluno = dao.listar();
        //Fim da conexao com o BD
        dao.close();
        //////////////////////////////////////////////////////////////////////

        ///// Atualiza a tela /////////////////////////////////////////////////
        this.adapter = new ArrayAdapter<Aluno>(this, adapterLayout,listaAluno);

        this.listagemAluno.setAdapter(adapter);
        ////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void onResume(){

        super.onResume();

        try {

            this.carregaLista();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
