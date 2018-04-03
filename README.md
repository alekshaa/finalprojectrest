# REST API DOKUMENTAATIO

<b>RESTIN KÄYTTÖMAHDOLLISUUDET</b>

******************* TICKETS *******************

GET
/api/tickets
* listaa kaikki tiketit

/api/tickets/{courseId}
* listaa kaikki tiketit valitulla kurssiID:llä

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
* listaa kaikki tiketit

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



<b> OMINAISUUDET TULOSSA APIIN..</b>

OK tikettien listaus (kaikki, kurssi id:n perusteella)
OK tiketin lisäys
OK tiketin siirto pois aktiivisesta > muutos passiviksi PUT (poisto)

(* tiketin muokkaus)
* käyttäjän lisäys/muokkaus/poisto
* kurssin lisäys/muokkaus/poisto


<b>MIETITÄÄN NÄITÄ: </b>
* HASH / käyttöoikeudet API:iin
* Käyttäjän luominen MySQL:ään
* Websocket vs. REST pollaus

<b>LUEMUT REST käyttöön:</b>
MySQL: luo tietokanta komennolla: CREATE database ticketservice
IntelJ: vaihda resources > applicatio.properties > oman MySQL:n username & password + muista git IGNORE
