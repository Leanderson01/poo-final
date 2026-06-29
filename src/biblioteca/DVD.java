package biblioteca;

/**
 * DVD / mídia audiovisual do acervo.
 *
 * Critério (b): HERANÇA GENUÍNA nº 3 — estende {@link ItemAcervo}.
 * É o item mais "caro" de manter parado: prazo mínimo e multa mais alta.
 */
public class DVD extends ItemAcervo {

    private final int duracaoMinutos;

    public DVD(String codigo, String titulo, String diretor, int ano, int duracaoMinutos) {
        super(codigo, titulo, diretor, ano);
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String getDescricao() {
        return String.format("DVD:     \"%s\", dir. %s (%d) — %d min",
                titulo, autor, ano, duracaoMinutos);
    }

    @Override
    public int getPrazoEmprestimoDias() {
        return 3; // DVDs só podem ficar 3 dias
    }

    @Override
    public double calcularMulta(long diasAtraso) {
        return diasAtraso <= 0 ? 0.0 : diasAtraso * 3.00; // R$ 3,00 por dia
    }
}
