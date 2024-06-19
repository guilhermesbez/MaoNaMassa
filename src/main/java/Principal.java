import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        /*
        Inserindo todos os funcionários
         */
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));


        /*
         Removendo o funcionário "João"
         */
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        /*
         Imprimindo todos os funcionários
         */
        System.out.println("Funcionários:");
        for (Funcionario f : funcionarios) {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: R$%,.2f, Função: %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    f.getSalario(),
                    f.getFuncao());
        }

        /*
         Aumentando o salário de todos os funcionários em 10%
         */
        for (Funcionario f : funcionarios) {
            f.aumentarSalario(10);
        }

        /*
         Agrupando os funcionários por função
         */
        Map<String, List<Funcionario>> funcoesFuncionarios = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        /*
         Imprimindo os funcionários agrupados por função
         */
        System.out.println("\nFuncionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcoesFuncionarios.entrySet()) {
            System.out.printf("%s:%n", entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.printf("  Nome: %s, Salário: R$%,.2f%n", f.getNome(), f.getSalario());
            }
            System.out.println();
        }

        /*
         Imprimindo os funcionários que fazem aniversário em outubro e dezembro
         */
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.printf("Nome: %s, Data de Nascimento: %s%n",
                        f.getNome(),
                        f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }

        /*
         Imprimindo o funcionário com a maior idade
         */
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparing(f -> f.getDataNascimento().until(LocalDate.now()).getYears()))
                .orElse(null);
        if (funcionarioMaisVelho != null) {
            int idade = funcionarioMaisVelho.getDataNascimento().until(LocalDate.now()).getYears();
            System.out.printf("\nFuncionário mais velho: Nome: %s, Idade: %d%n",
                    funcionarioMaisVelho.getNome(), idade);
        }

        /*
         Imprimindo a lista de funcionários por ordem alfabética
         */
        List<Funcionario> funcionariosOrdenadosAlfabeticamente = new ArrayList<>(funcionarios);
        funcionariosOrdenadosAlfabeticamente.sort(Comparator.comparing(Pessoa::getNome));
        System.out.println("\nFuncionários ordenados alfabeticamente:");
        for (Funcionario f : funcionariosOrdenadosAlfabeticamente) {
            System.out.printf("Nome: %s, Salário: R$%,.2f%n", f.getNome(), f.getSalario());
        }

        /*
         Imprimindo o total dos salários dos funcionários
         */
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("\nTotal de salários: R$%,.2f%n", totalSalarios);

        /*
         Imprimindo quantos salários mínimos ganha cada funcionário
         */
        double salarioMinimo = 1212.00;
        System.out.println("\nQuantidade de salários mínimos que cada funcionário ganha:");
        for (Funcionario f : funcionarios) {
            double salariosMinimosFuncionario = f.getSalario().doubleValue() / salarioMinimo;
            System.out.printf("Nome: %s, Salários Mínimos: %.2f%n", f.getNome(), salariosMinimosFuncionario);
        }
    }
}