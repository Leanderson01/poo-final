package biblioteca;

/**
 * Livro do acervo.
 *
 * Critério (b): HERANÇA GENUÍNA nº 1 — estende {@link ItemAcervo}.
 * Especializa o item com nº de páginas e ISBN, e define suas próprias
 * regras de prazo e multa.
 */
public class Livro extends ItemAcervo {

    private final int numeroPaginas;
    private final String isbn;

    public Livro(String codigo, String titulo, String autor, int ano,
                 int numeroPaginas, String isbn) {
        super(codigo, titulo, autor, ano); // reúso do construtor da superclasse
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
    }

    @Override
    public String getDescricao() {
        return String.format("Livro:   \"%s\", por %s (%d) — %d págs, ISBN %s",
                titulo, autor, ano, numeroPaginas, isbn);
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 14; // livros podem ficar 14 dias
    }

    @Override
    public double calcularMulta(long diasAtraso) {
        return diasAtraso <= 0 ? 0.0 : diasAtraso * 1.50; // R$ 1,50 por dia
    }
}
