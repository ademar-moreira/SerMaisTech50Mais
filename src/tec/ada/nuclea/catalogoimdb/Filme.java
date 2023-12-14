package tec.ada.nuclea.catalogoimdb;

import java.time.LocalDate;

public class Filme {
    private String nome;
    private LocalDate dataLancamento;
    private int orcamento;
    private String descircao;
    private Diretor diretor;
    private Ator[] elenco;
    public Filme (String nome, LocalDate dataLancamento, int orcamento, Diretor diretor, Ator[] elenco) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.diretor = diretor;
        this.elenco = elenco;
    }

    public String getNome() {
        return this.nome;
    }

    public LocalDate getDataLancamento() {
        return this.dataLancamento;
    }
    public int getOrcamento() {
        return this.orcamento;
    }
    public String getDescircao() {
        return this.descircao;
    }
    public Diretor getDiretor() {
        return this.diretor;
    }
    public Ator[] getElenco() {
        return this.elenco;
    }
}
