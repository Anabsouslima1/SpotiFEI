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

## Documentação - Arquivos Principais
A seguir está a lista de pastas e classes criadas, organizadas de acordo com cada funcionalidade. Incluí uma breve descrição para cada um dos recursos utilizados.

### **`controller/`**: Contém os controladores que fazem a mediação entre a interface e os dados.
  - `ControladorPlaylists`
  - `FuncaoCadastro`
  - `FuncaoLogin`
  - `FuncaoMusica`

### **`model/`**: Contém as classes que representam os dados e regras de negócio.
  - `Artista`
  - `Conexao_bd`
  - `Musica`
  - `MusicaCurtida`
  - `MusicaCurtidaDAO`
  - `MusicaDAO`
  - `Pessoa`
  - `Playlist`
  - `PlaylistDAO`
  - `Usuario`
  - `UsuarioDAO`

### **`view/`**: Contém todas as telas da aplicação feitas com Java Swing.
  - `LoginUsuario`: Primeira tela exibida ao executar o programa. Contém campos para o usuário inserir e-mail e senha, além de botões para login e cadastro.
  - `CadastroUsuario`: Tela acessada caso o usuário clique no botão de cadastro na tela de login. Permite o registro de nome, e-mail e senha. Após o cadastro bem-sucedido, o usuário é redirecionado de volta à tela de login.
  - `Home`: A tela principal do projeto. Exibe uma mensagem de boas-vindas com o nome do usuário, lista de músicas disponíveis na biblioteca, campo de busca com filtros, contagem de músicas e links para criação/edição de playlists e acesso às músicas curtidas. É possível interagir com os elementos usando os botões direito e esquerdo do mouse.
  - `CriarPlaylist`:Primeira opção do menu de playlists. Abre uma nova tela para preenchimento dos campos de nome e descrição da playlist.
  - `EditarPlaylist`: Segunda opção no menu de playlists. Exibe uma lista com todas as playlists existentes para seleção. Após escolher uma, uma nova tela é aberta para edição da playlist selecionada.
  - `SelecaoPlaylist`: Tela complementar à edição de playlists. Apresenta os mesmos campos de tela de edição, com um botão para salvar as alterações.
  - `ExcluirPlaylist`: Terceira opção no menu de playlists. Abre uma nova tela para seleção e exclusão de uma playlist existente. 
  - `AdicionarMusicaPlaylist`: Ao clicar em uma música da biblioteca, é possível selecionar a opção de adicioná-la a uma playlist. Uma nova tela é aberta com as playlists disponíveis para escolha.  
  - `MusicasCurtidas`: Exibe uma nova tela com a lista de músicas curtidas na tela Home. A curtida é feita da mesma forma que a adição à playlist: clicando em uma linha da tabela de músicas.

## Professores responsáveis: 
### - Profª. Dra. Gabriela Biondi
### - Prof. Dr. Isaac Jesus da Silva 
### - Prof. Dr. Luciano Rossi
