curl -d "family=Ferguson&given=Lucas&dob=1968-06-22&sex=M&address=2 Warren Street&phone=387-866-1399" -X POST http://localhost:8081/patient/add
curl -d "family=Rees&given=Pippa&dob=1952-09-27&sex=F&address=745 West Valley Farms Drive&phone=628-423-0993" -X POST http://localhost:8081/patient/add
curl -d "family=Arnold&given=Edward&dob=1952-11-11&sex=M&address=599 East Garden Ave&phone=123-727-2779" -X POST http://localhost:8081/patient/add
curl -d "family=Sharp&given=Anthony&dob=1946-11-26&sex=M&address=894 Hall Street&phone=451-761-8383" -X POST http://localhost:8081/patient/add
curl -d "family=Ince&given=Wendy&dob=1958-06-29&sex=F&address=4 Southampton Road&phone=802-911-9975" -X POST http://localhost:8081/patient/add
curl -d "family=Ross&given=Tracey&dob=1949-12-07&sex=F&address=40 Sulphur Springs Dr&phone=131-396-5049" -X POST http://localhost:8081/patient/add
curl -d "family=Wilson&given=Claire&dob=1966-12-31&sex=F&address=12 Cobblestone St&phone=300-452-1091" -X POST http://localhost:8081/patient/add
curl -d "family=Buckland&given=Max&dob=1945-06-24&sex=M&address=193 Vale St&phone=833-534-0864" -X POST http://localhost:8081/patient/add
curl -d "family=Clark&given=Natalie&dob=1964-06-18&sex=F&address=12 Beechwood Road&phone=241-467-9197" -X POST http://localhost:8081/patient/add
curl -d "family=Bailey&given=Piers&dob=1959-06-28&sex=M&address=1202 Bumble Dr&phone=747-815-0557" -X POST http://localhost:8081/patient/add

curl -d "patId=5¬e=Le patient déclare qu'il « se sent très bien ». Poids égal ou inférieur au poids recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=5¬e=Le patient déclare qu'il « se sent très bien ». Poids égal ou inférieur au poids recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=5¬e=Le patient déclare qu'il ne se sent pas si fatigué que ça. Fumeur, il a arrêté dans les 12 mois précédents. Tests de laboratoire indiquant que les anticorps sont élevés" -X POST http://localhost:8082/patHistory/add
curl -d "patId=6¬e=Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement" -X POST http://localhost:8082/patHistory/add
curl -d "patId=6¬e=Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois. Il remarque également que son audition continue d'être anormale" -X POST http://localhost:8082/patHistory/add
curl -d "patId=6¬e=Tests de laboratoire indiquant une microalbumine élevée" -X POST http://localhost:8082/patHistory/add
curl -d "patId=6¬e=Le patient déclare que tout semble aller bien.Le laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé. Le patient déclare qu’il fume depuis longtemps" -X POST http://localhost:8082/patHistory/add
curl -d "patId=7¬e=Le patient déclare qu'il fume depuis peu" -X POST http://localhost:8082/patHistory/add
curl -d "patId=7¬e=Tests de laboratoire indiquant une microalbumine élevée" -X POST http://localhost:8082/patHistory/add
curl -d "patId=7¬e=Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière. Il se plaint également de crises d’apnée respiratoire anormales. Tests de laboratoire indiquant un taux de cholestérol LDL élevé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=7¬e=Tests de laboratoire indiquant un taux de cholestérol LDL élevé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=8¬e=Le patient déclare qu'il lui est devenu difficile de monter les escaliers. Il se plaint également d’être essoufflé. Tests de laboratoire indiquant que les anticorps sont élevés. Réaction aux médicaments" -X POST http://localhost:8082/patHistory/add
curl -d "patId=8¬e=Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps" -X POST http://localhost:8082/patHistory/add
curl -d "patId=8¬e=Le patient déclare avoir commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=9¬e=Le patient déclare avoir des douleurs au cou occasionnellement. Le patient remarque également que certains aliments ont un goût différent. Réaction apparente aux médicaments. Poids corporel supérieur au poids recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=9¬e=Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite. Taille incluse dans la fourchette concernée" -X POST http://localhost:8082/patHistory/add
curl -d "patId=9¬e=Le patient déclare qu'il souffre encore de douleurs cervicales occasionnelles. Tests de laboratoire indiquant une microalbumine élevée. Fumeur, il a arrêté dans les 12 mois précédents" -X POST http://localhost:8082/patHistory/add
curl -d "patId=9¬e=Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite. Tests de laboratoire indiquant que les anticorps sont élevés" -X POST http://localhost:8082/patHistory/add
curl -d "patId=10¬e=Le patient déclare qu'il se sent bien. Poids corporel supérieur au poids recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=10¬e=Le patient déclare qu'il se sent bien" -X POST http://localhost:8082/patHistory/add
curl -d "patId=11¬e=Le patient déclare qu'il se réveille souvent avec une raideur articulaire. Il se plaint également de difficultés pour s’endormir. Poids corporel supérieur au poids recommandé. Tests de laboratoire indiquant un taux de cholestérol LDL élevé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=12¬e=Les tests de laboratoire indiquent que les anticorps sont élevés. Hémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=13¬e=Le patient déclare avoir de la difficulté à se concentrer sur ses devoirs scolaires. Hémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=13¬e=Le patient déclare qu'il s’impatiente facilement en cas d’attente prolongée. Il signale également que les produits du distributeur automatique ne sont pas bons. Tests de laboratoire signalant des taux anormaux de cellules sanguines" -X POST http://localhost:8082/patHistory/add
curl -d "patId=13¬e=Le patient signale qu'il est facilement irrité par des broutilles. Il déclare également que l'aspirateur des voisins fait trop de bruit. Tests de laboratoire indiquant que les anticorps sont élevés" -X POST http://localhost:8082/patHistory/add
curl -d "patId=14¬e=Le patient déclare qu'il n'a aucun problème" -X POST http://localhost:8082/patHistory/add
curl -d "patId=14¬e=Le patient déclare qu'il n'a aucun problème. Taille incluse dans la fourchette concernée. Hémoglobine A1C supérieure au niveau recommandé" -X POST http://localhost:8082/patHistory/add
curl -d "patId=14¬e=Le patient déclare qu'il n'a aucun problème. Poids corporel supérieur au poids recommandé. Le patient a signalé plusieurs épisodes de vertige depuis sa dernière visite" -X POST http://localhost:8082/patHistory/add
curl -d "patId=14¬e=Le patient déclare qu'il n'a aucun problème. Tests de laboratoire indiquant une microalbumine élevée" -X POST http://localhost:8082/patHistory/add
