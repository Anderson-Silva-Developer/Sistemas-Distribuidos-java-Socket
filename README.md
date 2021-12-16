# Sistemas-Distribuidos

### **Socket**
 <div style="text-align: justify"> De acordo com a própria Oracle: “Socket é um ponto de comunicação entre duas máquinas”, ou seja, podemos enviar mensagens entre a máquina A e a máquina B através de uma conexão estabelecida com o Socket..</div>
  
  [devmedia](https://www.devmedia.com.br/java-socket-entendendo-a-classe-socket-e-a-serversocket-em-detalhes/31894)
 
## Tecnologias utiizadas para o projeto
* Socket
* java 11
* javafx 11
* [Scene Builder 16.0.0](https://gluonhq.com/products/scene-builder/)

## Descrição do projeto
<div style="text-align: justify"> O projeto foi feito com as tecnologias javafx, socket e java-11 se trata de um jogo de dois players onde o objetivo é movimentar-se e conseguir o maior numero de recompensas essas não são vistas por eles e podem ser positivas(adiociona pontos) ou negativas(diminui os pontos).</div>

## server
![server](https://user-images.githubusercontent.com/51086466/146376036-afca09ca-306b-401a-9c28-06d1581a4869.png)
## start server exercute a classe Server.java
<div style="text-align: justify">caso queira testar em outro servidor sem ser o local crie um projeto apenas para server usando esse diretório</div>

![startServer](https://user-images.githubusercontent.com/51086466/146376410-ff202120-ccac-4766-9548-bc4eadd77208.png)

## login Player (cada player começa com um ponto se perder coeça com 5 pontos) 

![login](https://user-images.githubusercontent.com/51086466/146377175-bd158ffb-9d3c-44c7-9804-6be555e3f5a5.png)
![entrando](https://user-images.githubusercontent.com/51086466/146377289-0ad35b98-c011-414a-941e-046c99515ec9.png)

## segundo Player
![segundo](https://user-images.githubusercontent.com/51086466/146377475-55c55174-b580-4230-82f3-da0d5cb4fecc.png)
## indicadores de posição em relação a recompensa
* (cor amarela) o player está a dois passos da recompensa
* (cor branca) o player está a um passos da recompensa
* (sem cor) o player está a mais de dois passos da recompens
* (cor verde) ganhou um ponto
* (cor vermelha) perdeu um ponto

## amarela
![amarelo](https://user-images.githubusercontent.com/51086466/146378306-b2bd9dbb-79cb-4071-a570-74c5b8e356c1.png)
## branca
![branco](https://user-images.githubusercontent.com/51086466/146378456-66213da0-82fa-4e59-8f66-9b2de5d67786.png)
## verde
![verde](https://user-images.githubusercontent.com/51086466/146378550-20fbf1a6-d00d-418b-b823-61fd5db9d331.png)
## vermelho
![vermelho](https://user-images.githubusercontent.com/51086466/146378720-6f1605f5-d51f-40aa-91ed-0227d1bc46f9.png)
## derota (o player começa com 5 pontos novamente)
![perdeu](https://user-images.githubusercontent.com/51086466/146379084-50074b97-f851-414d-9289-277af69233aa.png)



