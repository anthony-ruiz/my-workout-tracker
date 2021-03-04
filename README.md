# my-workout-tracker


This is an Android application written in Kotlin. it allows a user to create a workout and track their different workouts. The User has the ability to choose from a list of exercises and input the weight used and reps performed on each of their Sets. It was developed using a MVVM design pattern. It utilizes liteSQL and Room as the persistence Later. Taking advantage of two-way data binding and safeArgs in order to update and store the data. 


When a user is going to start a workout they are presented with the following screen:

![picture alt](https://i.imgur.com/2cIknoq.png)



After they choose to add a new exercise they are presented with a list of possible exercises to track.

![picture alt](https://i.imgur.com/66dGReS.png)


After an exercise is selected it is passed to their current workout where they can see all of the exercises that they have done for this workout and they can update the reps and weight used for each of the exercises. \

![picture alt](https://i.imgur.com/LWJt1zW.png)


##Tools Used:


* Kotlin
* SafeArgs
* Databinding
* Room
* LiteSQL
* Recycler View 
