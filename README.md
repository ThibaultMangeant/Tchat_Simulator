# Tchat Simulator by Mangeant Thibault, Dujardin Thao

## Installation
Vous devez **extraire l'archive** zip ***TchatSimulator*** dans le répertoire de votre choix.

**Compiler le programme** en utilisant la commande `javac *.java` dans le répertoire choisi.

**Exemple d'installation :**
Vous avez **extrait l'archive** dans le dossier `~/TP/s3/r3.06_reseaux/`.
Dans un terminal situé dans `~/TP/s3/r3.06_reseaux/`, utilisez la commande `javac *.java`.

Pour s'assurer que la commande a bien fonctionné, vérifiez l'existence des fichiers d'extension .class.


## Fonctionnalités

- Choisir son pseudo
- Envoyer des messages
- Envoyer des messages privés (à un seul destinataire)
- Se déconnecter

## Fonctionnalités non présentes

- Pseudo unique


## Utilisation

### Lancement et connexion au serveur
**Lancez le serveur** avec la commande `java TestServeurSimple` (toujours dans le répertoire choisi).

Pour se **connecter au serveur**, utilisez la commande `nc <adresse_ip> 2005`. L'adresse IP correspond à celle de la machine exécutant le serveur. Si c'est la même machine que celle où la commande est lancée, utilisez simplement "localhost".

Nota : Utilisez `clear` (Linux) ou `cls` (Windows) avant de vous connecter au tchat pour éviter des problèmes d'affichage.

### Choisir son pseudo
À la connexion, votre pseudo vous sera demandé, **écrivez simplement votre pseudo** dans le terminal et appuyez sur la touche `Entrée` de votre clavier pour valider.

### Envoyer des messages
**Écrivez simplement votre message** dans le terminal et appuyez sur la touche `Entrée` de votre clavier pour envoyer le message.

### Envoyer des messages privés
À l'aide de la commande `/msgpriv <pseudo> <message>`, vous pouvez envoyer un `<message>` dont **seul l'utilisateur** du `<pseudo>` le verra.

### Se déconnecter
En pressant les **touches CTRL+C**, vous pouvez quitter le tchat.
