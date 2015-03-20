package br.com.danielandre.cadastroaluno;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.danielandre.cadastroaluno.dao.AlunoDAO;
import br.com.danielandre.cadastroaluno.helper.TrataDados;
import br.com.danielandre.cadastroaluno.modelo.Aluno;


public class Formulario extends Activity {

    private Button botSalvar;
    private TrataDados dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        dados =  new TrataDados(this);
        botSalvar = (Button) findViewById(R.id.sbSalvar);

        botSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno a = dados.getAluno();

                //inciando a conexao com o BD
                AlunoDAO bdAluno = new AlunoDAO(Formulario.this);

                //Chamado do metodo para cadastrar o aluno
                bdAluno.cadastrar(a);

                //Encerra a connexao com o BD
                bdAluno.close();

                //Toast.makeText(Formulario.this, a.getNome(),Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
