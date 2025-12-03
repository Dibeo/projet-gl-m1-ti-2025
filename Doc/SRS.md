# Software Requirements Specification (SRS)

**Titre du projet :** Application d’échange local de biens et de compétences
**Version :** 1.0
**Auteur :** Simon
**Date :** —

---

# 1. Introduction

## 1.1 Objectif du document

Décrire les exigences fonctionnelles et non fonctionnelles pour le serveur Java monolithique, implémenté avec Jetty et Hibernate ORM, compatible SQLite et PostgreSQL, en appliquant les principes SOLID.

## 1.2 Portée du système

Serveur backend monolithique : API REST pour le frontend, logique métier, persistance via Hibernate, notifications, messagerie, gestion des échanges, système d’avis et d’authentification.

## 1.3 Définitions, acronymes et abréviations

- API : Application Programming Interface
- SGBD : Système de gestion de base de données
- SOLID : principes de conception objet
- JWT : JSON Web Token
- ORM : Object Relational Mapping
- ACID : Atomicité, Cohérence, Isolation et Durabilité

## 1.4 Références

Projet de synthèse : Application d’échange local de biens et de compétences.

---

# 2. Description générale

## 2.1 Perspective du produit

Monolithe Java, exécution sur JVM 17+, serveur embarqué Jetty, persistance via Hibernate ORM. L’application expose une API REST et persiste les données dans une base relationnelle (PostgreSQL en production, SQLite possible pour démo et/ou tests).

## 2.2 Fonctions du produit

- Authentification (JWT), gestion des profils.
- CRUD annonces (objets, compétences).
- Gestion des demandes d’échange et messagerie.
- Avis et notation.
- Notifications (in-app _+ email_).

## 2.3 Caractéristiques des utilisateurs

- Visiteur (lecture limitée).
- Utilisateur authentifié (création/modification d’annonces, échanges).
- Administrateur (gestion, suppression abusive).

## 2.4 Contraintes générales

- Application monolithique.
- Persistance via Hibernate.
- Serveur embarqué Jetty.
- Compatibilité : SQLite (local et/ou dev) et PostgreSQL (pour déploiement).
- Respect minimal de la confidentialité (pas d' adresse/localisation exacte exposée).

## 2.5 Hypothèses et dépendances

- Frontend respecte conventions des API REST.
- Serveur SMTP disponible pour envois d’e-mails (non-implémenté).
- Environnements Java 17+ disponibles.

---

# 3. Exigences fonctionnelles

## 3.1 Authentification

- RF-AUTH-001 : Inscription (email, nom, localisation approximative).
- RF-AUTH-002 : Connexion et émission d’un JWT.
- RF-AUTH-003 : Stockage des mots de passe hachés (BCrypt).

## 3.2 Gestion des utilisateurs

- RF-USR-001 : Edition du profil (bio, photo).
- RF-USR-002 : Calcul et retour de la note moyenne.

## 3.3 Annonces (objets)

- RF-OBJ-001 : Création d’annonce (titre, description, catégorie, images, disponibilité).
- RF-OBJ-002 : Recherche/filtrage par mots-clés/catégorie.
- RF-OBJ-004 : Consultation d’une annonce.

## 3.4 Annonces (compétences)

- RF-COMP-001 : Création, recherche, consultation d’annonces de compétences.

## 3.5 Échanges

- RF-ECH-001 : Envoi de demande d’échange.
- RF-ECH-002 : Acceptation/refus par le propriétaire.
- RF-ECH-004 : Historique des échanges.

## 3.6 Messagerie

- RF-MSG-001 : Création de conversation à l’acceptation.
- RF-MSG-002 : Envoi et persistance de messages.

## 3.7 Avis

- RF-AVIS-001 : Notation 1–5 étoiles.
- RF-AVIS-002 : Commentaire public.

## 3.8 Notifications

- RF-NOTIF-001 : Notification sur nouvelle demande.
- RF-NOTIF-002 : Notification de message.
- RF-NOTIF-003 : Envoi d’e-mail (si configuré).

---

# 4. Exigences non fonctionnelles

## 4.1 Performance

- Réponse < 300 ms.

## 4.2 Sécurité

- JWT pour authentification.
- BCrypt pour mots de passe.
- Validation côté serveur.
- HTTPS obligatoire en production, sinon infos visible par tous le monde.

## 4.3 Fiabilité

- Transactions ACID grace à JPA/Hibernate pour les opérations critiques.

## 4.4 Maintenabilité (SOLID)

L’architecture applicative doit suivre SOLID :

- Single Responsibility (SRP) : classes fines (contrôleur/service/repository).
- Open/Closed : logique extensible via des interfaces et injections (ex : NotificationService interface, impl EmailNotification, InAppNotification).
- Liskov Substitution : interfaces stables pour des services persistants.
- Interface Segregation : petites interfaces cohérentes.
- Dependency Inversion : modules de haut niveau dépendent d’interfaces abstraites.

## 4.5 Scalabilité

- Pour un potentiel découpage futur : couches claires, contrats API, repository faiblement couplés.
- En production, possibilité d’horizontaliser la lecture et d’extraire services si besoin.

## 4.6 Compatibilité

- API JSON, UTF-8.
- ORM Hibernate configuré pour PostgreSQL et SQLite (dialects configurables).

---

# 5. Interfaces externes

## 5.1 API REST

- POST /auth/register
- POST /auth/login
- GET /annonces
- POST /annonces
- POST /echanges/{id}/demande
- POST /messages
- GET /notifications

## 5.2 Base de données

Tables principales : utilisateur, annonce, echange, message, avis, notification.

## 5.3 Services externes

- SMTP (envoi d’e-mails) (Non implémenté).
- (Optionnel) service d’hébergement d’objets multimédias (S3 ou stockage local).

---

# 6. Contraintes techniques

## 6.1 Environnement d’exécution

- Java 17+
- Jetty embarqué (démarré par l’application)
- Spring Boot (ou configuration Jetty standalone gérée par Spring)
- Maven ou Gradle

## 6.2 Dépendances logicielles

- Jetty
- Hibernate ORM
- Driver PostgreSQL (`org.postgresql:postgresql`)
- Driver SQLite (ex : `org.xerial:sqlite-jdbc`) pour dev local
- JUnit (tests)

## 6.3 Configuration Hibernate

- Par défaut base de données : PostgreSQL
- Pour changer le type ajouter : `--database` suivi du type

  Actuellement supporté :

  - PostgreSQL (par défaut)
  - - SQLite (pour les tests)

# 7. Critères d’acceptation

## 7.1 Critères fonctionnels

- Flux d’échange complet (demande → acceptation → messagerie → avis) fonctionnel.
- Authentification et autorisations vérifiées.

## 7.2 Critères non fonctionnels

- Tests de compatibilité DB : exécution de la suite d’intégration avec SQLite (CI) et PostgreSQL (préprod).
- Temps de réponse conforme (< 300 ms pour opérations classiques/standards).
- Sécurité : mots de passe hachés.

## 7.3 Critères spécifiques DB

- Compatible PostgreSQL et SQLite.
- Application démarrée et fonctionne avec SQLite pour tests locaux (prise en compte des limites de concurrence).

---

# 8. Annexes

## 8.1 Modèle conceptuel de données (à produire)

- Diagramme entités-relations : utilisateur, annonce, echange, message, avis, notification.

## 8.2 Diagrammes d’architecture (à produire)

- Diagramme de classes (respectant SOLID : controllers/services/repositories).
- Diagramme de séquence pour le flux d’échange.

## 8.3 Exemples de configuration

- Script de démarrage Jetty intégré.
