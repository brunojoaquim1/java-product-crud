package service;


public class Validador {
    private boolean retorno ;
    private String mensagem;

    public boolean isRetorno() {
        return retorno;
    }

    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Validador(boolean retorno, String mensagem) {
        this.retorno = retorno;
        this.mensagem = mensagem;
    }
    
    
    
        
}
