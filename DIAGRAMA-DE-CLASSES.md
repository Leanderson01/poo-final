# Diagrama de Classes — Sistema de Biblioteca

O diagrama abaixo representa **fielmente** o código-fonte entregue em `src/biblioteca/`.
Ele está escrito em [Mermaid](https://mermaid.js.org/), que é renderizado
automaticamente pelo GitHub. Para editar/visualizar fora do GitHub, cole o
código em <https://mermaid.live>.

## Imagem do diagrama

![Diagrama de classes do Sistema de Biblioteca](diagrama_classes.png)

## Legenda das relações

| Notação | Significado | Exemplo no projeto |
|---------|-------------|--------------------|
| `..|>`  | Realização de interface | `ItemAcervo` realiza `Emprestavel` |
| `--|>`  | Herança (generalização) | `Livro`, `Revista`, `DVD` → `ItemAcervo` |
| `*--`   | **Composição** (parte morre com o todo) | `Usuario` ◆ `Endereco`; `Biblioteca` ◆ `Emprestimo` |
| `o--`   | **Agregação** (parte vive sem o todo) | `Biblioteca` ◇ `Usuario`; `Biblioteca` ◇ `ItemAcervo` |
| `-->`   | **Associação simples** | `Emprestimo` → `Usuario`; `Emprestimo` → `ItemAcervo` |
| `..>`   | **Dependência** (uso por parâmetro) | `GeradorRelatorio` ⇢ `Biblioteca` |

> Nos membros: `*` = método abstrato, `$` = membro estático,
> `#` = `protected`, `-` = `private`, `+` = `public`.

## Diagrama

```mermaid
classDiagram
    direction LR

    class Emprestavel {
        <<interface>>
        +getPrazoEmprestimoDias() int
        +calcularMulta(long diasAtraso) double
    }

    class ItemAcervo {
        <<abstract>>
        #titulo String
        #autor String
        #ano int
        -codigo String
        -disponivel boolean
        +getDescricao() String*
        +isDisponivel() boolean
        #setDisponivel(boolean) void
    }

    class Livro {
        -numeroPaginas int
        -isbn String
        +getDescricao() String
        +getPrazoEmprestimoDias() int
        +calcularMulta(long) double
    }

    class Revista {
        -edicao int
        +getDescricao() String
        +getPrazoEmprestimoDias() int
        +calcularMulta(long) double
    }

    class DVD {
        -duracaoMinutos int
        +getDescricao() String
        +getPrazoEmprestimoDias() int
        +calcularMulta(long) double
    }

    class Endereco {
        -rua String
        -cidade String
        -cep String
        +formatar() String
    }

    class Usuario {
        -nome String
        -matricula String
        -endereco Endereco
        +getNome() String
    }

    class Emprestimo {
        -totalEmprestimos int$
        -id int
        -usuario Usuario
        -item ItemAcervo
        +getTotalEmprestimos() int$
        +registrarDevolucao(LocalDate) double
        +getPrevisaoDevolucao() LocalDate
    }

    class Biblioteca {
        -nome String
        -acervo List~ItemAcervo~
        -usuarios List~Usuario~
        -emprestimos List~Emprestimo~
        +adicionarItem(ItemAcervo) void
        +cadastrarUsuario(Usuario) void
        +emprestar(Usuario, ItemAcervo, LocalDate) Emprestimo
        +devolver(Emprestimo, LocalDate) double
    }

    class GeradorRelatorio {
        +gerarRelatorioAcervo(Biblioteca) String$
        +gerarRelatorioEmprestimos(Biblioteca) String$
    }

    ItemAcervo ..|> Emprestavel : realiza
    Livro --|> ItemAcervo : heranca
    Revista --|> ItemAcervo : heranca
    DVD --|> ItemAcervo : heranca

    Usuario *-- Endereco : composicao
    Biblioteca o-- Usuario : agregacao
    Biblioteca o-- ItemAcervo : agregacao
    Biblioteca *-- Emprestimo : composicao
    Emprestimo --> Usuario : assoc simples
    Emprestimo --> ItemAcervo : assoc simples
    GeradorRelatorio ..> Biblioteca : dependencia
```
