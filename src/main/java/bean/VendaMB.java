package bean;

import dao.ClienteDao;
import dao.EnderecoDao;
import dao.ProdutoDao;
import dao.VendaDao;
import interfaces.IBaseDao;
import model.*;
import model.enums.FormaPagamento;
import org.primefaces.event.FlowEvent;
import util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.EnumSet;
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
    private IBaseDao<Endereco> enderecoDao;
    List<FormaPagamento> formaPagamentos;

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
        venda.getCliente().setEndereco(new Endereco());
        venda.setItems(new ArrayList<ItemVenda>());
        produto = new Produto();
        itemVenda = new ItemVenda();
        vendaDao = new VendaDao();
        clienteDao = new ClienteDao();
        produtoDao = new ProdutoDao();
        enderecoDao = new EnderecoDao();
        vendas = new ArrayList<>();
        produtos = new ArrayList<>();
        itemVendas = new ArrayList<>();
        formaPagamentos = new ArrayList<FormaPagamento>(EnumSet.allOf(FormaPagamento.class));

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

    public void removerItem() {
        itemVendas.remove(itemVenda);
    }


    public void efetivarVenda() {
        if (venda.getItems().isEmpty()) {
            Mensagem.addMensagemWarn("vendaSemItens");
            return;
        }

        if (venda.getCliente() == null) {
            Mensagem.addMensagemWarn("vendaSemCliente");
            return;
        }

        if (venda.getFormaPagamento() == null) {
            Mensagem.addMensagemWarn("vendaSemFormaPagamento");
        }

        vendaDao.salvar(venda);

    }


    public String onFlowProcess(FlowEvent event) {
        if (!itemVendas.isEmpty()) {
            venda.setItems(itemVendas);
        }


        if (venda.getValorTotal() == null) {
            venda.setValorTotal(0.0);
        }

        if (venda.getValorDesconto() == null) {
            venda.setValorDesconto(0.0);
        }

        if (venda.getValorTotal() == 0.0) {
            if (!venda.getItems().isEmpty()) {
                List<ItemVenda> itemsDaVenda = venda.getItems();
                for (int i = 0; i < itemsDaVenda.size(); i++) {
                    ItemVenda umItemDaVenda = itemsDaVenda.get(i);
                    venda.setValorTotal(venda.getValorTotal() + (umItemDaVenda.getProduto().getPreco() * umItemDaVenda.getQuantidade()));
                }
            }
        } else {
            venda.setValorTotal(venda.getValorTotal() - venda.getValorDesconto());
        }


        if (venda.getCliente().getEndereco().getCep() != null) {
            enderecoDao.salvar(venda.getCliente().getEndereco());
        }

        if (venda.getCliente().getCpf() != null) {
            clienteDao.salvar(venda.getCliente());
        }

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

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }
}
