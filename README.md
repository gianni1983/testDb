# testDb
Test MagNews  
Creazione di un progetto per i DB Banchmarcks.

Per far partire il programma bisogna modificare il file di properties "config.properties", al suo interno bisogna inserire l' url del DB l' utente e la password per collegarsi ad esso, ed eventualmente i Driver se ci si vuole collegare a un db diverso da Postgres.

Nel file "Creazione DB.sql" sono presenti gli script per creare le tabelle che saranno utlizzate nell' applicativo.

Una volta lanciati gli script di creazione delle tabelle, basterà eseguire il main della classe "DBMain", i risultati saranno scritti sulla console.
