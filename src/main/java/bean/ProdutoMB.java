package bean;

import dao.ProdutoDao;
import interfaces.IBaseDao;
import model.Produto;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
public class ProdutoMB implements Serializable {

    private Produto produto;
    private IBaseDao<Produto> produtoDao;
    private List<Produto> produtos;
    private String[] fotos;

    @PostConstruct
    private void init() {
        inicializaObjetos();
        atualizar();
    }


    public void limpar() {
        produto = new Produto();
    }


    public void salvar() {
        produtoDao.salvar(produto);
        atualizar();
        limpar();

    }

    public void atualizar() {
        produtos = produtoDao.buscarTodos();
    }

    private void inicializaObjetos() {
        produto = new Produto();
        produtoDao = new ProdutoDao();
        produtos = new ArrayList<>();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }

    public List<String> getFotos() {
        return Arrays.asList(fotos);
    }
}
