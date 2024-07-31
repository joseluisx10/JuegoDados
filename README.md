# Juego de Dados Generala

## Descripción

Desarrollo de una aplicación de juego de dados "Generala", desarrollada en Android Java. La aplicación incluye autenticación de usuarios, una mesa de dados, perfil de usuario, reglas del juego y una lista de cócteles.

## Contenido

- [Login (Firebase Authentication)](#1-login)
- [Mesa de Dados](#2-mesa-de-dados-menú-principal)
- [Perfil de Usuario (Firestore database)](#3-perfil-de-usuario)
- [Reglas del Juego](#4-reglas-del-juego)
- [Lista de Cócteles (TheCocktailDB API para la lista de cócteles)](#5-lista-de-cócteles)

## Operaciones Básicas

### 1. Login

En esta pantalla, el usuario debe ingresar su email y clave, luego presionar el botón "Send" para ingresar.

![Login](https://github.com/user-attachments/assets/cd0a64a5-93eb-4608-97bd-95cee9efbef0)

### 2. Mesa de Dados (Menú Principal)

En la pantalla principal del juego de dados, el usuario puede tirar los dados presionando el botón "Pulsar".

![Mesa de Dados](https://github.com/user-attachments/assets/49a0a474-9db3-4cb7-913a-02c5859ba7d2)

### 3. Perfil de Usuario

Muestra información detallada del usuario en sesión. Para acceder a esta pantalla, debe presionar el botón "Mis datos" desde el menú principal.

![Perfil de Usuario](https://github.com/user-attachments/assets/0059b938-cedb-4e2b-bacd-10128fea47fc)

### 4. Reglas del Juego

Al hacer clic en el botón "Ver Reglas", se muestra una imagen con las instrucciones del juego. En la parte superior de la pantalla, se habilitan dos botones para navegar por las demás pantallas.

![Reglas del Juego](https://github.com/user-attachments/assets/8128c3b7-e644-4233-b6a5-ad27d992846a)

### 5. Lista de Cócteles

Para ingresar, debe presionar el botón "Ver Cocktail" desde la pantalla de reglas del juego. En la siguiente pantalla, se muestran datos de bebidas para acompañar el juego.

![Lista de Cócteles](https://github.com/user-attachments/assets/12000ac7-0cb0-4c81-9624-c9348546385d)

## Instalación y Configuración

1. Clonar el repositorio:

   ```sh
   git clone https://github.com/joseluisx10/final-am-acn4a-leguizamon-quispe.git
   cd final-am-acn4a-leguizamon-quispe

   Abrir el proyecto en Android Studio.

2. Configurar Firebase Authentication y Firestore Database siguiendo la documentación oficial.

3. Ejecutar la aplicación en un dispositivo o emulador Android.

