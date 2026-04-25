public class Produto {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private boolean disponivel;

    public Produto(String nome, double preco, int quantidade) {
        this.id = contadorId++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // SETTERS
    public void setNome(String nome) { 
        this.nome = nome; 
    }
    public void setPreco(double preco) { 
        this.preco = preco; 
    }
    public void setQuantidade(int quantidade) { 
        this.quantidade = quantidade; 
    }
    public void setDisponivel(boolean disponivel) { 
        this.disponivel = disponivel; 
    }

    // GETTERS
    public String getNome() { 
        return nome; 
    }
    public double getPreco() { 
        return preco; 
    }
    public int getId() { 
        return id; 
    }
    public int getQuantidade() { 
        return quantidade; 
    }

    public boolean isDisponivel() { 
        this.disponivel = (this.quantidade > 0);
        return this.disponivel; 
    }
    
    @Override
    public String toString() {
        String statusDisponivel = isDisponivel() ? "SIM" : "NÃO";
        
        return String.format("ID: %d | Nome: %-15s | Preço: R$%.2f | Estoque: %d | Disponível: %s", id, nome, preco, quantidade, statusDisponivel);
    }
}