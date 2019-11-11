package model.enums;


public enum FormaPagamento {

    BOLETO("Boleto"),
    CARTAO_CREDITO("Cartão de Credito");


    private String nome;

    FormaPagamento(String nome) {
        this.nome = nome;
    }
}
