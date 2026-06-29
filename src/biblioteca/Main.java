package biblioteca;

import java.time.LocalDate;

/**
 * Ponto de entrada da aplicação. Demonstra, na prática, todas as
 * funcionalidades e critérios do trabalho.
 *
 * Observação: o método main() NÃO conta como o "método estático" do critério (g);
 * por isso o contador de empréstimos vive em {@link Emprestimo}.
 */
public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca("Biblioteca Central da UFC");

        // ---------------------------------------------------------------
        // 1) Montagem do acervo — subclasses usadas de forma polimórfica.
        //    A variável é do tipo da superclasse (ItemAcervo), mas o objeto
        //    real é Livro / Revista / DVD.
        // ---------------------------------------------------------------
        ItemAcervo livro   = new Livro("L001", "O Cortiço", "Aluísio Azevedo", 1890, 304, "978-8501012340");
        ItemAcervo revista = new Revista("R001", "Superinteressante", "Editora Abril", 2024, 458);
        ItemAcervo dvd     = new DVD("D001", "Cidade de Deus", "Fernando Meirelles", 2002, 130);

        biblioteca.adicionarItem(livro);
        biblioteca.adicionarItem(revista);
        biblioteca.adicionarItem(dvd);

        // ---------------------------------------------------------------
        // 2) Cadastro de usuário (composição: o usuário cria seu Endereco).
        // ---------------------------------------------------------------
        Usuario leanderson = new Usuario("Leanderson Nunes da Rocha", "545589",
                "Av. da Universidade, 2853", "Fortaleza", "60020-181");
        biblioteca.cadastrarUsuario(leanderson);

        System.out.println("Usuário cadastrado: " + leanderson);
        System.out.println();

        // ---------------------------------------------------------------
        // 3) Relatório do acervo (dependência + método estático + polimorfismo).
        // ---------------------------------------------------------------
        System.out.println(GeradorRelatorio.gerarRelatorioAcervo(biblioteca));
        System.out.println();

        // ---------------------------------------------------------------
        // 4) Empréstimos (composição + associações simples).
        // ---------------------------------------------------------------
        LocalDate hoje = LocalDate.of(2026, 6, 1);
        Emprestimo empLivro = biblioteca.emprestar(leanderson, livro, hoje);
        Emprestimo empDvd   = biblioteca.emprestar(leanderson, dvd, hoje);

        System.out.println(GeradorRelatorio.gerarRelatorioEmprestimos(biblioteca));
        System.out.println();

        // ---------------------------------------------------------------
        // 5) Devoluções — multa calculada POLIMORFICAMENTE por tipo de item.
        // ---------------------------------------------------------------
        double multaLivro = biblioteca.devolver(empLivro, hoje.plusDays(20)); // 6 dias de atraso (prazo 14)
        double multaDvd   = biblioteca.devolver(empDvd, hoje.plusDays(2));    // dentro do prazo (prazo 3)

        System.out.printf("Devolução do livro com atraso -> multa: R$ %.2f%n", multaLivro);
        System.out.printf("Devolução do DVD no prazo     -> multa: R$ %.2f%n", multaDvd);
        System.out.println();

        // ---------------------------------------------------------------
        // 6) Atributo/método estático: total acumulado de empréstimos.
        // ---------------------------------------------------------------
        System.out.println("Total de empréstimos já registrados no sistema: "
                + Emprestimo.getTotalEmprestimos());
    }
}
