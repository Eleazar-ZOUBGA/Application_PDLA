Lorsqu'on rajoute une nouvelle dépendance maven, faire "mvn clean install"
pour installer les nouvelles dépendances.

Afin de démarrer, suivez les étapes ci-dessous
1. Compiler le code:  
   Exécutez la commande `mvn compile` pour compiler le code source de l'application. Si le code contient des erreurs, elles seront signalées à cette étape. Assurez-vous que la compilation se termine sans erreurs avant de continuer.

2. Générer le package :  
   Exécutez la commande `mvn package` pour générer un fichier exécutable . Ce fichier se trouve généralement dans le dossier `target`. Il s'agit de l'application prête à être exécutée.

3. Tester  :  
   Exécutez la commande `mvn test` pour lancer les tests automatisés inclus dans le projet. Cette étape permet de vérifier que toutes les fonctionnalités principales fonctionnent correctement. Si cette étape est ignorée, vous pouvez passer directement à l'exécution de l'application.

4. Exécuter :  
   Exécutez la commande `mvn exec:java` pour lancer l'application. Après son exécution, des instructions s'afficheront dans le terminal pour interagir avec l'application. Suivez attentivement ces indications pour utiliser l'application correctement.


Il y a plusieurs fichiers ".jar" parce que nous nous sommes rendus compte que le projet refusait de rajouter les dépendances
liées à la bdd.
Nous avons donc rajouté indépendamment l'accès la bdd. D'où le fait qu'il y ait plusieurs fichiers ".jar"
 