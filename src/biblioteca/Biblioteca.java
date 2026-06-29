package biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Fachada do sistema: orquestra acervo, usuários e empréstimos.
 *
 * Critério (c) AGREGAÇÃO: mantém listas de {@link ItemAcervo} e {@link Usuario}
 * que são criados FORA dela e apenas cadastrados — eles vivem de forma
 * independente da biblioteca.
 *
 * Critério (c) COMPOSIÇÃO: cria e é dona dos objetos {@link Emprestimo}. Um
 * empréstimo só nasce dentro de uma biblioteca e não faz sentido fora dela.
 */
public class Biblioteca {

    private final String nome;
    private final List<ItemAcervo> acervo = new ArrayList<>();      // agregação
    private final List<Usuario> usuarios = new ArrayList<>();       // agregação
    private final List<Emprestimo> emprestimos = new ArrayList<>(); // composição

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    /** Agregação: o item vem pronto de fora e é apenas referenciado. */
    public void adicionarItem(ItemAcervo item) {
        acervo.add(item);
    }

    /** Agregação: o usuário vem pronto de fora e é apenas referenciado. */
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Realiza um empréstimo: cria o registro (composição) e marca o item como
     * indisponível usando o método protected da superclasse (mesmo pacote).
     */
    public Emprestimo emprestar(Usuario usuario, ItemAcervo item, LocalDate data) {
        if (!item.isDisponivel()) {
            throw new IllegalStateException("Item indisponível: " + item.getTitulo());
        }
        item.setDisponivel(false);                            // acesso protected (mesmo pacote)
        Emprestimo emp = new Emprestimo(usuario, item, data); // biblioteca CRIA -> composição
        emprestimos.add(emp);
        return emp;
    }

    /** Registra a devolução, libera o item e retorna a multa cobrada. */
    public double devolver(Emprestimo emp, LocalDate data) {
        double multa = emp.registrarDevolucao(data);
        emp.getItem().setDisponivel(true);
        return multa;
    }

    public String getNome() {
        return nome;
    }

    public List<ItemAcervo> getAcervo() {
        return acervo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
