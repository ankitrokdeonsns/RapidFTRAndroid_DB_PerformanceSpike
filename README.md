RapidFTRAndroid_DB_PerformanceSpike
===================================

Development Environment : 
MacOSX 10.7.4 (x86_64)

Android Emulator Configuration:
Version : Android 2.2 (API Level 8) 
SDCard (not present)

________________________________________________


The Performance timing has been measured in ms for 500 records (rows in the database).
The performance is somewhat directly proportional to the number of records handled in a DB transaction.

			SQLCipher	SQLite  	

Insert		~ 4200		~ 2700		

Select		~ 490		~ 30



