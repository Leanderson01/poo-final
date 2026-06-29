package biblioteca;

/**
 * Classe ABSTRATA que generaliza qualquer item do acervo da biblioteca.
 *
 * Critério (d): uso genuíno de CLASSE ABSTRATA para abstrair uma funcionalidade.
 * Centraliza os atributos e comportamentos comuns a todos os itens (título,
 * autor, ano, disponibilidade) e obriga cada subclasse a se descrever e a
 * definir suas próprias regras de empréstimo/multa.
 *
 * Implementa {@link Emprestavel}, mas deixa os métodos da interface para as
 * subclasses concretas — por isso a classe é abstrata.
 */
public abstract class ItemAcervo implements Emprestavel {

    // Critério (f) 'protected': visível para as subclasses Livro, Revista e DVD,
    // que usam estes campos ao montar sua descrição.
    protected String titulo;
    protected String autor;
    protected int ano;

    // Critério (f) 'private': totalmente encapsulado nesta classe.
    private final String codigo;
    private boolean disponivel;

    public ItemAcervo(String codigo, String titulo, String autor, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
    }

    /**
     * Método ABSTRATO: cada subclasse descreve a si mesma de uma forma diferente.
     * É o ponto central do polimorfismo do projeto.
     */
    public abstract String getDescricao();

    public boolean isDisponivel() {
        return disponivel;
    }

    // 'protected': só a própria biblioteca (mesmo pacote) e subclasses alteram o estado.
    protected void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        // Chama getDescricao() de forma polimórfica (resolvida em tempo de execução).
        return getDescricao() + (disponivel ? "  [DISPONÍVEL]" : "  [EMPRESTADO]");
    }
}
