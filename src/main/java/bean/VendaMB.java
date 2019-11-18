package bean;

import dao.ClienteDao;
import dao.VendaDao;
import interfaces.IBaseDao;
import model.Cliente;
import model.ItemVenda;
import model.Venda;
import model.enums.FormaPagamento;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class VendaMB implements Serializable {

    private Venda venda;
    private ItemVenda itemVenda;
    private IBaseDao<Venda> vendaDao;

    private List<Venda> vendas;
    private List<ItemVenda> itemVendas;

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
    }

    public void atualizar() {
        vendas = vendaDao.buscarTodos();
    }

    private void inicializaObjetos() {
        venda = new Venda();
        venda.setCliente(new Cliente());
        itemVenda = new ItemVenda();
        vendaDao = new VendaDao();
        clienteDao = new ClienteDao();
        vendas = new ArrayList<>();
        clientes = new ArrayList<>();

        clientes = clienteDao.buscarTodos();
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
}
