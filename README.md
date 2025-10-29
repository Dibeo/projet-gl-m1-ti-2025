# Projet d’application d’échange local

## Description
Ce projet a pour objectif de développer une application d’échange local permettant aux utilisateurs de :  
- Publier et rechercher des annonces d’objets ou de compétences,  
- Envoyer et gérer des demandes d’échanges,  
- Noter et évaluer les utilisateurs,  
- Être notifiés en temps réel des interactions.  

L’architecture est conçue pour être modulaire, évolutive et robuste, avec un frontend en couches et un backend en microservices.

---

## Structure actuelle du projet
Pour le moment, le projet contient uniquement la **Documentation** générale :

---
## Vision architecturale

Documentation/SRS contient :  
- Le patron d’architecture du client (pattern en couches),  
- Le patron d’architecture du serveur (pattern microservices),  
- Les besoins fonctionnels et techniques associés.  

- **Client (Front-end)** : architecture en couches  
  - Présentation (UI)  
  - Services applicatifs  
  - Domaine (modèles)  
  - Infrastructure (API, WebSocket)  

- **Serveur (Back-end)** : architecture microservices  
  - Microservice Utilisateurs  
  - Microservice Annonces  
  - Microservice Échanges  
  - Microservice Notifications  
  - API Gateway et services transverses (Discovery, Monitoring, Config, Sécurité)  

---
## Diagramme UML

Documentation/UML contient :
- Le diagramme de cas d'utilisation,
- Le diagramme de classe coté client (versions light et détaillée).

---

## Documentation
La doc (ouvrable avec Obsidian) contient :  
- Les schémas Canvas (Obsidian),  
- Les schémas UML,
- Les besoins fonctionnels,  
- L’architecture cible.  

---
