# Eu, Colaborador - Android APP

<span style="display: block; text-align:center;">
    <img src="eu-colaborador.png" height="400" >
</span>

## Requisitos

Este aplicativo foi desenvolvido usando as seguintes ferramentas:

- IntelliJ IDEA Community Edition 2020.3
- Java JDK 15
- Android JDK v29

## Configuração do ambiente de desenvolvimento

- **Passo 1**: Verifique se você possui o [Kit de Desenvolvimento do Java (JDK)](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html) instalado no computador. Este aplicativo está configurado para usar a versão 15. Você pode verificar a versão instalada executando o seguinte comando no terminal do seu SO:

  ```bash
  javac --version
  ```

- **Passo 2**: Faça o _dowload_ do IntelliJ IDEA Community Edition e instale em seu computador. Você pode obtê-lo [neste link](https://www.jetbrains.com/pt-br/idea/download).

- **Passo 3**: Faça o download do Android Studio e instale localmente no seu computador. Em seguida execute o mesmo e faça a instalação do Android SDK v29 (Android 10) e do Dispositivo Virtual Android (AVD) em `Configure > SDK/AVD Manager`. Por fim, configure a localização do SDK no IntelliJ acessando o menu `File > Settings > Appearance & Behaviour > System Settings > Android SDK`.

- **Passo 4**: Adicione a pasta "EuAgenteAndroid" ao seu workspace no IntelliJ, pois ela é o root do projeto (abra o projeto a partir do arquivo settings.gradle).  Após isso, faça o _build_ da aplicação através do menu Build > Build Project. Pode ser que seja necessário criar um arquivo local.properties contendo a localização do SDK na variável sdk.dir.

- **Passo 5**: Se você seguiu os passos anteriores e não ocorreram erros durante o processo, você será capaz de executar o aplicativo no dispositivo virtual selecionando o menu `Run`.

## Gerando APK

Para gerar o APK você deve, primeiro, configurar seu ambiente de desenvolvimento seguindo os passos apresentados na seção anterior.

Feito isso, você deve acessar o menu `Build > Build Bundle(s) / APK(s) > Build APK(s)` que o processo de exportação terá início. 
Não havendo erros,o APK será armazenado na pasta `app > build > outputs > apk`.
