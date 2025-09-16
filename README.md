![Logo Projedata](https://i.imgur.com/nd8h6UP.png)

# Teste Prático – Iniflex

Este desafio foi desenvolvido para o **Teste Prático - Iniflex**.  
O teste cobre todos os requisitos listados no teste, do 1 ao 3.12, e abaixo apresento **cada requisito com seu resultado visual**.

---

## Tecnologias e Ferramentas Utilizadas

- **Java 21**  
- **IntelliJ IDEA**
  
---

## 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.

**Implementação:**  
No `Main.java`, foi criada uma `List<Funcionario>` para armazenar os funcionários. Cada funcionário foi adicionado à lista usando o construtor da classe `Funcionario`.  
A inserção foi feita na mesma ordem da tabela fornecida no enunciado.

---

## 3.2 – Remover o funcionário “João” da lista

**Implementação:**  
Após inserir todos os funcionários na lista, o funcionário “João” foi removido usando o método `remove(index)` da `List`.

---

## 3.3 Imprimir todos os funcionários com todas suas informações, sendo que:
  - informação de data deve ser exibido no formato dd/mm/aaaa;
  - informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.

**Implementação:**  
Foi criado um método auxiliar `imprimirFuncionarios(List<Funcionario> funcionarios)` no `Main.java`.  
- O método percorre cada funcionário da lista usando um loop `for`.  
- A data de nascimento é formatada com `DateTimeFormatter`.  
- O salário é formatado com `DecimalFormat`.  
- A saída inclui o nome, data, salário e função de cada funcionário.

**Resultado no console:** <br>
![3.3 - Impressão todos os Funcionarios](https://imgur.com/IU1KmaC.png)

---

## 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

**Implementação:**  
Foi utilizado o método `forEach` da lista de funcionários para atualizar os salários:  
- Para cada funcionário, foi calculado 10% do salário atual.  
- O salário do funcionário é atualizado somando o valor do aumento ao salário original.  
- Após a atualização, o método `imprimirFuncionarios` é chamado novamente para exibir os novos valores formatados no console.

**Resultado no console:** <br>
![3.4 - Aumento de Salário](https://imgur.com/RoBZoQ7.png)

---

## 3.5 – Agrupar os funcionários por função em um MAP

**Implementação:**  
Foi utilizado o **Stream API** para agrupar os funcionários por função:  
- O método `Collectors.groupingBy` foi usado para criar um `Map<String, List<Funcionario>>`, onde a chave é a função e o valor é a lista de funcionários que exercem a função.  

---

## 3.6 – Imprimir os funcionários, agrupados por função

**Implementação:**  
Foi criado um método auxiliar `imprimirFuncionariosAgrupados(Map<String, List<Funcionario>> map)` para exibir os funcionários agrupados:  
- Para cada função (chave do mapa), imprime-se o nome da função.  
- Cada funcionário da lista correspondente é impresso.  

**Resultado no console:** <br>
![3.6 - Impressão Agrupada](https://imgur.com/41bH02r.png)

---

## 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12

**Implementação:**  
Foi utilizado o **Stream API** para filtrar os funcionários que nascem em outubro (10) ou dezembro (12):  
- Para cada funcionário, obtém-se o mês da data de nascimento (`getMonthValue()`).  
- Apenas os funcionários do mês 10 ou 12 são exibidos.

**Resultado no console:** <br>
![3.8 - Aniversariantes](https://imgur.com/0y9s4F8.png)

---

## 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

**Implementação:**  
Para encontrar o funcionário mais velho:  
- Usou-se `stream().min(...)` comparando as datas de nascimento.  
- A idade foi calculada com `Period.between(dataNascimento, LocalDate.now()).getYears()`.  

**Resultado no console:** <br>
![3.9 - Mais velho](https://imgur.com/lHjV1JW.png)

---

## 3.10 – Imprimir a lista de funcionários por ordem alfabética

**Implementação:**  
- A lista de funcionários foi ordenada pelo nome usando `Comparator.comparing(Funcionario::getNome)`.  

**Resultado no console:** <br>
![3.10 - Ordem alfabética](https://imgur.com/eDQtzQu.png)

---

## 3.11 – Imprimir o total dos salários dos funcionários

**Implementação:**  
- Foi somado o salário de todos os funcionários usando um loop `for` e acumulando em um `BigDecimal total`.
- O salário é somado o com aumento de 10% feito no passo **3.4**.

**Resultado no console:** <br>
![3.11 - Total salários](https://imgur.com/eAI0b7x.png)

---

## 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

**Implementação:**  
- Considerando o salário mínimo como R$1212,00, cada salário foi dividido pelo valor do salário mínimo usando `BigDecimal.divide()`.  

**Resultado no console:** <br>
![3.12 - Salários mínimos](https://imgur.com/3RHgSzO.png)



