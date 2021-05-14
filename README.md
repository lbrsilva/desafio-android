# Desafio Eai

### Principais funcionalidades

- Galeria de imagem iniciando do dia de atual (range de d-10)
- Zoom na imagem (pinch ou duplo clique)
- Carregamento de vídeo de maneira automática
- Cache das requisições realizadas e imagens já exibidas

**API Base:** [https://apod.nasa.gov/apod/](https://apod.nasa.gov/apod/ "https://apod.nasa.gov/apod/")

Na query string utilizei os parâmetros **start_date** e **end_date**, onde sempre carrega com o range de 10 dias de acordo com a paginação realizada pelo usuário.

No botão de info (*i* no canto inferior esquerdo), é possível verificar a data da imagem ou vídeo, o Autor e o descritivo daquela imagem.

## Linguagem Utilizada

- Kotlin

## Padrão de Projeto

- MVVM

## Bibliotecas

- [Retrofit]
- [Hilt]
- [Glide]
- [TouchImageView]
- [YoutubePlayerVideo]
- [Coroutines]

O aplicativo muda o idioma das identificadores da dialog de informações de acordo com o idioma do celular.
Alguns testes unitários foram criados para validar algumas funcionalidades.

[Retrofit]: <https://square.github.io/retrofit>
[Hilt]: <https://developer.android.com/training/dependency-injection/hilt-android?hl=pt-br>
[Glide]: <https://bumptech.github.io/glide>
[TouchImageView]: <https://github.com/MikeOrtiz/TouchImageView>
[YoutubePlayerVideo]: <https://github.com/PierfrancescoSoffritti/android-youtube-player>
[Coroutines]: <https://developer.android.com/kotlin/coroutines>