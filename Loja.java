import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loja {
    private static List<Conta> listaContas = new ArrayList<>();
    private static List<Produto> listaProdutos = new ArrayList<>();
    private static Conta usuarioLogado = null;

    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in);
        Scanner nmr = new Scanner(System.in);
        Scanner doub = new Scanner(System.in);

        int option = -1;

        // Dados iniciais
        listaContas.add(new Conta("Admin", "adminloja@gmail.com", "123@bC", true));
        listaContas.add(new Conta("Miguel", "miguelito@gmail.com", "123@bC", false));
        listaProdutos.add(new Produto("Notebook", 2049.90, 2));
        listaProdutos.add(new Produto("Memória RAM 8 GB", 449.99, 0));

        while (option != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Cadastre sua Conta");
            System.out.println("2 - Faça Login");
            System.out.println("3 - Visualizar Produtos/Comprar");
            System.out.println("4 - Visualizar meu Perfil");

            if (isAdmin()) {
                menuAdmin();
            }

            System.out.println("0 - Sair da Aplicação");
            System.out.print(">> ");
            option = nmr.nextInt();
            nmr.nextLine();

            switch (option) {
                case 1:
                    cadastrarConta(txt);
                    break;
                case 2:
                    fazerLogin(txt);
                    break;
                case 3:
                    listarProdutos(txt, nmr);
                    break;
                case 4:
                    visualizarPerfil(txt, nmr);
                    break;
                case 5:
                    if (isAdmin()) cadastrarProduto(txt, doub, nmr);
                    else System.out.println("Opção Inválida!");
                    break;
                case 6:
                    if (isAdmin()) listarProdutosAdmin(txt, nmr);
                    else System.out.println("Opção Inválida!");
                    break;
                case 7:
                    if (isAdmin()) listarUsuarios(nmr);
                    else System.out.println("Opção Inválida!");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void cadastrarConta(Scanner txt) {
        while (true) {
            System.out.println("\n--- NOVO CADASTRO (Digite 0 no nome para cancelar) ---");
            System.out.print("Nome: ");
            String nome = txt.nextLine();
            if (nome.equals("0")) return;
            
            if (nome.matches(".*\\d.*")) {
                System.out.println("Erro: O nome não pode conter números!");
                continue;
            }

            System.out.print("Email: ");
            String email = txt.nextLine();
            if (!verificaEmail(email)) continue;

            System.out.print("Senha: ");
            String senha = txt.nextLine();
            if (!verificaSenha(senha)) continue;

            listaContas.add(new Conta(nome, email, senha, false));
            System.out.println("Conta cadastrada com sucesso!");
            return;
        }
    }

    public static void fazerLogin(Scanner txt) {
        int tentativas = 0;
        do {
            System.out.println("\n--- LOGIN (Tentativa " + (tentativas + 1) + " de 5) ---");
            System.out.print("Email: ");
            String email = txt.nextLine();
            System.out.print("Senha: ");
            String senha = txt.nextLine();

            for (Conta conta : listaContas) {
                if (conta.getEmail().equalsIgnoreCase(email) && conta.getSenha().equals(senha)) {
                    usuarioLogado = conta;
                    System.out.println("\nBem-vindo de volta, " + conta.getNome() + "!");
                    return;
                }
            }
            tentativas++;
            System.out.println("Erro: Email ou senha incorretos.");
        } while (tentativas < 5);
        System.out.println("\nAcesso bloqueado: Limite de tentativas excedido!");
    }

    public static boolean verificaEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Erro: Formato de email inválido!");
            return false;
        }
        for (Conta conta : listaContas) {
            if (conta.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Erro: Email já cadastrado!");
                return false;
            }
        }
        return true;
    }

    public static boolean verificaSenha(String senha) {
        if (senha.length() < 6 || senha.length() > 20) {
            System.out.println("Erro: A senha deve ter entre 6 e 20 caracteres!");
            return false;
        }
        if (!senha.matches(".*\\d.*") || !senha.matches(".*[a-z].*") || !senha.matches(".*[A-Z].*") || !senha.matches(".*[!@#$%^&*()].*")) {
            System.out.println("Erro: Senha fraca! Use Números, Letras (A/a) e Caracteres Especiais.");
            return false;
        }
        return true;
    }

    public static void cadastrarProduto(Scanner txt, Scanner doub, Scanner nmr) {
        System.out.print("\nNome do Produto: ");
        String nome = txt.nextLine();
        System.out.print("Preço: ");
        double preco = doub.nextDouble();
        doub.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = nmr.nextInt();
        nmr.nextLine();
        
        listaProdutos.add(new Produto(nome, preco, quantidade));
        System.out.println("Produto cadastrado!");
    }

    public static void listarProdutos(Scanner txt, Scanner nmr) {
        int idEscolhido = -1;
        while (idEscolhido != 0) {
            System.out.println("\n--- LISTA DE PRODUTOS ---");
            if (listaProdutos.isEmpty()) {
                System.out.println("Nenhum produto em estoque.");
                return;
            }
            listaProdutos.forEach(System.out::println);

            System.out.print("\nDigite o ID do produto para comprar (ou 0 para voltar ao Menu Principal): ");
            idEscolhido = nmr.nextInt();
            nmr.nextLine();

            if (idEscolhido == 0) break;

            boolean encontrado = false;
            for (Produto produto : listaProdutos) {
                if (produto.getId() == idEscolhido) {
                    encontrado = true;
                    System.out.print("Confirmar compra de " + produto.getNome() + "? [s/n]: ");
                    String resposta = txt.nextLine();
                    
                    if (resposta.equalsIgnoreCase("s")) {
                        if (usuarioLogado == null) {
                            System.out.println("Erro: Faça login para comprar.");
                        } else if (produto.isDisponivel()) {
                            produto.setQuantidade(produto.getQuantidade() - 1);
                            System.out.println("Compra realizada com sucesso!");
                        } else {
                            System.out.println("Produto esgotado!");
                        }
                    } else {
                        System.out.println("Compra cancelada. Voltando para a lista...");
                    }
                }
            }
            if (!encontrado) System.out.println("ID não encontrado!");
        }
    }

    public static void visualizarPerfil(Scanner txt, Scanner nmr) {
        int optionPerfil = -1;
        while (optionPerfil != 0 && usuarioLogado != null) {
            System.out.println("\n--- MEU PERFIL ---");
            System.out.println(usuarioLogado);
            System.out.println("1-Editar Perfil | 2-Deletar Perfil | 3-Logout | 0-Voltar ao Menu Principal");
            System.out.print(">> ");
            optionPerfil = nmr.nextInt();
            nmr.nextLine();

            switch (optionPerfil) {
                case 1:
                    editarPerfil(txt);
                    break;
                case 2:
                    deletarPerfil(txt);
                    if (usuarioLogado == null) return;
                    break;
                case 3:
                    usuarioLogado = null;
                    System.out.println("Logout efetuado.");
                    return;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        if (usuarioLogado == null && optionPerfil != 0) System.out.println("Você não está logado!");
    }

    public static void editarPerfil(Scanner txt) {
        System.out.print("Novo nome (Enter para manter): ");
        String novoNome = txt.nextLine();
        if (!novoNome.isEmpty()) usuarioLogado.setNome(novoNome);

        System.out.print("Novo email: ");
        String novoEmail = txt.nextLine();
        if (!novoEmail.isEmpty() && verificaEmail(novoEmail)) usuarioLogado.setEmail(novoEmail);

        System.out.println("Perfil atualizado!");
    }

    public static void deletarPerfil(Scanner txt) {
        System.out.print("Confirme sua senha para DELETAR (ou 0 para cancelar): ");
        String confirmacao = txt.nextLine();
        if (confirmacao.equals("0")) return;

        if (confirmacao.equals(usuarioLogado.getSenha())) {
            listaContas.remove(usuarioLogado);
            usuarioLogado = null;
            System.out.println("Conta removida com sucesso.");
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    public static void listarUsuarios(Scanner nmr) {
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---");
        for (Conta conta : listaContas) {
            System.out.println(conta);
        }
        System.out.println("\nPressione Enter para voltar...");
        nmr.nextLine(); 
    }

    public static void listarProdutosAdmin(Scanner txt, Scanner nmr) {
        System.out.println("\n--- ÁREA ADMINISTRATIVA: PRODUTOS ---");
        for (Produto produto : listaProdutos) {
            System.out.println(produto);
        }
    }

    public static boolean isAdmin() {
        return usuarioLogado != null && usuarioLogado.getAdmin();
    }

    public static void menuAdmin() {
        System.out.println("5 - Cadastrar Produtos");
        System.out.println("6 - Editar Produtos");
        System.out.println("7 - Ver Usuários");
    }
}