# CryptoWallet API


API gerenciador para uma plataforma de intercambio de criptomoedas. A aplicação armazena clientes e suas carteiras com 
criptomoedas. Além de fazer o controle e autenticação de usuarios. 

### Funcionalidades

Algumas funcionalidades estão restritas a usuarios devidamente autenticados:

- [x] Autenticação de usuarios
- [x] CRUD das clientes, carteiras e criptomoedas.
- [x] Adicionar, remover e gerir a quantidade de criptomoedas que cada cliente possui.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Você instalou `Java 11`


## ☕ Usando CryptoWallet_API

Leia a documentação e teste o API:
```
https://gftstartcryptowalletapi.herokuapp.com/swagger-ui.html
```

Para fins de teste, você pode se registrar acessando com o usuario padrão:

**ADMIN**:
* Email: admin_test@gft.com
* Senha: 1234

Quando tiver o seu Token de usuario, clique no butão `Autenticar` e no campo `api_key()` ingresse o seu token
da seguinte maneira:
```
"Bearer" + " " + token
```  

## 📝 Créditos

Esse projeto foi desenvolvido por [Alex López](https://github.com/lop19029). como parte do programa Starter da  [GFT](https://www.gft.com/br/pt).
