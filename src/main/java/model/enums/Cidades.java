package model.enums;

public enum Cidades {
    PH("Palhoça"),
    SJ("São José"),
    NY("Nova York"),
    LV("Las Vegas");

    private String descricao;

    Cidades(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
