___
```plantuml@startuml
title Diagramme de classes du client (pattern en couches - simplifié)

top to bottom direction

skinparam backgroundColor #F9FAFB
skinparam defaultFontName Arial
skinparam defaultFontSize 13
skinparam class {
  BackgroundColor #E0F2FE
  BorderColor #0284C7
  ArrowColor #334155
  FontColor #0F172A
  FontStyle bold
}

' --- Couche Présentation (UI) ---
package "Couche Présentation (UI)" {
  class PageAccueil
  class PageAnnonce
  class PageProfil
}

' --- Couche Application (Services) ---
package "Couche Application (Services)" {
  class AnnonceService
  class ProfilService
  class EchangeService
  class NotificationService
}

' --- Couche Domaine (Modèles) ---
package "Couche Domaine (Modèles)" {
  class Utilisateur
  class Annonce
  class Competence
  class Echange
  class Notification
}

' --- Couche Infrastructure (API) ---
package "Couche Infrastructure (API)" {
  class ApiClient
  class WebSocketClient
}

' --- Relations entre couches ---
PageAccueil --> AnnonceService
PageAnnonce --> AnnonceService
PageAnnonce --> EchangeService
PageProfil --> ProfilService
PageProfil --> NotificationService

AnnonceService --> Annonce
ProfilService --> Utilisateur
EchangeService --> Echange
NotificationService --> Notification
EchangeService --> WebSocketClient

AnnonceService --> ApiClient
ProfilService --> ApiClient
EchangeService --> ApiClient
NotificationService --> ApiClient

@enduml
```

___
Version détaillé

```plantuml
@startuml
title Diagramme de classes du client (pattern en couches, vertical avec signatures)

top to bottom direction

skinparam backgroundColor #F9FAFB
skinparam defaultFontName Arial
skinparam defaultFontSize 13
skinparam class {
  BackgroundColor #E0F2FE
  BorderColor #0284C7
  ArrowColor #334155
  FontColor #0F172A
}

' --- Couche Présentation (UI) ---
package "Couche Présentation (UI)" {
  class PageAccueil {
    +afficherAnnonces(): void
    +naviguerVersProfil(): void
  }
  class PageAnnonce {
    +afficherDetailsAnnonce(idAnnonce: int): void
    +envoyerDemande(idAnnonce: int): void
  }
  class PageProfil {
    +afficherProfil(): void
    +modifierProfil(utilisateur: Utilisateur): void
  }
}

' --- Couche Application (Services) ---
package "Couche Application (Services)" {
  class AnnonceService {
    +creerAnnonce(annonce: Annonce): Annonce
    +rechercherAnnonce(motsCles: String): List<Annonce>
    +consulterAnnonce(id: int): Annonce
  }
  class ProfilService {
    +authentifier(email: String, motDePasse: String): Utilisateur
    +inscrire(utilisateur: Utilisateur): Utilisateur
    +mettreAJourProfil(utilisateur: Utilisateur): Utilisateur
  }
  class EchangeService {
    +envoyerDemande(echange: Echange): Echange
    +gererDemandes(idUtilisateur: int): List<Echange>
    +messagerie(idEchange: int): List<String>
  }
  class NotificationService {
    +recevoirNotifications(idUtilisateur: int): List<Notification>
  }
}

' --- Couche Domaine (Modèles) ---
package "Couche Domaine (Modèles)" {
  class Utilisateur {
    -id: int
    -nom: String
    -email: String
    -motDePasse: String
    +getProfil(): String
  }
  class Annonce {
    -id: int
    -titre: String
    -description: String
    -dateCreation: Date
    +getDetails(): String
  }
  class Competence {
    -id: int
    -nom: String
    -description: String
  }
  class Echange {
    -id: int
    -statut: String
    -date: Date
  }
  class Notification {
    -id: int
    -message: String
    -date: Date
  }
}

' --- Couche Infrastructure (API) ---
package "Couche Infrastructure (API)" {
  class ApiClient {
    +get(url: String): String
    +post(url: String, data: Object): String
    +put(url: String, data: Object): String
    +delete(url: String): String
  }
  class WebSocketClient {
    +connect(url: String): void
    +envoyerMessage(msg: String): void
    +recevoirMessage(): String
  }
}

' --- Relations entre couches ---
PageAccueil --> AnnonceService
PageAnnonce --> AnnonceService
PageAnnonce --> EchangeService
PageProfil --> ProfilService
PageProfil --> NotificationService

AnnonceService --> Annonce
ProfilService --> Utilisateur
EchangeService --> Echange
NotificationService --> Notification
EchangeService --> WebSocketClient

AnnonceService --> ApiClient
ProfilService --> ApiClient
EchangeService --> ApiClient
NotificationService --> ApiClient

@enduml
```