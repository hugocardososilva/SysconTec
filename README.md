# Sycon

# observações de instalação:

Crie um novo database com o nome: syscon senha:12345 . Em seguida, vá no package util e rode a classe Execute para criar um sample de administrador, o login sera admin e senha admin

existem alguns bug no controle de sessão que ainda não foram corrigidos, portanto, use o nagegador para acessar http:localhost:8080/syscon/

no servidor, abra o arquivo server.xhml e adicione  "Maior(abrir tag)" Context docBase="C:\syscon" path="/sysconImages" debug="0" privileged="true" / "Menor(fechar tag)"   para criar um diretorio virtual onde estarão as imagens capturadas da webcam. Adicione ao c: a pasta syscon.

