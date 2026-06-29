package biblioteca;

/**
 * Revista / periódico do acervo.
 *
 * Critério (b): HERANÇA GENUÍNA nº 2 — estende {@link ItemAcervo}.
 * Por ter giro rápido, possui prazo curto e multa mais alta que o livro.
 */
public class Revista extends ItemAcervo {

    private final int edicao;

    public Revista(String codigo, String titulo, String autor, int ano, int edicao) {
        super(codigo, titulo, autor, ano);
        this.edicao = edicao;
    }

    @Override
    public String getDescricao() {
        return String.format("Revista: \"%s\" — edição nº %d (%d)", titulo, edicao, ano);
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 7; // revistas só podem ficar 7 dias
    }

    @Override
    public double calcularMulta(long diasAtraso) {
        return diasAtraso <= 0 ? 0.0 : diasAtraso * 2.00; // R$ 2,00 por dia
    }
}
