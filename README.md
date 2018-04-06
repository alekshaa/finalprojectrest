# REST API DOKUMENTAATIO

<b>RESTIN KÄYTTÖMAHDOLLISUUDET</b>

******************* TICKETS *******************

GET
/api/tickets
* listaa kaikki tiketit
aktiivisen tiketin (vanhin kurssin tiketti) päivittyy tätä haettaessa

/api/tickets/{courseId}
* listaa kaikki tiketit valitulla kurssiID:llä
aktiivisen tiketin (vanhin kurssin tiketti) päivittyy tätä haettaessa

POST
/api/tickets/createticket
* uuden tiketin luonti (title, description, location)
* käyttäjä ja kurssi pitää tulla lomakkeelta Reactista (miten??)

PUT
/api/setpassive/{ticketId}
* asettaa tiketin passiiviseksi. Eli tämä toiminto kun tiketti "poistetaan" / kun on ratkaistu

******************* USERS *******************

GET
/api/users
* listaa kaikki käyttäjät

POST
/api/users/createuser
* uuden käyttäjän luonti (userName)
* mten yhdistetään käyttäjän luontiin??

*******************COURSES *******************

GET
/api/courses
* listaa kaikki kurssit

POST
/api/courses/createcourse
* uuden kurssin luonti (courseName)


<b>LUEMUT REST käyttöön:</b>

MySQL: luo tietokanta komennolla: CREATE database ticketservice
IntelJ: vaihda resources > applicatio.properties > oman MySQL:n username & password + muista git IGNORE


<b>MIETITÄÄN NÄITÄ: </b>
* HASH / käyttöoikeudet API:iin
* Käyttäjän luominen MySQL:ään
* Websocket vs. REST pollaus
* virhekäsittelyt

