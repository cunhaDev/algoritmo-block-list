public class Produto {

    private String titulo;
    private String descricao;
    private String marca;
    private String ean;
    private String sellerId;

    public Produto(String titulo, String descricao, String marca, String ean, String sellerId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.marca = marca;
        this.ean = ean;
        this.sellerId = sellerId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public String getEan() {
        return ean;
    }

    public String getSellerId() {
        return sellerId;
    }
}
