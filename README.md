| Abordagem  | Rotação de tela | Morte do processo |
| ------------- | --------------- | --------------- |
| Remember | Não Sobrevive  | Não Sobrevive |
| ViewModel + mutableStateOf | Sobrevive  | Não Sobrevive |
| ViewModel + StateFlow | Sobrevive | Não Sobrevive |
| ViewModel + SavedStateHandle | Sobrevive  | Sobrevive |

1. Por que o ViewModel sozinho (etapas 2 e 3) não é suficiente para sobreviver à morte do processo, mesmo sobrevivendo à rotação de tela?

   Trocar mutableStateOf por stateflow não adiciona nenhuma forma de persistência em disco, ele só vive em memória dentro de uma mesmo processo
   A sobrevivencia a destruição do activity se da pelo viewmodel que ambas possuem.

   
2. Qual a diferença prática entre usar mutableStateOf e StateFlow dentro do ViewModel 2 nesta aplicação? Em algum momento essa diferença foi perceptível nos testes?

    Na prática nenhuma, visto que sobrevivem a rotação de tela e não sobrevivem a morte do processo. A diferença entre as duas é mais estrutural do que no comportamento, e não foi perceptível.
    Resumidamente a diferença é que por mais que mais verboso (precisa do .collectAsStateWithLifecycle()) o stateflow permite algumas lógicas assicronas mais complexas e comunicações que o mutablestateof não faz.
    Por sua vez o mutablestateof é integrado nativamente no sistema do Compose.
   
3. Se este placar precisasse ser salvo permanentemente (mesmo após o usuário fechar o app e abrir dias depois), qual das quatro abordagens ainda seria insuficiente, e o que seria necessário adicionar?

    As 4 seriam insuficiente, até o savedstatehandle, porque existe uma diferença entre o processo morrer e o processo ser fechado pelo usuário. O OnSavedInstanceState não é um mecanismo de armazenamento permanente, só uma forma de recriar o sistema e recuperar infos a curto prazo.
    Para uma persistencia seria necessário alguma datastore, ou sqlite, algo nesse sentido.
  
 
4. Na sua opinião, qual abordagem você usaria em produção para este placar e por quê?

   Entre as 4 abordagens, usaria o savedstatehandle para armazenar os valores e dar uma segurança caso o processo do app morra, e para o resto mutablestateof, apesar de criar uma certa "dependencia" com o Compose, eu achei melhor de trabalhar, talvez não seja a melhor escolhar arquitetural caso queira migrar no futuro..

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
OBS: criei tags para ajudar a navegação. *Desculpe ter nomeado de forma errada StateFlow como MutableStateFlow
   
| Nome tag  | etapa | nome abordagem |
| ------------- | ---------- | ----------------------|
|  remember | 1  | Remember |
| View-Model_MutableStateOf | 2  | ViewModel + mutableStateOf |
| ViewModel-MutableStateFlow | 3 | ViewModel + StateFlow |
| ViewModel-SavedStateHandle | 4  | ViewModel + SavedStateHandle |

OBS_2: a ordem dos commits entre StateFlow e SavedStateHandle estão invertidos (StateFlow que foi feito primeiro, está antes) porque no momento de desenvolvimento eu acabei esquecendo de commitar e criar a tag, e ja 
parti pro savedstatehandle. Conferindo percebi que não tinha commitado e acabei dando ctrl + z para reverter as mudanças e voltar para a versão com stateflow (são poucas modificações então não foi um ctrl + z muito grande haha)
De qualquer forma muitas desculpas, estava super cansado e como não consegui fazer no fds devido a uma viagem, tive que fazer depois da aula de ontem.
