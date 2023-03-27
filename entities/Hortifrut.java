package exerciciosProprios.usandoArray.hortifrut.entities;

public class Hortifrut {
    
    // Atributos
    private String nomeProduto;
    private Integer codigo;
    private int quantityInStock;
    private Double valor;

    // Construtor
    public Hortifrut(String nomeProduto, Integer codigo, Double valor){
        this.nomeProduto = nomeProduto;
        this.codigo = codigo; 
        this.valor = valor;
    }

    // Sobrecarga de construtor
    public Hortifrut(String nomeProduto, Integer codigo, int quantityInStock, Double valor) {
        this.nomeProduto = nomeProduto;
        this.codigo = codigo;
        this.quantityInStock = quantityInStock;
        this.valor = valor;
    }

    // Encapsulamento
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    // Métodos
    public Integer adicionarInStock(int quantity){
        return quantityInStock += quantity;
    }

    public Integer retirarDoStock(int quantity){
        return quantityInStock -= quantity;
    }

    public Double precoTotalProduto(){
        return quantityInStock * valor;
    }

    // ToString
    public String toString(){
        return "\nCodigo: "+codigo+"\nNome: "+nomeProduto+"\nValor Unidade: R$"+String.format("%.2f", valor)+
        "\nQuantidade no estoque: "+quantityInStock+" Kg\nValor total de "+nomeProduto+"s no seu estoque: R$"
        +String.format("%.2f", precoTotalProduto())+"\n";
    }
}
