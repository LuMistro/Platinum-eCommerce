package bean;

import dao.VendaDao;
import interfaces.IBaseDao;
import model.ItemVenda;
import model.Venda;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class VendaMB implements Serializable {

    private Venda venda;
    private ItemVenda itemVenda;
    private IBaseDao<Venda> vendaDao;


    private List<Venda> vendas;
    private List<ItemVenda> itemVendas;

    @PostConstruct
    private void init() {
        inicializaObjetos();
    }


    public void limpar() {
        venda = new Venda();
    }

    public void atualizar() {
        vendas = vendaDao.buscarTodos();
    }

    private void inicializaObjetos() {
        venda = new Venda();
        itemVenda = new ItemVenda();
        vendaDao = new VendaDao();
        vendas = new ArrayList<>();
    }

}
