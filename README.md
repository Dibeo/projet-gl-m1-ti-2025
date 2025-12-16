# Application d’Échange Local
Simon F.

[![Serveur CI - Test Junit](https://github.com/Dibeo/projet-gl-m1-ti-2025/actions/workflows/Junit-Test.yml/badge.svg)](https://github.com/Dibeo/projet-gl-m1-ti-2025/actions/workflows/Junit-Test.yml)

Une plateforme permettant aux utilisateurs d’échanger des objets ou des compétences au sein d’une même communauté.

---

## Description du projet

Cette application vise à faciliter l’échange local grâce à une interface intuitive et une architecture moderne.
Les utilisateurs peuvent :

* Publier, rechercher et filtrer des annonces,
* Envoyer et gérer des demandes d’échanges,
* Noter et évaluer les autres utilisateurs,
* Recevoir des notifications en temps réel (push),
* Suivre leurs échanges via une interface claire.

L’architecture générale du projet repose sur :

* Un frontend modulable basé sur une architecture en couches (utilisation de Angular),
* Un backend robuste monolithique (Javalin).

## Vision architecturale

### Client (Front-end)

Architecture en couches :

* Présentation (UI) : Composants Angular, pages, layout
* Services applicatifs : logique métier front
* Domaine (modèles) : entités, types
* Infrastructure : appels API REST

### Serveur (Back-end)

Architecture Monolithique, en suivant la methode SOLID :
---

## Diagrammes UML

Disponibles dans `Doc` (type mermaid):

* Schéma entité relation de la BDD (Database.md),
* Diagrammes de classes coté serveur (Serveur.md).

_Version PNG dans assets_

---

## Lancement du projet

### Lancer le client (Angular)

Avoir Node.js + Angular CLI installés.

```bash
cd client
npm install
ng serve
```

L’application sera disponible sur :
[http://localhost:4200/](http://localhost:4200/)

---

## Lancer le serveur (Monolithique)

```bash
cd Serveur
java -jar target/Serveur-1.0-SNAPSHOT.jar [options]
```

#### Options:

- `-l`, `--logs`: Active la visualisation des logs
- `-db`, `--database`: Type de base de données :_(defaut : Postgres)_
  - Supporté :
    - `postgres` 
    - `sqlite`
- `-db-link`, `--database-link`: lien vers la base _(defaut : localhost)_
- `-p`, `--port`: port vers la base _(defaut : 4040)_
- `-h`, `--help`: Affiche ce message d'aide

---

## Documentation

Accessible dans le dossier `Doc/` :

* UML
* Besoins fonctionnels (SRS)
