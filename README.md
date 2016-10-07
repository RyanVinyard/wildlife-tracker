# _Ranger Pete Jones' Wildlife Tracker_

#### _An exercise in SQL, postgres, and Spark, October 2016_

#### By _**Ryan Vinyard**_

## Description

_This is a webapp that allows forest rangers to list instances of sightings of creatures in Sandalwoodshire Forest. Specifically, it allows you to denote whether or not they are endangered._

## Setup/Installation Requirements

* _Clone repo at https://github.com/RyanVinyard/hair-salon_
* _Run a postgres server by typing "postgres" into the terminal_
* _Start PSQL by typing PSQL in a separate terminal window_
* _In PSQL:_
* _CREATE DATABASE wildlife_tracker;_
* _Connect by typing "\c wildlife_tracker"_
* _In a new terminal window, run command "psql wildlife_tracker < media.sql"_
* _Back in PSQL, run "CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker_
* _In a separate terminal window, run gradle test to ensure complete functionality_
* _Gradle run and connect to http://localhost:4567_

## Known Bugs

_In the new animal form, sometimes clicking the box for animal age will not make the menu drop down. This may be due to the hidden endangered form. It does not break anything, and is harmless._

## Support and contact details

_Rav.ryanvinyard@gmail.com_

## Technologies Used

_Java, postgresql, SQL, Spark, Velocity_

### License

*Free use under MIT license*

Copyright (c) 2016 **_Ryan Vinyard_**
