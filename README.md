README

A framwork escolhida foi o Spring Boot, vem com as features necessárias para programar uma API REST de acordo com os requisitos, bem como é possível utilizar Java.

A API possui um controller com um endpoint que recebe o nome do profissional que queremos pesquisar como query parameter. Não pareceu haver necessidade de destinguir em termos de código pois a pesquisa na primeira API (MovieDb) não faz distinção da mesma, mas caso necessário, poderíamos por exemplo usar um ENUM para melhor distinguir. Este endpoint recebe também um query parameter opcional com dois valores possíveis (true ou false), que permite decidir a conversão ou não dos resultados para um Powerpoint.

Usamos então a primeira API (MovieDb) e usamos um endpoint de search para ir buscar o id para uma busca posterior sobre os filmes em que o profissional participou (aparentemente a lista está limitada a 3 resultados). De seguida, usamos esta lista de id's para chamar um endpoint com informações mais relevantes dos filmes, nomeadamente o IMDB Id e a data de lançamento.

Este IMDB id é então usado para chamar um endpoint da segunda API (OmdbAPi) que nos retorna a restante informação necessária (nome do realizador e atores principais).
Esta informação é toda agregada num Objeto que tanto serve para retornar ao clinte como para gerar o PowerPoint caso seja o caso.

A geração do PowerPoint recorre a uma biblioteca externa, Apache POIO, em que cada página tem como título o nome do file e dois bullet points que contêm o nome do realizador e data de lançamento do filme.

Não foram feitos comentários, salvo uma exceção, tendo em mente um dos príncipios de Clean Code na escrita de código.

Melhorias:
  - Segurança a aplicar na autenticação, não parecendo ser necessário neste caso;
  - Caso o ficheiro application.yml chegue a produção devemos guardar num repositório mas sem chaves visíveis, por exemplo como no Spring Cloud Server com HashiCorp Vault;
  - Melhorar a perfomance das chamadas externas de API's (não foi possível concatenar vários id's numa chamada), usando Futures e sincronizando o modo do merge dos dados, 
  - ou até mesmo usar reactive programming que levaria a mudanças masi profundas;
  - Melhor a abstração das classes, por exemplo dos clientes de API's externas ou do Powerpoint Converter;
  - Apenas foram feitos testes de integração para o controller, aplicar também testes unitário à lógica interna e de integração às API's externas;
  
Como correr o programa:
  -Dentro da raiz do projeto correr o seguinte comando;
  - java -jar santander-0.0.1-SNAPSHOT.jar
