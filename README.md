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

## Working
1. Visitor logs in to the application with Google Sign-In Authentication provided by firebase.
2. If visitor has to start a new appointment , they will fill a form and details are sent to the host.
3. If there is a on going appointment for the current visitor , he/she gets am option to checkout of the appointment.

## Further improvement idea
1. There can be multiple hosts
2. Visitors can be mapped to host based on some unique host IDs.
3. Multiple appointments can be made by a single visitor.
