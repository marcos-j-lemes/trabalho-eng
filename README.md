### Princípios de Projeto - Atividade Prática I

**Trabalho de Engenharia de Software**
**Estudantes:** Marcos Júnior Lemes e Mônica Cancellier

**Descrição geral:** Tem objetivo de verificar a questão da coesão, acoplamento, ocultamento e integridade das informações 
Estrutura do projeto: Para esse projeto foi utilizado gradle, utilizando orientação a objetos, está dividindo em classes no qual foi feita a refatoração.


## <strong>Documentação</strong> 


### Parte 1 Análise do Sistema

### 1. Coesão

**Problemas identificados:**
- A classe `Pedido` faz tudo: calcula frete, aplica desconto, atualiza estoque, processa pagamento, envia notificação, gera relatório e salva no banco.
- Ela tem pelo menos 8 responsabilidades diferentes em uma única classe.
- Métodos como `atualizarEstoque()` e `enviarNotificacao()` não têm relação direta com o conceito de "Pedido".

### 2. Acoplamento

**Problemas identificados:**
- Dependência direta com `BancoDeDados.salvarPedido()` e `BancoDeDados.salvarLog()`.
- Não tem interfaces, tudo é implementação concreta.
- O método `finalizar()` chama vários métodos na ordem certa, mas se quisermos mudar a ordem ou adicionar uma etapa nova, temos que mexer na classe.

### 3. Ocultamento de Informação

**Problemas identificados:**
- Todos os atributos são `public` (ex: `public List<String> produtos`).
- Qualquer classe externa pode acessar e modificar as listas diretamente.
- Quebra total do encapsulamento.

### 4. Integridade Conceitual

**Problemas identificados:**
- Nomes inconsistentes: `calcularTotal()` e `calcularFrete()` mas `aplicarDesconto()` (não tem "calcular" no nome).
- Lógica de desconto fixa dentro do método, sem padrão.
- O método `processarPagamento` recebe string e faz `if/else` ao invés de usar algo mais flexível.

---

## Parte 2 — Refatoração

### O que fizemos:

Separamos a classe `Pedido` original em várias classes menores:

- **Models:** `Pedido`, `Cliente`, `ItemPedido`, `StatusPedido`
- **Services:** `CalculadoraService`, `EstoqueService`, `PagamentoService`, `NotificacaoService`, `RelatorioService`, `FinalizacaoService`
- **Repository:** `PedidoRepository`
- **entity:** `BancoDeDados`

### Principais mudanças:

1. **Cliente agora é uma classe separada** (antes estava como atributos soltos dentro de Pedido)
2. **ItemPedido** foi criado para agrupar nome, preço e quantidade (ao invés de 3 listas paralelas)
3. **CalculadoraService** concentra tudo que é cálculo (total, frete, desconto)
4. **FinalizacaoService** orquestra o fluxo de finalização do pedido
5. **Atributos agora são privados** com getters/setters

---

## Parte 3 — Documentação

### 1. Coesão

**O que foi alterado?**
- Cada service tem uma responsabilidade única.
- `CalculadoraService` só faz cálculos.
- `PagamentoService` só processa pagamento.

**Por que é mais coesa?**
- Antes a classe `Pedido` precisava ser modificada por qualquer motivo (cálculo, pagamento, estoque...)
- Agora se quisermos mudar a regra de frete, mexemos só em `CalculadoraService`.
- Se quisermos adicionar SMS além de email, mexemos só em `NotificacaoService`.

### 2. Acoplamento

**Dependências removidas:**
- `Pedido` não depende mais diretamente de `BancoDeDados`.
- `Pedido` não chama mais `RelatorioService` internamente.

**Onde usamos abstrações?**
- Poderíamos criar interfaces como `PagamentoStrategy`, `NotificacaoStrategy`, mas para esse exercício mantivemos simples.
- O `PedidoRepository` age como uma abstração entre o pedido e o banco.

### 3. Ocultamento de Informação

**O que foi encapsulado?**
- Todos os atributos viraram `private`.
- As listas de produtos agora estão dentro da classe `Pedido` como `List<ItemPedido>`.

**Como o acesso foi controlado?**
- Usamos getters e setters onde necessário.
- O acesso aos itens do pedido é feito por `pedido.getItens()` mas a lista interna não pode ser modificada diretamente de fora (poderíamos usar `Collections.unmodifiableList()` se quiséssemos).

### 4. Integridade Conceitual

**Padronização de nomes:**
- Todos os services agora têm nomes no padrão `XxxService`.
- Métodos de cálculo começam com `calcular` (`calcularTotal`, `calcularFrete`).
- `StatusPedido` usa constantes ao invés de strings soltas.

**Inconsistências removidas:**
- Antes tinha `calcularTotal()` e `aplicarDesconto()` – agora `aplicarDesconto` virou parte da `CalculadoraService` com nome consistente.
- A lógica de pagamento com `if/else` foi substituída por `switch`, mas ainda pode melhorar com Strategy Pattern no futuro.

---

## Código

**Todo o código está disponível em:**

>/refatoracao/src/main/java/com/eng/refatoracao

O `codigo_professor` contém o código original do professor, enquanto o código alterado encontra-se dentro da estrutura do Gradle.