
## GET

Il metodo GET restituisce tutti i record nella tabella dei contatti. Puoi specificare il formato di risposta desiderato utilizzando il parametro "?format". Se imposti "?format=json", riceverai la risposta in formato JSON. Se imposti "?format=xml", riceverai la risposta in formato XML.
Se il parametro format non viene utilizzato il formato di base sarà JSON.
Esempio di richiesta:
```
GET /RubricAPI/api?format=xml
```
  

## POST

Il metodo POST accetta dati in formato JSON, XML o x-www-form-urlencoded nel corpo della richiesta e inserisce un nuovo record nel database dei contatti se i dati forniti sono validi.
```
POST /RubricAPI/api
 
Content-Type: application/json 

{ 
	"name": "Mario", 
	"surname": "Rossi", 
	"prefix": "39", 
	"number": "1234567890" 
}
```
  

## PUT

Il metodo PUT accetta dati in formato JSON o XML nel corpo della richiesta e aggiorna un record esistente nel database dei contatti in base all'ID specificato nei parametri dell'URL.
```
PUT /RubricAPI/api?id=1 

Content-Type: application/xml 

<contact>  
	<name>Mario</name>
	<surname>Rossi</surname>
	<prefix>39</prefix>
	<number>1234567890</number>
</contact>
```
  

## DELETE

Il metodo DELETE elimina un record dal database dei contatti in base all'ID specificato nei parametri dell'URL.

```
DELETE /RubricAPI/api?id=1
```