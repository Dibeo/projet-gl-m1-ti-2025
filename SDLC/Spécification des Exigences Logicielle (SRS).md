# Projet : Application d’échange local de biens et de compétences

---

## 1. Introduction

### 1.1 But du document
Décrire les exigences fonctionnelles et non-fonctionnelles de l’application d’échange local.  
Public cible : équipe de développement, enseignants, testeurs et administrateurs système.

### 1.2 Portée du projet
L’application permet aux utilisateurs de partager des biens et compétences avec leurs voisins afin de favoriser la solidarité locale et réduire la consommation.

### 1.3 Définitions, acronymes et abréviations
- **Annonce** : Publication décrivant un bien ou une compétence disponible.
- **Demande d’échange** : Proposition faite par un utilisateur en réponse à une annonce.
- **Donateur** : Utilisateur créant une annonce.
- **Bénéficiaire** : Utilisateur répondant à une annonce.

### 1.4 Références
- Cours de Génie Logiciel.
- Norme IEEE 830.
- Documentation des frameworks utilisés (ex. Django, React, etc.).

### 1.5 Vue d’ensemble
Le reste du document présente une description générale du système, les exigences fonctionnelles et non-fonctionnelles, ainsi que des diagrammes UML.

---

## 2. Description générale

### 2.1 Perspective du produit
Application web progressive (PWA) fonctionnant sur desktop(et mobile).  
Système autonome avec base de données et API REST.

### 2.2 Fonctionnalités principales
- Authentification et gestion de profil.
- Création et gestion d’annonces.
- Gestion des compétences et des objets.
- Transactions d’échange avec messagerie.
- Notifications en temps réel.
- Système de réputation et notation.

### 2.3 Caractéristiques des utilisateurs
- **Utilisateur standard** : Personne cherchant à échanger biens ou compétences.
- **Administrateur** : Responsable de la modération et gestion du système.

### 2.4 Contraintes
- Adresses confidentielles.
- Mot de passe stocké avec hashage sécurisé.
- Compatible navigateurs modernes.

### 2.5 Hypothèses et dépendances
- Les utilisateurs disposent d’une connexion Internet.
- Les notifications nécessitent un service SMTP ou Push.

---

## 3. Exigences spécifiques

### 3.1 Exigences fonctionnelles
- **RF1** : L’utilisateur peut s’inscrire avec email, nom et localisation.
- **RF2** : Le système permet une connexion sécurisée (email + mot de passe).
- **RF3** : L’utilisateur peut gérer son profil (photo, bio, numéro de téléphone).
- **RF4** : L’utilisateur connecté peut créer une annonce d’objet avec titre, description, image.
- **RF5** : L’utilisateur peut proposer une compétence avec description et disponibilités.
- **RF6** : Les utilisateurs peuvent parcourir/rechercher annonces et compétences.
- **RF7** : Un utilisateur peut envoyer une demande d’échange en précisant l’objet ou la compétence offerts en retour.
- **RF8** : Le créateur d’une annonce peut accepter ou refuser une demande.
- **RF9** : Un système de messagerie permet d’échanger une fois la demande acceptée.
- **RF10** : Les utilisateurs peuvent laisser une note et un avis après un échange.
- **RF11** : Le système envoie des notifications (demandes, messages, avis).

### 3.2 Exigences non-fonctionnelles
- **Performance** : Le temps de réponse du serveur doit être < 2s pour une recherche.
- **Sécurité** : Hashage (bcrypt ou autre) des mots de passe, chiffrement des communications (HTTPS).
- **Fiabilité** : Disponibilité 99% en production.
- **Compatibilité** : Fonctionne sur mobile et desktop (CSS responsive).
- **Convivialité** : 
	- Interface intuitive avec filtres de recherche.
	- Graphiques d'activités (ChartJS)
	- Pop-up durant l'utilisation du site (Solid-Toast)
	- Admin Panel (GridStackJS)

### 3.3 Interfaces
- **Interface utilisateur** : Web responsive (mobile-first) (Angular/ React Native a définir).
- **Interface logicielle** : API REST JSON, WebSocket pour messagerie.
- **Interface matérielle** : PC, smartphone, tablette (Navigateur).
- **Interface de communication** : Email SMTP, Push notifications.

---

## 4. Diagrammes UML
- Diagramme de [[Cas d'utilisations]]
- Diagrammes de séquence (scénario d’échange).
- Diagramme de classes (objets principaux : [[Diagrammes Classes - Client]], [[Diagrammes Classes - Serveur]]).
- Diagrammes d’activité (workflow : créer annonce, gérer demande, etc.).

---

## 5. Autres considérations

### 5.1 Exigences de sécurité
- Authentification forte.
- Confidentialité des données personnelles.
- Anonymisation partielle de la localisation.