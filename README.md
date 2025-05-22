# SpotiFEI
Este programa simula um app de músicas, como o spotify, como projeto da disciplina CCM310 -  Arquitetura de Software e Programação Orientada a Objetos.

## Descrição
O objetivo do projeto **SpotiFEI** é simular uma plataforma de informações de áudios digitais, como músicas e podcasts. O projeto foi inspirado na *Plataforma de Streaming de Áudios Digitais Spotify*. 

## Tecnologias Utilizadas
- Java Swing: Interface gráfica do usuário (GUI)
- JDBC com PostgreSQL: Conexão e manipulação de banco de dados
- Padrão MVC (Model-View-Controller): Organização do código em camadas

## O que foi feito
Este projeto foi desenvolvido no NetBeans e versionado com Git. 

## Como Rodar o Projeto
Para executar este projeto no seu computador usando o NetBeans:
1. **Clone o repositório**

  Abra o terminal e execute:

```bash
git clone https://github.com/Anabsouslima1/SpotiFEI.git
```

2. **Abra o Netbeans**
  Acesse o menu `File > Open Project` e selecione a pasta clonada.
3. **Configure o banco de dados PostgreSQL (recomenda-se PgAdmin)**
  - Crie um banco de dados no PostgreSQL com o nome esperado pelo projeto (spotifei)
  - Atualize as credenciais de conexão no código, localizados na classe Conexao_bd. Exemplo:
   ```bash
String url = "jdbc:postgresql://localhost:5432/nome_do_banco";
   String user = "seu_usuario";
   String password = "sua_senha";
```
4. **Crie as tabelas e insira os dados**
   - Consulte o arquivo `consultas.sql`, que está na raiz deste repositório.
    - Execute o script no seu gerenciador de banco de dados para criar as tabelas e popular o banco com os dados iniciais (biblioteca de músicas).
  - **Nota**: o arquivo `consultas.sql` contém todos os comandos necessários. Para evitar erros, recomenda-se executar os trechos na seguinte ordem: 
    - Criação das tabelas 
    - Inserção dos dados de cada tabela, individualmente.
5. **Execute o projeto**
   Após configurar corretamente o banco de dados, o projeto estará pronto para execução. No NetBeans, clique com o botão direito no projeto e selecione "Run" ou pressione F6. 

## Documentação
### Arquivos Principais



## Professores responsáveis: 
### Profª. Dra. Gabriela Biondi
### Prof. Dr. Isaac Jesus da Silva 
### Prof. Dr. Luciano Rossi.
