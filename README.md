# EMS - Entry Management System
SDE Intern Assignment for Innovaccer summergeeks 2020. 

## Technology Used
1. Android
2. Kotlin
3. XML
4. Firebase

## Assumptions
1. There is a single host.
2. There can be multiple visitors.
2. One visitor can plan only one visit until he/she checks out of the current visit.
3. Host Name and address visited details are fixed.

## How to use

### A working internet connection is must for running the app.

#### Changes to be made - 
1. Go to EntryDetails.kt  
2. Find the comment that says "Add sender and receivers credentials". The similar comment will be at 2 places.
3. Change the sample to email and passwords to a working email credentials for both sender as well the receipent.
4. After making above mentioned changes , app will give the required functionality.

#### Follow the Working section to see the functionality of the app.


## Screenshots
<p>
  <img src="https://github.com/anshulkhattar/EMS/blob/master/ss/Screenshot_20191130-141813_EMS.jpg" width="250" height="500" />
  <img src="https://github.com/anshulkhattar/EMS/blob/master/ss/Screenshot_20191130-141836_EMS.jpg" width="250" height="500" />
  <img src="https://github.com/anshulkhattar/EMS/blob/master/ss/Screenshot_20191130-141925_EMS.jpg" width="250" height="500" />
</p>

## Working
1. Visitor logs in to the application with Google Sign-In Authentication provided by firebase.
2. If visitor has to start a new appointment , they will fill a form and details are sent to the host.
3. If there is a on going appointment for the current visitor , he/she gets am option to checkout of the appointment.

## Further improvement idea
1. There can be multiple hosts
2. Visitors can be mapped to host based on some unique host IDs.
3. Multiple appointments can be made by a single visitor.
