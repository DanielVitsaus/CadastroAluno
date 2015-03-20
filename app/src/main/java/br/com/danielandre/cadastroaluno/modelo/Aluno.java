package br.com.danielandre.cadastroaluno.modelo;

/**
 * Created by danielandre on 14/03/15.
 */
public class Aluno {

    private long id;
    private String nome;
    private String telefone;
    private String enderecao;
    private String site;
    private String email;
    private String foto;
    private float notas;

    @Override
    public String toString(){

        return nome;
    }

    public Aluno() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEnderecao() {
        return enderecao;
    }

    public void setEnderecao(String enderecao) {
        this.enderecao = enderecao;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getNotas() {
        return notas;
    }

    public void setNotas(float notas) {
        this.notas = notas;
    }
}
