public class Conta {
    private static int contadorId = 1;
    private String nome;
    private String senha;
    private String email;
    private int id;
    private boolean admin;

    public Conta(String nome, String email, String senha, boolean admin) {
        this.id = contadorId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
    }

    // --- SETTERS ---
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public void setSenha(String senha) { 
        this.senha = senha; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public void setAdmin(boolean admin) { 
        this.admin = admin; 
    }

    // --- GETTERS ---
    public String getNome() { 
        return nome;
    }

    public String getSenha() { 
        return senha;
    }

    public String getEmail() { return email; 

    }

    public boolean getAdmin() { 
        return admin; 
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %-15s | Email: %s | Admin: %b", id, nome, email, admin);
    }
}