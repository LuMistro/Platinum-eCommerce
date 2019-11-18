package bean;

import dao.ClienteDao;
import interfaces.IBaseDao;
import model.Cliente;
import model.enums.Cidades;
import model.enums.Estados;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ClienteMB implements Serializable {

    private Cliente cliente;
    private IBaseDao<Cliente> clienteDao;
    private List<Cliente> clientes;

    @PostConstruct
    private void init() {
        inicializaObjetos();
        atualizar();
    }

    public void salvar() {
        clienteDao.salvar(cliente);
        limpar();
        atualizar();
    }

    public void limpar() {
        cliente = new Cliente();
    }

    public void atualizar() {
        clientes = clienteDao.buscarTodos();
    }

    private void inicializaObjetos() {
        cliente = new Cliente();
        clienteDao = new ClienteDao();
        clientes = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
