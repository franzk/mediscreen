# Microservice mPatient

Gère les données de base concernant l'identité des patients

## Installation
Ajouter les fichiers de proporiétés dans le dossier [src](src) > [main](src%2Fmain) > [resources](src%2Fmain%2Fresources) :  
- **application-prod.properties** 
- **application-test.properties**   
  <br>
_Chacun de ces fichiers doit contenir les champs suivants complétés :_  
  spring.datasource.url=  
  spring.datasource.username=  
  spring.datasource.password=
  <br><br>
  spring.jpa.hibernate.ddl-auto=

## Import de données
L'ajout de données se fait à l'aide de la commande curl en suivant ce format :  

curl -d "family=TestNone&given=Test&dob=1966-12-31&sex=F&address=1 Brookside St&phone=100-222-3333" -X POST http://localhost:8081/patient/add
