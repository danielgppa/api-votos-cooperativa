# API-VOTOS-COOPERATIVA



No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

Cadastrar uma nova pauta;
Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
Contabilizar os votos e dar o resultado da votação na pauta.
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

# Casos de Teste:

[//]: # (__`16/16`__)
1) - [x] Cadastrar nova pauta:
     - Dado: que o usuário vai cadastrar uma nova pauta
     - Quando: o título e a descrição da pauta forem preenchidos
     - Então: a pauta deve ser cadastrada com sucesso
<p><br /></p>

2) - [x] Cadastrar nova pauta com título vazio:
      - Dado: que o usuário vai cadastrar uma nova pauta sem título
      - Quando: o título da pauta não for preenchido
      - Então: a pauta não deve ser cadastrada e deve retornar uma mensagem de erro
<p><br /></p>

3) - [x] Cadastrar nova pauta com título e descrição repetidos:
      - Dado: que o usuário vai cadastrar uma nova pauta com título e descrição repetidos
      - Quando: o título e a descrição da pauta forem repetidos
      - Então: a pauta não deve ser cadastrada e deve retornar uma mensagem de erro
<p><br /></p>

4) - [x] Abrir sessão de votação em uma pauta:
      - Dado: que o usuário vai abrir uma sessão de votação em uma pauta
      - Quando: o id e duração (opcionalmente) da pauta forem preenchidos
      - Então: a sessão de votação deve ser aberta com sucesso
<p><br /></p>

5) - [x] Abrir sessão de votação em uma pauta inexiste:
      - Dado: que o usuário vai abrir uma sessão de votação em uma pauta que não existe
      - Quando: o id da pauta não for encontrado
      - Então: a sessão de votação não deve ser aberta e deve retornar uma mensagem de erro
<p><br /></p>

6) - [x] Abrir sessão de votação em uma pauta específica já está em andamento:
      - Dado: que o usuário vai abrir uma sessão de votação em uma pauta que já está em andamento
      - Quando: o id da pauta já estiver com sessão em andamento
      - Então: a sessão de votação não deve ser aberta e deve retornar uma mensagem de erro
<p><br /></p>

7) - [x] Registrar Associado:
      - Dado: que o usuário vai registrar um novo associado
      - Quando: o cpf do associado for preenchido
      - Então: o associado deve ser registrado com sucesso
<p><br /></p>

8) - [x] Registrar Associado com cpf vazio:
      - Dado: que o usuário vai registrar um novo associado com cpf vazio
      - Quando: o cpf do associado não for preenchido
      - Então: o associado não deve ser registrado e deve retornar uma mensagem de erro
<p><br /></p>

9) - [x] Registrar Associado com cpf inexistente:
      - Dado: que o usuário vai registrar um novo associado com cpf inexistente
      - Quando: o cpf do associado não for encontrado
      - Então: o associado não deve ser registrado e deve retornar uma mensagem de erro
<p><br /></p>

10) - [x] Registrar Associado com cpf já registrado:
      - Dado: que o usuário vai registrar um novo associado com cpf já registrado
      - Quando: o cpf do associado já estiver registrado
      - Então: o associado não deve ser registrado e deve retornar uma mensagem de erro
<p><br /></p>

11) - [x] Registrar um Voto:
      - Dado: que o usuário vai registrar um novo voto
      - Quando: o id da pauta, o id do associado e o voto forem preenchidos corretamente
      - Então: o voto deve ser registrado com sucesso
<p><br /></p>

12) - [x] Registrar mais de um Voto para o mesmo associado na mesma Pauta:
      - Dado: que o associado vai registrar um novo voto em uma Pauta já votada pelo mesmo
      - Quando: o mesmo id da pauta e o mesmo id do associado forem preenchidos novamente
      - Então: o voto não deve ser registrado e deve retornar uma mensagem de erro
<p><br /></p>

13) - [x] Registrar um Voto inválido (diferente de true ou false):
      - Dado: que o usuário vai registrar um novo voto inválido
      - Quando: o voto for diferente de true ou false
      - Então: o voto não deve ser registrado e deve retornar uma mensagem de erro
<p><br /></p>

14) - [x] Registrar um Voto sem preencher o id do associado:
      - Dado: que o usuário vai registrar um novo voto sem preencher o id do associado
      - Quando: o id da pauta e o voto forem preenchidos sem o id do associado
      - Então: o voto não deve ser registrado e deve retornar uma mensagem de erro
<p><br /></p>

15) - [x] Resultado da votação:
      - Dado: que o usuário vai consultar o resultado da votação
      - Quando: o id da pauta for preenchido
      - Então: o resultado da votação deve ser exibido com sucesso
<p><br /></p>

16) - [x] Resultado da votação sem votos contabilizados:
      - Dado: que o usuário vai consultar o resultado de uma votação sem votos contabilizados
      - Quando: o id da pauta for preenchido
      - Então: deve retornar uma mensagem de erro de votação inexistente
<p><br /></p>