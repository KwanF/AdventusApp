# My Personal Project

## Travel Destination Application

This application will allow users to:

- As a user, I want to add a city that I have visited to my collection
- As a user, for each city added, I also want to assign a rating from 1-5 star, and as well as its continent
- As a user, I want to view all the cities in my collection
- As a user, I want to view the cities by their continent
- As a user, I want to view the cities by their ratings
- As a user, I want to remove a city
- As a user, I want to be able to save my travel destination list to file (if I so choose)
- As a user, I want to be able to be able to load my travel destination from file (if I so choose)

This project is of interest to me because I think it would be convenient to have a central 
repository of all the destinations I have travelled to and a rating function, so that years later when a friend
asks me about my experience at a certain location, I won't forget the name and the details of my experiences.

# Instructions for Grader
- Start the GUI application by running AdventusAppUI (Main is for the console application)
- You can generate the first required action related to adding Xs to a Y (adding a city) by filling out the three 
  fields at the bottom, then clicking the "Add" button at the bottom of the panel to add the city
- You can generate the second required action related to adding Xs to a Y (removing a city) by selecting 
  a city entry with your mouse to the city you want to remove, then clicking the "Remove" button at the 
  bottom of the panel to remove a city. Remember to save so that the .JSON file gets updated as well!
- You can locate my visual component by running AdventusAppUI, as the image is a splash screen that shows as a 
  popup! The image is called "adventus-splash-screen.png" in the file explorer
- You can save the state of my application by clicking the "Save" button at the bottom of the panel
- You can reload the state of my application by clicking the "Load" button at the bottom of the panel

# Phase 4: Task 3
Below are some refactoring that I could do:
- Use the Observer pattern to implement the GUI and the model, where the GUI is the subject, and the model is the 
  observer. Whenever the GUI changes state based on an ActionListener, the model is automatically notified and updated.
- Apply the composite pattern, where DestinationList is the composite, City is the leaf, and a new abstract class or 
  interface will need to be created to be the component.
- Apply the composite pattern to the GUI, where the individual Jbuttons and text forms/fields are the leaf, the 
  JFrame is the composite, and a new abstract class or interface will need to be created to be the component.
- Implement the Singleton pattern for DestinationList, to ensure that there can only be one Destinationlist at all 
  times. 
- Increase the cohesion for the AdeventusAppUI class, as methods like AddCityListener and RemoveCityListener should 
   be a separate class