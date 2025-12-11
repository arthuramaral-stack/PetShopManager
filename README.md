# 🐾 PetShop Manager  
Sistema desenvolvido para a disciplina **Programação Orientada a Objetos**, com objetivo de gerenciar **Clientes**, **Pets** e **Serviços** através de uma interface gráfica simples e funcional utilizando **Java Swing**.

---

## 📘 Sumário
- [Descrição](#descrição)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura do Projeto](#arquitetura-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Capturas de Tela](#capturas-de-tela)
- [Persistência de Dados](#persistência-de-dados)
- [Autores](#autores)
- [Documentação Completa](#documentação-completa)

---

## 📌 Descrição
O **PetShop Manager** é um sistema que permite:

✔ Cadastrar e gerenciar clientes  
✔ Cadastrar pets vinculados a clientes  
✔ Agendar serviços como banho e tosa, consulta veterinária, hospedagem e mais  
✔ Persistir todos os dados em arquivos CSV  

O foco do projeto é demonstrar:

- Boas práticas de POO  
- Estruturação em camadas (MVC simplificado)  
- Uso de interface gráfica com Swing  
- Manipulação de arquivos CSV  
- Validação de entrada de dados  

---

## 🛠 Tecnologias Utilizadas

- **Java 17+**
- **Eclipse IDE**
- **Java Swing (GUI)**
- **CSV (persistência de dados)**

---

## 🧱 Arquitetura do Projeto

src/
├── dao/ # Camada de acesso a dados (CSV)
│ ├── ClienteDAO.java
│ ├── PetDAO.java
│ ├── ServicoDAO.java
│ └── CSVUtils.java
│
├── model/ # Classes principais (entidades)
│ ├── Cliente.java
│ ├── Pet.java
│ └── Servico.java
│
└── view/ # Telas da aplicação (Swing)
├── MenuPrincipal.java
├── TelaClientes.java
├── TelaPets.java
└── TelaServicos.java


---

## ✨ Funcionalidades

### ✔ **Clientes**
- Cadastro com validação (nome, telefone, email)
- Exclusão
- Listagem automática

### ✔ **Pets**
- Cadastro vinculado ao cliente
- Validações (idade e peso)
- Exclusão e listagem

### ✔ **Serviços**
- Agendamento de serviços
- Preço validado
- Data obrigatoriamente futura
- Cancelamento e listagem

---

## ▶️ Como Executar o Projeto

1. Abra o Eclipse  
2. Importe o projeto via:  
   `File → Import → Existing Projects into Workspace`  
3. Abra o arquivo:  
   `src/view/MenuPrincipal.java`  
4. Clique com o botão direito →  
   **Run As → Java Application**

A interface gráfica será exibida automaticamente.

---

## 🖼 Capturas de Tela

### **Menu Principal**
![Menu](./CapturaMenu.png)

### **Clientes**
![Clientes](./CapturaClientes.png)

### **Pets**
![Pets](./CapturaPets.png)

### **Serviços**
![Servicos](./CapturaServicos.png)

---

## 💾 Persistência de Dados

Os dados são armazenados automaticamente em arquivos CSV:

- `clientes.csv`
- `pets.csv`
- `servicos.csv`

Cada linha representa um registro, permitindo fácil leitura, exportação e backup.

---

## 👨‍💻 Autores

- **Arthur Amaral dos Santos**
- **Lucas Ferreira**
- **Arthur Rocha**

Professor: *Felippe Pires Ferreira*  
Disciplina: *Programação Orientada a Objetos (POO)*  

---

## 📄 Documentação Completa

O PDF oficial com instruções e prints está disponível aqui:

👉 [Instrucoes_PetShop_Manager_V2.pdf](./Instrucoes_PetShop_Manager_V2.pdf)

---

## 🎓 Status do Projeto
✔ Concluído  
✔ Testado  
✔ Entregue para avaliação

---

## 📝 Licença
Este projeto é destinado apenas para fins acadêmicos.
