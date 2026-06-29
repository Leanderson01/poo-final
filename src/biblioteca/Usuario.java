package biblioteca;

/**
 * Usuário cadastrado na biblioteca.
 *
 * Critério (c) COMPOSIÇÃO: o usuário cria e é dono do seu próprio
 * {@link Endereco} (instanciado aqui dentro, no construtor). O endereço não
 * existe sem o usuário — ciclos de vida acoplados.
 */
public class Usuario {

    private final String nome;
    private final String matricula;
    private final Endereco endereco; // composição (parte pertence ao todo)

    public Usuario(String nome, String matricula, String rua, String cidade, String cep) {
        this.nome = nome;
        this.matricula = matricula;
        // O Endereco nasce AQUI: composição.
        this.endereco = new Endereco(rua, cidade, cep);
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return nome + " (mat. " + matricula + ") — " + endereco.formatar();
    }
}
