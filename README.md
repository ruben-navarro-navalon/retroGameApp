# retroGameApp
A web app to track, buy or sell retro games.

# Description
retroGameApp allows users to signup and login. Once logged in, users can search for retro games from old systems and keep an organized list. Users can:

- Search games by name or by platform.
- Add titles to their collection.
- Add titles to their "I want" list.
- Add titles from collection to their "I sell" list.
- Look for people who sells the games that user wants, or who buys the games that user sells.
- Send email to users who buys/sells the games that user sells/buys.
- Once buyed, users can move the game from the "I want" list to the collection.
- Once sold, users can remove the game from the "I sell" and collection lists at once.

# Installation
retroGameApp uses two external APIs, one email account and two mySQL databases. First of all, you have to obtain keys for the APIs, an email account and have a user on your SQL server.

## External APIs:
### GeoApi España
retroGameApp uses [GeoApi España](https://geoapi.es/registro) to retrieve geographical info. You have to register in the web, so you will obtain the API key. Once you have the API key, you have to put it the "key" field of file: ```frontend/src/app/services/geo-api.service.ts```
### RAWG
retroGameApp uses [RAWG API](https://rawg.io/signup) to retrieve games info. You have to register in the web, so you will obtain the API key. Once you have the API key, you have to put it in the "key" field of file:```frontend/src/app/services/rawg-api.service.ts```

## Mail account:
retroGameApp can use an email account to send emails at register and when looking for people who sells/buys games. You can follow [these steps](https://www.lifewire.com/get-a-password-to-access-gmail-by-pop-imap-2-1171882) to retrieve an "app password" from your email account.
Once you have an email "app password" you have to put your credentials in the "spring.mail.username" and "spring.mail.password" fields in the file: ```backend/email/src/main/resources/application.properties```

## SQL Databases:
retroGammApp uses two databases that have to exist in your mySQL server. Simply add permissions to your sql user and run the provided sql files in ```sql/```
Once done, you have to add your credentials in:
```backend/users/src/main/resources/application.properties``` and ```backend/usercatalog/src/main/resources/application.properties```

# Usage
Once setted up, you have to run all the springboot microservices of ```backend/``` and the angular project of ```frontend/```
Then, open your favourite web browser and go to http://localhost:4200/
