package model;

import interfaces.IBaseModel;
import model.enums.FormaPagamento;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Venda implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> items;
    @ManyToOne
    private Cliente cliente;
    @Enumerated
    FormaPagamento formaPagamento;


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemVenda> getItems() {
        return items;
    }

    public void setItems(List<ItemVenda> items) {
        this.items = items;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
