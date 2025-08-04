/*-- Autor....: Bruno Joaquim
 *-- Data.....: 31/07/2025
 *-- Objetivo.: Serviço correspondente as rotinas de produto 
*/
package service;

import DAO.ProdutoDAO;
import DAO.RetornoDAO;
import DAO.RetornoListaDAO;
import service.Validador;
import service.ProdutoValidador;

public class ProdutoService {
    private final ProdutoDAO dao = new ProdutoDAO();
    private final ProdutoValidador produtoValidador = new ProdutoValidador();
    
    public void adicionar(String nome, double preco, int quantidade){
        Validador validacao = ProdutoValidador.validar(nome, preco, quantidade);

        if (!validacao.isRetorno()) {
            System.out.println(validacao.getMensagem());
            return;
        }

        RetornoDAO retornoDao = dao.insert(nome, preco, quantidade);
        System.out.println(retornoDao.getMensagem());
    }

    
    public void listar(){
        RetornoListaDAO RetornoListaDao = dao.listAll();
        
        if (RetornoListaDao.getDados().isEmpty()){
          System.out.println("Lista Vazia");  
        }
        else {
           RetornoListaDao.getDados().forEach(System.out::println);
        }
    }
    
    public void atualizar(int id, String nome, double preco, int novaQuantidade){
        Validador validacao = ProdutoValidador.validar(nome, preco, novaQuantidade);

        if (!validacao.isRetorno()) {
            System.out.println(validacao.getMensagem());
            return;
        }
        
        RetornoDAO RetornoDao = dao.update(id, nome, preco, novaQuantidade);
        System.out.println(RetornoDao.getMensagem());

    }

    public void remover(int id) {
        RetornoDAO RetornoDao = dao.delete(id);
        System.out.println(RetornoDao.getMensagem());

    }
}
