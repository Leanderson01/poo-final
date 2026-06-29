package biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Registro de um empréstimo: liga um {@link Usuario} a um {@link ItemAcervo}.
 *
 * Critério (c) ASSOCIAÇÃO SIMPLES: guarda referências para um Usuario e um
 * ItemAcervo, que existem de forma independente do empréstimo.
 *
 * Critério (g) ESTÁTICO: usa um atributo estático (contador global) e um
 * método estático para informar quantos empréstimos já foram criados.
 */
public class Emprestimo {

    // Critério (g) ATRIBUTO ESTÁTICO: compartilhado por TODAS as instâncias.
    private static int totalEmprestimos = 0;

    private final int id;
    private final Usuario usuario;   // associação simples
    private final ItemAcervo item;   // associação simples
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, ItemAcervo item, LocalDate dataEmprestimo) {
        totalEmprestimos++;          // toda nova instância incrementa o contador
        this.id = totalEmprestimos;  // e recebe um id único a partir dele
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * Critério (g) MÉTODO ESTÁTICO (não é o main): total de empréstimos já criados.
     */
    public static int getTotalEmprestimos() {
        return totalEmprestimos;
    }

    /** Data limite de devolução = data do empréstimo + prazo do item (polimórfico). */
    public LocalDate getPrevisaoDevolucao() {
        return dataEmprestimo.plusDays(item.getPrazoEmprestimoDias());
    }

    /**
     * Registra a devolução e devolve o valor da multa.
     * A multa é calculada de forma POLIMÓRFICA: cada tipo de item tem sua regra.
     */
    public double registrarDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        long atraso = ChronoUnit.DAYS.between(getPrevisaoDevolucao(), dataDevolucao);
        return item.calcularMulta(atraso); // chamada polimórfica
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ItemAcervo getItem() {
        return item;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Empréstimo #" + id + " | " + usuario.getNome() + " -> " + item.getTitulo();
    }
}
