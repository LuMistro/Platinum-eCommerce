package bean;

import com.lowagie.text.pdf.AcroFields;
import dao.ClienteDao;
import dao.ProdutoDao;
import dao.VendaDao;
import interfaces.IBaseDao;
import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class VendaMB implements Serializable {

    private Venda venda;
    private Produto produto;
    private Integer quantidade;
    private ItemVenda itemVenda;
    private IBaseDao<Venda> vendaDao;


    private List<Venda> vendas;
    private List<ItemVenda> itemVendas;

    private List<Produto> produtos;
    private IBaseDao<Produto> produtoDao;

    private IBaseDao<Cliente> clienteDao;
    private List<Cliente> clientes;
    private List<Cliente> clientesFiltrados;

    @PostConstruct
    private void init() {
        inicializaObjetos();
    }


    public void limpar() {
        venda = new Venda();
        venda.setCliente(new Cliente());
        produto = new Produto();
        itemVenda = new ItemVenda();
    }

    public void atualizar() {
        vendas = vendaDao.buscarTodos();
        produtos = produtoDao.buscarTodos();
    }

    private void inicializaObjetos() {
        venda = new Venda();
        venda.setCliente(new Cliente());
        produto = new Produto();
        itemVenda = new ItemVenda();
        vendaDao = new VendaDao();
        clienteDao = new ClienteDao();
        produtoDao = new ProdutoDao();
        vendas = new ArrayList<>();
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        itemVendas = new ArrayList<>();

        clientes = clienteDao.buscarTodos();
        produtos = produtoDao.buscarTodos();
    }

    public void adicionarItem() {
        itemVenda = new ItemVenda();
        itemVenda.setProduto(produto);
        itemVenda.setQuantidade(quantidade);
        itemVenda.setVenda(venda);
        itemVendas.add(itemVenda);
        itemVenda = new ItemVenda();
    }


    public void efetivarVenda() {

    }


    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }


    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
        this.clientesFiltrados = clientesFiltrados;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


}
