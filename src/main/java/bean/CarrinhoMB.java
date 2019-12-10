package bean;

import model.Produto;
import util.Mensagem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CarrinhoMB implements Serializable {

    public static List<Produto> produtosDoCarrinho;

    public CarrinhoMB() {
        produtosDoCarrinho = new ArrayList<>();
    }

    public void inserirProdutosNoCarrinho(List<Produto> produtos) {
        this.produtosDoCarrinho = produtos;
        Mensagem.addMensagemInfo("adicionadoNoCarrinhoComSucesso");
    }

    public void inserirProdutosNoCarrinho(Produto produto) {
        this.produtosDoCarrinho.add(produto);
        Mensagem.addMensagemInfo("adicionadoNoCarrinhoComSucesso");
    }

    public void removerProdutoDoCarrinho(Produto produto) {
        this.produtosDoCarrinho.remove(produto);
    }

    public List<Produto> getProdutosDoCarrinho() {
        return produtosDoCarrinho;
    }

    public void setProdutosDoCarrinho(List<Produto> produtosDoCarrinho) {
        this.produtosDoCarrinho = produtosDoCarrinho;
    }
}
