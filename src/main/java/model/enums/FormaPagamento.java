package model.enums;


public enum FormaPagamento {

    CARTAO_DEBITO("Cartão de Débito"),
    CARTAO_CREDITO("Cartão de Crédito");


    private String nome;

    public String getNome() {
        return nome;
    }

    FormaPagamento(String nome) {
        this.nome = nome;
    }
}
