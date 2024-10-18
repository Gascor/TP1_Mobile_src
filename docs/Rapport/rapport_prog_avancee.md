
### Université de Versailles Saint-Quentin-en-Yvelines

### **IUT DE VELIZY, Département Informatique**

**Auteur :** `Lucas DA SILVA FERREIRA`  
**Classe :** `INFO3 - FI`  
**Date :** `18 Octobre 2024`  
**Heure de finalisation :** `03:20`

# Compte Rendu - Développement Avancé

## Sommaire

- [1. Introduction](#introduction)
- [2. Architecture Matérielle](#architecture-matérielle)
  - [2.1. Comparaison des Configurations Matérielles](#comparaison-des-configurations-matérielles)
- [3. TP 1 - Conception et Threads](#tp-1)
  - [3.1. Diagramme de Classe](#diagramme-de-classe)
  - [3.2. Les Classes et leurs Rôles](#les-classes-et-leurs-rôles)
  - [3.3. Cycle de Vie des Threads](#cycle-de-vie-des-threads)
- [4. TP 2 - Problèmes de Synchronisation](#tp-2)
  - [4.1. Accès Concurrent](#accès-concurrent)
  - [4.2. Exclusion Mutuelle et Problèmes d'Affichage](#exclusion-mutuelle-et-problèmes-daffichage)
- [5. TP 3 - Producteur-Consommateur](#tp-3)
  - [5.1. Diagramme de Classe](#tp3-diagramme-de-classe)
  - [5.2. Explication du Problème](#explication-du-problème)
- [6. Conclusion](#conclusion)

---

## <a id="introduction"/>1. Introduction 

Dans le cadre du cours de **Programmation Avancée**, plusieurs travaux pratiques (TP) ont été réalisés pour approfondir la compréhension des concepts comme les **threads**, la **synchronisation** et la **programmation concurrente** en Java. Ce rapport couvre trois TP principaux : la conception et l'utilisation des threads, les problèmes d'accès concurrent, ainsi que la mise en place d'un modèle **Producteur-Consommateur**.

## <a id="architecture-matérielle"/>2. Architecture Matérielle

### <a id="comparaison-des-configurations-matérielles"/>Comparaison des Configurations Matérielles

|            | G25                                                         | I21                                                         | G24                                                         | OnePlus 9                                                   |
|------------|--------------------------------------------------------------|--------------------------------------------------------------|--------------------------------------------------------------|--------------------------------------------------------------|
| **CPU**    | i7 4790, 4c/8t, 3.6GHz, 4.0GHz Turbo, 64-bit                 | i7 4790, 4c/8t, 3.6GHz, 4.0GHz Turbo, 64-bit                 | i7-12700T, 12c/20t (8p-core, 4e-core), 1.4GHz - 4.7GHz Turbo, 64-bit | Qualcomm SM8350 Snapdragon 888 5G (5 nm), Octa-core (1x2.84 GHz Cortex-X1 & 3x2.42 GHz Cortex-A78 & 4x1.80 GHz Cortex-A55) |
| **RAM**    | 8Go RAM Micron MT16KTF1G64AZ-1G6E1 DDR3 PC3L-12800U 2Rx8 1600MHz 1.35v CL11 | 2x8Go RAM Micron MT16KTF1G64AZ-1G6E1 DDR3 PC3L-12800U 2Rx8 1600MHz 1.35v CL11 | 2x32Go RAM Micron MTC16G2085S1SC-48BA1 SODIMM-DDR5 PC5-38400 2Rx8 4800MHz 1.1v CL40 | 12Go RAM LPDDR5-6400 Bus mémoire 64 bits Bande passante de la mémoire 51,2 GB/s |
| **GPU**    | eGPU Intel, Intel® HD Graphics 4600, Mémoire vidéo maxi du sous-ensemble graphique 2Go RAM for VRAM | eGPU Intel, Intel® HD Graphics 4600, Mémoire vidéo maxi du sous-ensemble graphique 2Go RAM for VRAM | eGPU Intel, Intel® HD Graphics 770, 32 cores, 300MHz - 1.5GHz Turbo | Adreno 660, 5nm, 792 MHz, 905 MHz Max |
| **Disque** | LITEONIT LCS-256L9S-11 256Go, 2.5", 7mm SATA 6Gb/s           | LITEONIT LCS-256L9S-11 256Go, 2.5", 7mm SATA 6Gb/s           | SOLIDIGM SSDPFKNU512GZ 512Go M.2 NVMe PCIe                   | 256Go UFS 3.0                                               |
| **Type de disque** | SSD                                                  | SSD                                                          | SSD                                                          | SSD                                                          |
| **Fichier de pagination** | Oui                                           | Oui                                                          | Oui                                                          | Non                                                          |
| **Fichier d’échange** | Oui                                               | Oui                                                          | Oui                                                          | Non                                                          |

Ces configurations matérielles ont été utilisées pour tester les différents TP (sauf sur smartphone). Il est important de noter que les performances des threads peuvent varier en fonction de la capacité de traitement parallèle de chaque machine, ce qui influence la rapidité et la fluidité de l'exécution des programmes.

On peut également souligner les différents moyens d'obtenir des informations détaillées sur le matériel des machines. Parmi les outils les plus courants, on trouve :

- **Msconfig** : un utilitaire Windows qui permet de visualiser et configurer le démarrage et certains aspects de la configuration matérielle.
- **Gestionnaire de tâches** : utile pour vérifier l'utilisation des ressources en temps réel, notamment l'usage du processeur, de la mémoire et du disque.
- **Commandes CMD** : Windows offre plusieurs commandes en ligne permettant d'obtenir des informations spécifiques sur le matériel. Par exemple, la commande `wmic memorychip get serialnumber` permet de récupérer le numéro de série de la mémoire vive (RAM), offrant ainsi plus de détails sur sa configuration.


## <a id="tp-1"/>3. TP 1

### <a id="diagramme-de-classe"/>3.1. Diagramme de Classe

![Diagramme TP1](conception.png)

### <a id="les-classes-et-leurs-rôles"/>3.2. Les Classes et leurs Rôles

#### 3.2.1. **UneFenetre**

`UneFenetre` est l'interface principale qui gère l'affichage de l'application. Elle hérite de `JFrame`, une classe utilisée pour la création de fenêtres graphiques en Java.

#### 3.2.2. **UnMobile**

La classe `UnMobile` représente un objet mobile graphique. Son rôle est de se déplacer sur l'écran selon les paramètres définis. Il contient des méthodes pour gérer son mouvement (`run()`) et son affichage (`paintComponent()`).

#### 3.2.3. **Thread et Runnable**

L'utilisation de threads dans cette application permet de déplacer `UnMobile` de manière asynchrone, en parallèle avec l'interface graphique. En héritant de l'interface `Runnable`, `UnMobile` peut être exécuté dans un thread indépendant.

#### 3.2.4. **TpMobile**

Classe contenant la méthode `main()`, elle sert de point de départ pour lancer l'application et initialiser les composants.

### <a id="cycle-de-vie-des-threads"/>3.3. Cycle de Vie des Threads

Un **thread** passe par plusieurs états :
1. **Nouveau** : Le thread est créé mais pas encore démarré.
2. **Prêt** : Le thread est prêt à être exécuté.
3. **En cours d'exécution** : Le thread est actuellement exécuté par le processeur.
4. **Bloqué** : Le thread attend une ressource.
5. **Terminé** : Le thread a terminé d'exécuter sa méthode `run()`.

L'OS (Système d'exploitation) gère la distribution des threads entre les cœurs du processeur, assurant une exécution concurrente fluide. Toutefois, il est essentiel de bien synchroniser les threads lorsqu'ils accèdent à des ressources partagées.

## <a id="tp-2"/>4. TP 2

### <a id="accès-concurrent"/>4.1. Accès Concurrent

Dans ce TP, nous avons exploré les problèmes d'accès concurrent entre plusieurs threads, notamment lorsqu'ils tentent d'accéder à une même ressource (comme une zone mémoire ou une variable partagée). Cela peut entraîner des conflits ou des erreurs si la synchronisation n'est pas gérée correctement.

### <a id="exclusion-mutuelle-et-problèmes-daffichage"/>4.2. Exclusion Mutuelle et Problèmes d'Affichage

Lorsque plusieurs threads accèdent aux mêmes ressources (comme `System.out` dans TP2), ils peuvent entrer en conflit. C'est ce qu'on appelle une **condition de course**. Pour éviter cela, il est nécessaire d'utiliser des mécanismes de **synchronisation**.

En Java, l'exclusion mutuelle peut être gérée par le mot-clé `synchronized` ou par des **verrous** comme les objets `Lock`. Cela permet de protéger les **sections critiques**, où l'accès concurrent pourrait entraîner des incohérences dans les données.

### Exclusion Mutuelle

L’**exclusion mutuelle** est un principe qui garantit qu’un seul thread peut accéder à une **section critique** à un moment donné. Cela empêche les **conditions de course** dans le cas d’accès concurrents à des ressources partagées.

Les **sémaphores** et **mutex** sont des mécanismes qui permettent de réguler cet accès :
- `wait()` force un thread à attendre qu'une ressource soit disponible.
- `notify()` réveille un thread en attente lorsque la ressource devient libre.

---

## <a id="tp-3"/>5. TP 3

### <a id="tp3-diagramme-de-classe"/>5.1. Diagramme de Classe

![Diagramme TP3](BalConception.png)

### <a id="explication-du-problème"/>5.2. Explication du Problème

Dans ce TP, nous avons implémenté un modèle **Producteur-Consommateur** en Java. L'idée était de simuler un producteur qui insère des lettres dans une boîte aux lettres (BAL) et un consommateur qui les retire de façon asynchrone. Les deux threads doivent fonctionner sans interférence, en utilisant une structure synchronisée pour éviter que l'un accède à la BAL pendant que l'autre l'utilise.

La **BAL** agit comme un tampon entre les deux threads. Le producteur utilise la méthode `deposerLettre()` pour insérer une lettre, tandis que le consommateur utilise `retirerLettre()` pour la récupérer. La synchronisation est cruciale ici pour éviter des conflits d'accès simultané à la BAL.

---

### <a id="conclusion"/>6. Conclusion

Jusqu'à maintenant, le cours de **Programmation Avancée** nous a permis d'explorer en profondeur la gestion des threads et des processus en Java. Les trois TP réalisés ont démontré l'importance de la **synchronisation** et de la gestion des ressources partagées. Grâce à ces travaux pratiques, j'ai acquis une solide compréhension des mécanismes sous-jacents à la programmation concurrente, et je comprends désormais mieux l'importance de l'architecture matérielle sur l'exécution des programmes multithreadés.

---

Ce rapport présente les concepts étudiés tout en mettant en avant les différentes problématiques rencontrées et les solutions mises en œuvre pour les résoudre.

---

> **Liens de Navigation**
>
> [Retour en haut](#compte-rendu---développement-avancé)
>
> [Voir le Diagramme de Classe du TP1](#diagramme-de-classe)
>
> [Voir le Diagramme de Classe du TP3](#tp3-diagramme-de-classe)

