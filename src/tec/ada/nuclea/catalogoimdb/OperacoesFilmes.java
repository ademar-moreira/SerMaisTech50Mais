package tec.ada.nuclea.catalogoimdb;

import java.util.ArrayList;
public class OperacoesFilmes {
    private ArrayList<Filme> filmesDoBancoDeDados;
    public OperacoesFilmes () {
        this.filmesDoBancoDeDados = new ArrayList<>();
    }

    public void salvarFilme(Filme filme) {
        boolean isCadastrao = false;
        for (Filme film : this.filmesDoBancoDeDados) {
            if (film.getNome().equals(filme.getNome())) {
                System.err.println("Filme já cadastrado.");
                isCadastrao = true;
            }
        }
        if (!isCadastrao) {
            filmesDoBancoDeDados.add(filme);
        }
    }
    public void excluirFime(String nome){
        for (Filme film : this.filmesDoBancoDeDados) {
            if (film.getNome().equals(nome)) {
                System.out.println("Removendo o fime " + nome);

            }
        }
    }

    public ArrayList<Filme> getFilmesDoBancoDeDados() {
        return filmesDoBancoDeDados;
    }

    private ArrayList<Ator> AtoresDoBancoDeDados;
}
