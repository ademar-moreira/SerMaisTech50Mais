package tec.ada.nuclea.catalogoimdb;

import java.time.LocalDate;

public class Ator extends ProfissionalCinema {

    public Ator(String nome, LocalDate dataNascimento, String nacionalidade) {
        super(nome, dataNascimento, nacionalidade);
    }

    public Ator(String nome) {
        super(nome);
    }

    public static Ator valueOf(String nomeAtorString) {
        return new Ator(nomeAtorString);
    }

}
