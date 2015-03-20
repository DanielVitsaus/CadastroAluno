package br.com.danielandre.cadastroaluno.helper;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import br.com.danielandre.cadastroaluno.Formulario;
import br.com.danielandre.cadastroaluno.R;
import br.com.danielandre.cadastroaluno.modelo.Aluno;

/**
 * Created by danielandre on 16/03/15.
 */

public class TrataDados {

    private EditText nome;
    private EditText telefone;
    private EditText enderecao;
    private EditText site;
    private EditText email;
    private SeekBar nota;
    private ImageView foto;

    private Aluno aluno;

    public TrataDados(Formulario form){

        nome = (EditText) form.findViewById(R.id.edNome);
        telefone =  (EditText) form.findViewById(R.id.edFone);
        enderecao = (EditText) form.findViewById(R.id.edEnde);
        site = (EditText) form.findViewById(R.id.edSite);
        email = (EditText) form.findViewById(R.id.edEmail);
        nota = (SeekBar) form.findViewById(R.id.sbNotas);
        foto = (ImageView) form.findViewById(R.id.img_aluno);

        aluno = new Aluno();
    }

    public Aluno getAluno(){

        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEnderecao(enderecao.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNotas(Float.valueOf(nota.getProgress()));

        return  aluno;
    }


}
