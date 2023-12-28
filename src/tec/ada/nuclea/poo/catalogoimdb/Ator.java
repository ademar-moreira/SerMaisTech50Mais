package tec.ada.nuclea.poo.catalogoimdb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Ator extends ProfissionalCinema {
    private Filme[] filmes;

    public Ator(String nome, LocalDate dataNascimento, String nacionalidade) {
        super(nome, dataNascimento, nacionalidade);
    }

    public Filme[] getFilmes() {
        return filmes;
    }

    public Ator(String nome) {
        super(nome);
    }

    public void adicionarFilme(Filme filme) {
        if (this.filmes == null) {
            this.filmes = new Filme[]{filme};
        } else {
            ArrayList<Filme> novoFilmes = new ArrayList<>(Arrays.asList(this.getFilmes()));
            if (!novoFilmes.contains(filme)) {
                novoFilmes.add(filme);
                this.filmes = novoFilmes.toArray(new Filme[0]);
            }
        }
    }

}
