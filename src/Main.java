import model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

//        3.2
        funcionarios.remove(1);

//        3.3
        imprimirFuncionarios(funcionarios);

//        3.4
        funcionarios.forEach(f -> {
            BigDecimal aumento = f.getSalario().multiply(new BigDecimal("0.10"));
            f.setSalario(f.getSalario().add(aumento));
        });

        System.out.println("\nApós aumento de 10%:\n");
        imprimirFuncionarios(funcionarios);

//        3.5
        Map<String, List<Funcionario>> funcionariosPorFuncao =
                funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\nFuncionários agrupados por função:\n");
        imprimirFuncionariosAgrupados(funcionariosPorFuncao);

//        3.6
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

    }

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
