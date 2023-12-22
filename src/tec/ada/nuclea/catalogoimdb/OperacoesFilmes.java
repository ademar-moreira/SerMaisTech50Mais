package tec.ada.nuclea.catalogoimdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        //boolean filmeEncontrado = false;

         String isFilme = String.valueOf(filmesDoBancoDeDados.stream().filter(nome -> {
            return nome.contains(nomeFilme);
        }).findAny().orElse(null));

         if(isFilme != null) {
             filmesDoBancoDeDados.stream().filter(nome -> {
                 return nome.contains(nomeFilme);
             }).findAny().ifPresent(filmeAchado -> filmeAchado.adicionarAtor(Ator.valueOf(nomeAtor)));

             System.out.println("Ator associado ao filme com sucesso!");

         } else {
             System.out.println("Filme não encontrado!");
         }


        /*for (Filme film : filmesDoBancoDeDados) {
            if (film.getNome().equalsIgnoreCase(nomeFilme)) {
                film.adicionarAtor(Ator.valueOf(nomeAtor));
                System.out.println("Ator associado ao filme com sucesso!");
                filmeEncontrado = true;
                break;
            }
        }*/
       /* if (!filmeEncontrado) {
            System.out.println("Filme não encontrado!");
        }*/

        /*boolean isCadastrao = false;
        for (Filme film : this.filmesDoBancoDeDados) {
            if (film.getNome().equalsIgnoreCase(String.valueOf(nome))) {
                filmesDoBancoDeDados.add(elenco);
                System.out.println("Elenco vinculado com sucesso!");
                isCadastrao = true;
                break;
            }
        }
        if (!isCadastrao) {
            System.out.println("Filme não encontrado!");
        }*/
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

    public void excluirFime(String nome){
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

