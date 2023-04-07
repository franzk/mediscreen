# Mediscreen

**Mediscreen** est une _application Web_ dont le but est de détecter, au sein de la base de données, les patients à risque les plus exposés au diabète de type 2.

## Microservices
Le back office, créé avec _Spring Boot_ est divisée en plusieurs _microservices_ :   
- [mPatient](mPatient) : gère les données qui concerne l'identité des patients. 

## Installation 
- Cloner ce repository : git clone https://github.com/franzk/mediscreen.git
  

- Créer une base de données MySQL et adapter les fichiers de configuration :
  - [application-dev.properties](mPatient%2Fsrc%2Fmain%2Fresources%2Fapplication-dev.properties) (mPatient)
  - [application-test.properties](mPatient%2Fsrc%2Fmain%2Fresources%2Fapplication-test.properties) (mPatient)
  - [application.properties](mAuthentication%2Fsrc%2Fmain%2Fresources%2Fapplication.properties) (mAuthentication)
  

- Créer unhe base de données MongoDB et adapter les fichiers de configuration :
  - [application.properties](mNotes%2Fsrc%2Fmain%2Fresources%2Fapplication.properties) (mNotes)
  - [application.properties](mAssessment%2Fsrc%2Fmain%2Fresources%2Fapplication.properties) (mAssessment)

### /!\ Could Gateway
Lorsqu'un service est lancé ultérieurement au service mGateway, il faudra rafraichir les routes du gateway manuellement grâce à la commande :  
curl --location --request POST 'http://localhost:8080/actuator/gateway/refresh'