package biblioteca;

/**
 * Interface que abstrai o comportamento de qualquer coisa que possa ser emprestada.
 *
 * Critério (d): uso genuíno de INTERFACE para abstrair uma funcionalidade.
 * Quem implementa esta interface se compromete a saber dizer seu prazo de
 * empréstimo e a calcular a própria multa por atraso.
 */
public interface Emprestavel {

    /** Prazo de empréstimo, em dias, específico de cada tipo de item. */
    int getPrazoEmprestimoDias();

    /**
     * Calcula a multa (em reais) de acordo com a regra de cada item.
     *
     * @param diasAtraso quantidade de dias de atraso (0 ou negativo = sem multa)
     * @return valor da multa em reais
     */
    double calcularMulta(long diasAtraso);
}
