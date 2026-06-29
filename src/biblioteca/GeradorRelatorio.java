package biblioteca;

/**
 * Utilitário que gera relatórios em texto sobre a biblioteca.
 *
 * Critério (c) DEPENDÊNCIA: usa {@link Biblioteca} apenas como PARÂMETRO de
 * método — não guarda nenhuma referência a ela como atributo. É o vínculo mais
 * fraco entre classes (relação de uso temporário).
 *
 * Critério (g): também demonstra um método estático auxiliar.
 */
public class GeradorRelatorio {

    /**
     * Gera o relatório do acervo. Depende de uma Biblioteca recebida por parâmetro.
     * Percorre os itens chamando seus métodos de forma POLIMÓRFICA.
     */
    public static String gerarRelatorioAcervo(Biblioteca biblioteca) {
        StringBuilder sb = new StringBuilder();
        sb.append("=========== ACERVO: ").append(biblioteca.getNome()).append(" ===========\n");
        for (ItemAcervo item : biblioteca.getAcervo()) {
            // item.toString() -> getDescricao(): resolvido em tempo de execução (polimorfismo)
            sb.append("  ").append(item).append("\n");
            sb.append("      prazo de empréstimo: ")
              .append(item.getPrazoEmprestimoDias()) // polimórfico
              .append(" dias\n");
        }
        sb.append("Total de itens no acervo: ").append(biblioteca.getAcervo().size());
        return sb.toString();
    }

    /** Relatório dos empréstimos já realizados. */
    public static String gerarRelatorioEmprestimos(Biblioteca biblioteca) {
        StringBuilder sb = new StringBuilder();
        sb.append("======== EMPRÉSTIMOS REALIZADOS ========\n");
        for (Emprestimo emp : biblioteca.getEmprestimos()) {
            sb.append("  ").append(emp)
              .append(" | devolver até ").append(emp.getPrevisaoDevolucao()).append("\n");
        }
        sb.append("Total acumulado de empréstimos: ").append(Emprestimo.getTotalEmprestimos());
        return sb.toString();
    }
}
