package biblioteca;

/**
 * Endereço de um usuário.
 *
 * Faz parte de uma COMPOSIÇÃO com {@link Usuario}: o endereço é criado dentro
 * do construtor do usuário e não tem sentido nem ciclo de vida próprios fora
 * dele. Se o usuário deixa de existir, seu endereço também deixa.
 */
public class Endereco {

    private final String rua;
    private final String cidade;
    private final String cep;

    public Endereco(String rua, String cidade, String cep) {
        this.rua = rua;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String formatar() {
        return rua + ", " + cidade + " - CEP " + cep;
    }
}
