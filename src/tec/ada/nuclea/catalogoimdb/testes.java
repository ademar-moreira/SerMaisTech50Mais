package tec.ada.nuclea.catalogoimdb;
import java.time.LocalDate;

public class testes {
    public static void main(String[] args) {
        // Obtendo a data atual
        LocalDate hoje = LocalDate.now();
        System.out.println("Data atual: " + hoje);

        // Criando uma data específica
        LocalDate dataEspecifica = LocalDate.of(2023, 12, 8);
        System.out.println("Data específica: " + dataEspecifica);

        // Manipulando datas
        LocalDate amanha = hoje.plusDays(1);
        System.out.println("Amanhã será: " + amanha);

        // Verificando diferença entre datas
        long diferencaEmDias = dataEspecifica.until(hoje).getDays();
        System.out.println("Diferença em dias: " + diferencaEmDias);
    }
}

