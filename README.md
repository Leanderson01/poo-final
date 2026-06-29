# Sistema de Gerenciamento de Biblioteca — Trabalho Final de POO

Projeto desenvolvido em **Java** para a disciplina **SMD0017 — Programação
Orientada a Objetos** (UFC / IUVI), Prof. Gilvan Maia.

O sistema modela uma biblioteca: cadastra um acervo com diferentes tipos de
itens (livros, revistas e DVDs), cadastra usuários, realiza empréstimos e
devoluções e calcula multas por atraso — cada tipo de item com sua própria
regra de prazo e multa.

> **Equipe:** Leanderson Nunes da Rocha — matrícula 545589 (ver `EQUIPE.txt`).
> **Diagrama de classes:** ver `DIAGRAMA-DE-CLASSES.md`.

---

## 1. Pré-requisitos

- **JDK 8 ou superior** instalado (o projeto foi testado com **JDK 17**).
- Para conferir se o Java está instalado, rode no terminal:

```bash
java -version
javac -version
```

Não há nenhuma biblioteca externa nem ferramenta de build (Maven/Gradle):
basta o `javac` e o `java`.

---

## 2. Estrutura do projeto

```
trabalho-final-poo/
├── README.md                 <- este manual
├── EQUIPE.txt                <- componentes da equipe
├── DIAGRAMA-DE-CLASSES.md    <- diagrama de classes (Mermaid)
└── src/
    └── biblioteca/
        ├── Emprestavel.java       (interface)
        ├── ItemAcervo.java        (classe abstrata)
        ├── Livro.java             (herda de ItemAcervo)
        ├── Revista.java           (herda de ItemAcervo)
        ├── DVD.java               (herda de ItemAcervo)
        ├── Endereco.java
        ├── Usuario.java
        ├── Emprestimo.java
        ├── Biblioteca.java
        ├── GeradorRelatorio.java
        └── Main.java              (ponto de entrada)
```

---

## 3. Como compilar e executar

Abra o terminal **dentro da pasta `trabalho-final-poo/`**.

### Linux / macOS

```bash
# 1) Compilar: gera os .class na pasta out/
javac -encoding UTF-8 -d out src/biblioteca/*.java

# 2) Executar a classe principal
java -cp out biblioteca.Main
```

### Windows (PowerShell ou Prompt de Comando)

```powershell
# 1) Compilar
javac -encoding UTF-8 -d out src\biblioteca\*.java

# 2) Executar
java -cp out biblioteca.Main
```

> Dica: se aparecerem acentos errados no console do Windows, rode
> `chcp 65001` antes para ativar UTF-8, ou execute
> `java -Dfile.encoding=UTF-8 -cp out biblioteca.Main`.

### Saída esperada

```
Usuário cadastrado: Leanderson Nunes da Rocha (mat. 545589) — Av. da Universidade, 2853, Fortaleza - CEP 60020-181

=========== ACERVO: Biblioteca Central da UFC ===========
  Livro:   "O Cortiço", por Aluísio Azevedo (1890) — 304 págs, ISBN 978-8501012340  [DISPONÍVEL]
      prazo de empréstimo: 14 dias
  Revista: "Superinteressante" — edição nº 458 (2024)  [DISPONÍVEL]
      prazo de empréstimo: 7 dias
  DVD:     "Cidade de Deus", dir. Fernando Meirelles (2002) — 130 min  [DISPONÍVEL]
      prazo de empréstimo: 3 dias
Total de itens no acervo: 3

======== EMPRÉSTIMOS REALIZADOS ========
  Empréstimo #1 | Leanderson Nunes da Rocha -> O Cortiço | devolver até 2026-06-15
  Empréstimo #2 | Leanderson Nunes da Rocha -> Cidade de Deus | devolver até 2026-06-04
Total acumulado de empréstimos: 2

Devolução do livro com atraso -> multa: R$ 9.00
Devolução do DVD no prazo     -> multa: R$ 0.00

Total de empréstimos já registrados no sistema: 2
```

A multa do livro é `6 dias de atraso × R$ 1,50 = R$ 9,00` (devolvido 20 dias
depois, com prazo de 14). O DVD foi devolvido dentro do prazo, sem multa.

---

## 4. Como o projeto atende a cada critério de avaliação

| # | Critério | Onde está no código |
|---|----------|---------------------|
| a | **≥ 7 classes** | 11 tipos: `Emprestavel`, `ItemAcervo`, `Livro`, `Revista`, `DVD`, `Endereco`, `Usuario`, `Emprestimo`, `Biblioteca`, `GeradorRelatorio`, `Main` |
| b | **≥ 3 heranças genuínas** | `Livro`, `Revista` e `DVD` estendem `ItemAcervo`; são usadas via `List<ItemAcervo>` (generalização útil) |
| c | **≥ 5 associações** (simples, dependência, agregação, composição) | **Composição:** `Usuario`◆`Endereco`, `Biblioteca`◆`Emprestimo`. **Agregação:** `Biblioteca`◇`Usuario`, `Biblioteca`◇`ItemAcervo`. **Associação simples:** `Emprestimo`→`Usuario`, `Emprestimo`→`ItemAcervo`. **Dependência:** `GeradorRelatorio`⇢`Biblioteca` |
| d | **≥ 2 abstrações** | Interface `Emprestavel` + classe abstrata `ItemAcervo` |
| e | **≥ 3 polimorfismos** | `getDescricao()`, `getPrazoEmprestimoDias()` e `calcularMulta()` chamados sobre referências `ItemAcervo`/`Emprestavel` (ver `GeradorRelatorio` e `Emprestimo`) |
| f | **3 modificadores de acesso** | `public` (APIs), `protected` (`titulo/autor/ano/setDisponivel` em `ItemAcervo`), `private` (atributos encapsulados) |
| g | **1 atributo e 1 método estáticos** | `Emprestimo.totalEmprestimos` (atributo) e `Emprestimo.getTotalEmprestimos()` (método) — o `main()` não conta |
| h | **Diagrama representa o código** | `DIAGRAMA-DE-CLASSES.md` |
| i | **Manual claro e objetivo** | este `README.md` |

---

## 5. Regras de negócio (resumo)

| Item    | Prazo de empréstimo | Multa por dia de atraso |
|---------|---------------------|-------------------------|
| Livro   | 14 dias             | R$ 1,50                 |
| Revista | 7 dias              | R$ 2,00                 |
| DVD     | 3 dias              | R$ 3,00                 |

Cada tipo define essas regras sobrescrevendo os métodos `getPrazoEmprestimoDias()`
e `calcularMulta()` herdados de `ItemAcervo` / `Emprestavel`.
