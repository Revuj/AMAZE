# AMAZE

Neste jogo controla-se um herói que se move, deslizando até embater numa parede, com o objetivo de chegar ao final de cada puzzle (nível). Visto que o herói é limitado no seu movimento, todos os movimentos terão de ser bem pensados, sendo possível apanhar moedas (de modo a aumentar a pontuação) e usar portais para aumentar a dinâmica do jogo.

### Instructions: How to play?
 No menu, pode-se navegar para cima e para baixo, usando w,s. Para selecionar a alternativa correta, usa-se a spacebar. Há duas alternativas no menu, ou jogar ou sair do jogo.Quando no jogo pode-se voltar ao menu principal, usando a letra q. Para controlar o herói usa-se w,a,s,d.

## Implemented Features
* **Leitura de Níveis:** Os mapas que definem os níveis, constituíndo informação sobre a posição dos elementos do jogo, são lidos em run-time pelo programa. Sempre que se completa um nível, o nível seguinte é lido através de um ficheiro e processado.A criação de novos níveis é bastante simples.
* **Colisao:** As colisões são bastante importantes para garantir o bom funcionamento do jogo. O programa não permite que o herói se mova através de paredes e deteta se o mesmo chegou ao fim do nível.  Eventualmente, será preciso verificar colisões entre outros elementos do jogo como moedas, power-ups e inimigos.
* **Movimento do Herói:** O herói movimenta-se deslizando (sem poder ser interrompido) até encontrar  uma parede na sua direção de movimento. Este movimento é controlado recebendo o input do utilizador (teclas w,a,s,d).
* **Multi Threads:** As funções usadas para ler o input do utilizador, bloqueiam enquanto esperam, o que impede o movimento contínuo e suave do herói. Para resolver este problema criou-se uma segunda thread que espera pelo input, não bloqueando a thread principal que trata das outras funcionalidades do jogo, como desenhar no ecrã. Outras threads poderão a vir ser usadas aquando da implentação de outras features.
* **Ciclo Principal:** Se o ciclo principal consistisse em algo como:
		       while (true) {
			readInput();
			ConsumeEvents();
			draw();
		       }
Não seria possível controlar nem o frame rate nem o update rate do jogo. Estes dependeriam apenas da velocidade do processador da máquina onde está a correr o jogo.
Para resolver este problema, implementou-se um sistema que permite controlar os FPS (frames per second) e UPS (updates per second) usando, para isso, medições de tempo (e diferenças entre elas) de modo a compensar quando uma iteração se processa demasiado rapidamente.

* **Moedas:** O herói poderá apanhar moedas de modo a aumentar a sua pontuação. Por cada moeda coletada, a pontuação é incrementada em 50 pontos.

* **Score** Cada vez que o jogador passa de nível o seu score é incrementado em 100 pontos.

* **Portais:** Existirão alguns níveis em que, de modo a serem mais complicados, o herói poderá ter de ocorrer a portais que o podem teletransportar para outro sítio no mapa.

* **Swing** O jogo irá poder ser jogado, usando, para além de Lanterna, Swing. Deve ser passado como parâmetro da linha de comandos ou 'swing' ou 'lanterna', dependendo da view que é pretendida usar.

* **Menu:** Foram implementado dois mvc, o primeiro correspondente à jogabilidade e ao jogo em si, e outro correspondente a um menu que usado para jogar ou sair do jogo.




## Design Architecure

### Problem In Context
Um dos problemas mais comuns no desenvolvimento de jogos consiste na arquitetura do projeto. Devido à complexidade das aplicações, torna-se ainda mais importante a separação organizada dos módulos utilizados, de modo a permitir um crescimento saudável do jogo.

### The Pattern
A arquitetura do jogo baseia-se na **MVC**-Model, View, Controller, separando as responsabilidades. O Model é responsável por guardar dados que deverão ser usados para desenhar no ecrã o jogo, sendo a View a responsável por isto. Para além disso, esta interpreta os comandos do utilizador (teclas para movimento do herói), enviando eventos ao Controller. Este interpreta os comandos e age em conformidade, podendo, por exemplo, desencadear o update de modelos.

### Implementation

![MVC UML](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/UMLS/mvc.png)

### Consequences
Esta arquitetura ajuda na modulação do código, definindo propósitos mais específicos para cada módulo. Desta forma, obtém-se uma estrutura muito mais organizada que permite alterar certos aspetos do código sem precisar de ter em conta outros.



## Design Patterns


### Problem In Context
Para que o jogo possa decorrer, o input do utilizador é processado e traduzido em ações que modificam o estado do jogo. É, por isso, importante garantir a organização das ações em causa de modo a ser fácil adicionar outras e manipulá-las, separando-as do objeto que as invoca.

### The Pattern
O padrão comportamental utilizado é o padrão **Command** que torna os pedidos de ação em objetos independentes que contêm a informação sobre o pedido e sabem realizar a ação necessária. Permite também parametrizar métodos, considerando diferentes pedidos, e realizar algumas operações que podem ser úteis como undo, redo, etc. Consiste num Behaviour Design Pattern.

### Implementation

![Command UML](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/UMLS/command.png)

### Consequences
Com a utilização deste padrão isolam-se as ações (mudanças de estado do herói) dos pedidos (input do teclado) , tratando os comandos como objetos, o que nos permite manipulá-los e adicionar outros, facilmente. 
Através desta implementação, encapsula-se um comando requisitado como um objeto. Mais especificamente, cada tecla usada para o movimento do herói serve como um comando que tenta mudar o estado do herói.Os comandos, para que a funcionalidade do jogo seja a pretendida, só serão chamados quando o herói está no estado STOPPED (parado).O single Responsible Principle é tido em conta bem como o open/closed.


