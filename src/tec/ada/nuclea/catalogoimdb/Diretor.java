package tec.ada.nuclea.catalogoimdb;

import java.time.LocalDate;

public class Diretor extends ProfissionalCinema{
    public Diretor(String nome, LocalDate dataNascimento, String nacionalidade, Filme[] filmes) {
        super(nome, dataNascimento, nacionalidade, filmes);
    }

}
