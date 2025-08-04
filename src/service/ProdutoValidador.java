package service;
import service.Validador;

public class ProdutoValidador {

    public static Validador validar(String nome, double preco, int quantidade) {
        if (nome == null || nome.isBlank()) {
            return new Validador(false, "Nome do produto não pode ser vazio.");
        }

        if (preco <= 0) {
            return new Validador(false, "Preço deve ser maior que zero.");
        }

        if (quantidade <= 0) {
            return new Validador(false, "Quantidade deve ser maior que zero.");
        }

        return new Validador(true, "Produto válido.");
    }
}
