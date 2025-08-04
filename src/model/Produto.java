/*-- Autor....: Bruno Joaquim
 *-- Data.....: 31/07/2025
 *-- Objetivo.: Modelo da classe de produtos 
*/
package model;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    
    public Produto(int id, String nome, double preco, int quantidade){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade ;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    @Override
    public String toString(){
        return "ID: " + id + ", Nome: " + nome + ", Preco R$: " + preco + "Qtd:" + quantidade;
    }
}



