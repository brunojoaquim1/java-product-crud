package DAO; 

import java.util.List;

public class RetornoListaDAO<T> {
    private boolean sucesso;
    private String mensagem;
    private List<T> dados;

    public RetornoListaDAO(boolean sucesso, String mensagem, List<T> dados) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.dados = dados;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<T> getDados() {
        return dados;
    }
}
