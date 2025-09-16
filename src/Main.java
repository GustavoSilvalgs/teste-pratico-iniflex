import model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        decimalFormat = new DecimalFormat("#,##0.00", symbols);
    }

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

//        3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

//        3.2 – Remover o funcionário “João” da lista.
        funcionarios.remove(1);

//        3.3
        imprimirFuncionarios(funcionarios);

//        3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(f -> {
            BigDecimal aumento = f.getSalario().multiply(new BigDecimal("0.10"));
            f.setSalario(f.getSalario().add(aumento));
        });

        System.out.println("\nApós aumento de 10%:\n");
        imprimirFuncionarios(funcionarios);

//        3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\nFuncionários agrupados por função:\n");
        imprimirFuncionariosAgrupados(funcionariosPorFuncao);

//        3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12:\n");
        funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(f -> System.out.println(
                        f.getNome() +
                                " | " + f.getDataNascimento().format(formatter) +
                                " | " + decimalFormat.format(f.getSalario()) +
                                " | " + f.getFuncao()
                ));

//        3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        System.out.println("\nFuncionário com maior idade:\n");
        Funcionario maisVelho = funcionarios.stream()
                .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + "\n");
        }

//        3.10 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        imprimirFuncionarios(funcionarios);

//        3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            total = total.add(f.getSalario());
        }
        System.out.println("\nTotal dos salários: R$ " + decimalFormat.format(total) + "\n");

//        3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos.");
        });

    }

    /*
    3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
            • informação de data deve ser exibido no formato dd/mm/aaaa;
            • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
    */
    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("Nome | Data Nascimento | Salário | Função");
        for (Funcionario f : funcionarios) {
            System.out.println(
                    f.getNome() +
                            " | " + f.getDataNascimento().format(formatter) +
                            " | " + decimalFormat.format(f.getSalario()) +
                            " | " + f.getFuncao()
            );
        }
    }

//    3.6 – Imprimir os funcionários, agrupados por função.
    private static void imprimirFuncionariosAgrupados(Map<String, List<Funcionario>> map) {
        map.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println(
                     " " + f.getNome() +
                            " | " + f.getDataNascimento().format(formatter) +
                            " | " + decimalFormat.format(f.getSalario())
            ));
            System.out.println();
        });
    }
}
