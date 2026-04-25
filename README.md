# Sistema-de-Loja-em-JAVA
## Resumo: Um sistema simples feito com JAVA para uma loja online utilizando conceitos de POO, verificações de email e senha e permissões de usuário.

O objetivo desse projeto foi treinar meus conhecimentos na linguagem JAVA, não apenas isso, mas reforçar conceitos da Programação Orientada a Objetos. Esse sistema foi feito pensando em alguns requisitos:
## Para o login, cadastro e verificações
- O Usuário não precisa estar logado para visualizar os Produtos;
- O Usuário precisa estar logado para visualizar seu Perfil e efetuar Compras;
- O Usuário só pode se logar com uma conta já existente.
- O Usuário não pode cadastrar uma conta já existente;
- O Email cadastrado deve estar no formato correto ( @###.com) e ter entre 5 a 50 letras;
- As senhas precisam ter entre 6 a 20 caracteres, contendo ao menos uma letra minúscula, 1 letra maiúscula e 1 caracter especial;

## Para o Administrador
- Apenas o Administrador poderá acessar certas opções do Menu;
- Deve haver um Menu de Administrador;
- Apenas o Administrador poderá visualizar a Lista de Usuários e Editar/Remover Produtos;
  
## Para os Produtos
- Quando a quantidade de um Produto chega a zero, ele se torna indisponível para a Compra;

## Para o Usuário
- Usuários podem deletar sua conta, porém precisam se verificar e confirmar com o uso de sua senha;

## Considerações do DEV
No fim, todos os requisitos foram atingidos após algumas horas de desenvolvimento e testes. Para dar continuidade a esse projeto pretendo fazer uma ligação direta com um Banco de Dados SQL, criar um registro de logs para melhorar a segurança e adicionar uma Interface Gráfica para não depender do uso do Terminal e tornar a aplicação mais "palpável". Caso usem esse sistema para algum teste, ficaria muito agradecido em receber feedbacks de melhoria ou correção de bugs!
