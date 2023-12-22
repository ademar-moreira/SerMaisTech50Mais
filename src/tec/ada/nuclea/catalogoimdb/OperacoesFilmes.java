package tec.ada.nuclea.catalogoimdb;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Optional;

public class OperacoesFilmes {
    private ArrayList<Filme> filmesDoBancoDeDados;
    private ArrayList<Ator> atoresDoBandoDeDados;
    private ArrayList<Diretor> diretoresDoBancoDeDados;
    public OperacoesFilmes () {
        this.filmesDoBancoDeDados = new ArrayList<>();
        this.atoresDoBandoDeDados = new ArrayList<>();
        this.diretoresDoBancoDeDados = new ArrayList<>();
    }

    public void salvarFilme(Filme filme) {
        boolean isCadastrao = false;
        for (Filme film : this.filmesDoBancoDeDados) {
            if (film.getNome().equalsIgnoreCase(filme.getNome())) {
                System.err.println("Filme já cadastrado.");
                isCadastrao = true;
            }
        }
        if (!isCadastrao) {
            filmesDoBancoDeDados.add(filme);
            System.out.println("Filme Cadastrado com sucesso!");
        }
    }

    public void associarAtorAoFilme(String nomeFilme, String nomeAtor) {
        Optional<Filme> filmeEncontrado = filmesDoBancoDeDados.stream()
                .filter(filme -> Normalizer.normalize(filme.getNome(), Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "")
                        .equalsIgnoreCase(Normalizer.normalize(nomeFilme, Normalizer.Form.NFD)
                                .replaceAll("[^\\p{ASCII}]", "")))
                .findAny();

        if (filmeEncontrado.isPresent()) {
            Optional<Ator> atorEncontrado = atoresDoBandoDeDados.stream()
                    .filter(ator -> Normalizer.normalize(ator.getNome(), Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "")
                            .equalsIgnoreCase(Normalizer.normalize(nomeAtor, Normalizer.Form.NFD)
                                    .replaceAll("[^\\p{ASCII}]", "")))
                    .findAny();

            if (atorEncontrado.isPresent()) {
                filmeEncontrado.get().adicionarAtor(atorEncontrado.get());

                atorEncontrado.get().adicionarFilme(filmeEncontrado.get());

                System.out.println("Ator associado ao filme com sucesso!");
            } else {
                System.out.println("Ator não encontrado!");
            }
        } else {
            System.out.println("Filme não encontrado!");
        }
    }

    public void associarDiretorAoFilme(String nomeFilme, String nomeDiretor) {
        Optional<Filme> filmeEncontrado = filmesDoBancoDeDados.stream()
                .filter(filme -> filme.getNome().equalsIgnoreCase(nomeFilme))
                .findAny();

        if (filmeEncontrado.isPresent()) {
            Optional<Diretor> diretorEncontrado = diretoresDoBancoDeDados.stream()
                    .filter(diretor -> diretor.getNome().equalsIgnoreCase(nomeDiretor))
                    .findAny();

            if (diretorEncontrado.isPresent()) {
                filmeEncontrado.get().adicionarDiretor(diretorEncontrado.get());

                diretorEncontrado.get().adicionarFilme(filmeEncontrado.get());

                System.out.println("Diretor associado ao filme com sucesso!");
            } else {
                System.out.println("Diretor não encontrado!");
            }
        } else {
            System.out.println("Filme não encontrado!");
        }
    }
    public Filme pesquisarFilmePorNome(String nome) {
        for (Filme filme : filmesDoBancoDeDados) {
            if (filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        System.out.println("Filme não encontrado.");
        return null;
    }

    public void excluirFilme(String nome){
        for (Filme film : this.filmesDoBancoDeDados) {
            if (film.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Removendo o fime " + nome);

            }
        }
    }
    public void salvarAtor(Ator ator) {
        boolean isCadastrao = false;
        for (Ator actor : this.atoresDoBandoDeDados) {
            if (actor.getNome().equalsIgnoreCase(ator.getNome())) {
                System.err.println("Ator já cadastrado.");
                isCadastrao = true;
            }
        }
        if (!isCadastrao) {
            atoresDoBandoDeDados.add(ator);
            System.out.println("Ator Cadastrado com sucesso!");
        }
    }
    public void excluirAtor(String nome){
        for (Ator actor : this.atoresDoBandoDeDados) {
            if (actor.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Removendo o ator " + nome);

            }
        }
    }

    public void salvarDiretor(Diretor diretor) {
        boolean isCadastrao = false;
        for (Diretor director : this.diretoresDoBancoDeDados) {
            if (director.getNome().equalsIgnoreCase(diretor.getNome())) {
                System.err.println("Diretor já cadastrado.");
                isCadastrao = true;
            }
        }
        if (!isCadastrao) {
            diretoresDoBancoDeDados.add(diretor);
            System.out.println("Diretor Cadastrado com sucesso!");
        }
    }
    public void excluirdiretor(String nome){
        for (Diretor director : this.diretoresDoBancoDeDados) {
            if (director.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Removendo o diretor " + nome);
            }
        }
    }

    public void setFilmesDoBancoDeDados(ArrayList<Filme> filmesDoBancoDeDados) {
        this.filmesDoBancoDeDados = filmesDoBancoDeDados;
    }

    public void setAtoresDoBandoDeDados(ArrayList<Ator> atoresDoBandoDeDados) {
        this.atoresDoBandoDeDados = atoresDoBandoDeDados;
    }

    public void setDiretoresDoBancoDeDados(ArrayList<Diretor> diretoresDoBancoDeDados) {
        this.diretoresDoBancoDeDados = diretoresDoBancoDeDados;
    }

    public ArrayList<Filme> getFilmesDoBancoDeDados() {
        return filmesDoBancoDeDados;
    }

    public ArrayList<Ator> getAtoresDoBandoDeDados() {
        return atoresDoBandoDeDados;
    }

    public ArrayList<Diretor> getDiretoresDoBancoDeDados() {
        return diretoresDoBancoDeDados;
    }
}