### Problem In Context
No jogo, ou se está a jogar ou pode-se estar no menu principal. Sempre que este estado muda surge a necessidade de todos os objetos conectados com esse estado sejam notificados para que possam ser atualizados, permitindo que objetos interessados sejam "avisados" acerca da mudança de estado ou de outros eventos que ocorreram noutro objeto.

### The Pattern
O padrão comportamental usado é o padrão **Observer** que define um mecanismo de subscrição que permite notificar vários objetos acerca de um evento que tenha acontecido aos objetos que estão a observar.

### Implementation

![Command UML](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/UMLS/observer.png)


### Consequences
O  princípio Open/Closed é tido em conta. É possível estabelecer relação entre os objetos em runtime. Contudo, não existe uma ordem pré-definida pela qual é feita a notificação.


### Problem in Context
Muitas classes poderiam apresentar muito código repetido. Para além disso sem usar polimorfismo poderá existir um uso de expressões condicionais extensas e indesejáveis.

### The pattern
O padrão comportamental usado para resolver o problema foi o **Template Method**.Este define o esqueleto do algoritmo na superclass mas são as subclasses que implementam especificamente o algoritmo.

### Implementation

![Command UML](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/UMLS/templateMethod.png)


###Consequences
O código duplicado passa a ser colocado numa superclasse. As subsclasses podem fazer override de apenas partes pequenas de um algoritmo, ficando estas menos afetadas pelas mudanças feitas nas outras partes do algoritmo. Algumas subclasses podem ficar limitadas pelo "esqueleto" que é fornecido. Podem ser bastante difíceis de manter quantos mais passos tiverem e o LisKov Substitution Principle pode ser violado.

### Problem In Context
Neste jogo, o herói deve-se movimentar consoante o estado atual, até embater numa parede ou passar de nível. Para isso acontecer é preciso alterar o comportamento (movimento) do herói, segundo o estado interno em que se encontra (MovingDown, MovingUp, MovingLeft, MovingRight).

### The Pattern
Sem o uso de um padrão de desenho ter-se-ia uma grande quantidade de conditional statements e, por isso, decidiu-se recorrer ao uso do padrão comportamental **State** que permitirá resolver o problema referido, criando uma classe para cada estado e associando ao herói uma máquina de estados que permite controlar o seu comportamento dependendo do estado em questão. Consiste num Behaviour Design Pattern.

### Implementation

![State UML](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/UMLS/state.png)

### Consequences
Com o uso do padrão as transições de estado do herói tornam-se mais explícitas e o código torna-se mais modular e organizado, permitindo manipular facilmente as ações a realizar segundo cada estado bem como adicionar novos estados (cada um corresponde a uma classe).


### Problem in Context
Delinear objetos até aos níveis mais baixos de granularidade, isto é, de detalhe  fornece maior flexibilidade, mas pode afetar a eficiência devido ao uso pesado de memória.

### The Pattern
Usar a partilha para suportar elevados número de objetos detalhada e eficientemente, substituindo widgets de elevado peso por gadgets de baixo. Para que isto seja conseguido, pode ser aplicado o **FlyWeight**. Aliado a este aparece ainda o **Factory**. FlyWeight consiste num structural Design Pattern e factory consiste num Creational Design Pattern.

### Implementação

![Factory UML](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/UMLS/factory.png)

A sua implementação passa por criar uma Factory. O cliente, GameView, em vez de criar as entidades que compõem a cena, apenas requisita-as à fábrica. A Fábrica é usada com o intuito de delegar a uma classe a responsabilidade de criar determinados objetos (EntityViews). A fábrica mantém uma cache de objetos para que possam ser usados posteriormente, sendo, portanto, reutilizados.

### Consequences
A Fábrica permite separar a criação de objeto da sua instanciação, tornado o código mais simples e claro. O uso de uma cache permite reutilizar os objetos nela contidos posteriormente, tornando o jogo menos pesado e consequentemente, operando mais suavemente.


## Known Code Smells and Refactoring Suggestions
As comparações poderiam ser evitadas com nulls, sendo tratado de forma "transparente". Para este caso bastava aplicar o Null Object design pattern.

O GameModel para além de ler os níveis, tem a informação deles, pelo que para cada classe ter a sua responsabilidade, esta poderia ter sido dividida em duas, obdecendo ao Single responsability principle.

Vários if seguidos poderiam ter sido evitados se fosse usado mais polimorfismo, contudo poderia levar ao uso excessivo de classes.

Switch Case longo em funções que tratam de ler/processar o input. No entanto achamos ser inevitável.

Código duplicado em funções que diferem apenas no tipo de views que usam (lanterna ou swing) nas classes GameState e MenuState. Bastaria colocar o código duplicado numa classe superior a ambas.

Nota: Muitos dos smells referidos não conseguiram ser tratados devido à falta de e/ou má gestão de tempo, embora consideremos que se deva principalmente à primeira. Deste facto, acabamos por não conseguir implementar o menu inicial em Swing porque não conseguimos descobrir o problema a tempo de entrega. Passo a explicar o problema: De modo a passar do menu inicial para o jogo propriamente dito, recorria ao uso de funções como removeAll() de modo a fazer "clear" do frame e preenche-lo com os novos elementos. No entando ao fazer isso, a função paintComponent() do novo painel deixava de ser chamada pela função repaint().


## Testing Results

[Coverage Tests](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/Coverage/index.html)
[Mutation Tests](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_73/blob/master/docs/Mutation/index.html)

## Self-evaluation

Consideramos que o trabalho foi distribuido de modo equitativo entre ambos os membros do grupo.
