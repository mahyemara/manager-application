# A Manager Application

## For Pet Owners

This application manages the important health records of any owner's pet/s. It can be used by any pet owner that would
 like to store their pets' health information in one place!

As the owner of a cat, I understand the importance of having a convenient record of my cat's important health history
in one place, especially because my cat has a chronic illness. So, when I visit a vet, I would like to be able to
access my cat's important health information easily!
   

## User Story   
   
- As a user, I want to be able to add a new animal to my list of pets.
- As a user, I want to be able to view all my animals' basic information (like name and age) and health information
(like health insurance type and list of vaccines taken).
- As a user, I want to be able to remove an animal from my list of animals.
- As a user, I want to be able to select an animal and update their information, like their age and/or health 
insurance type. 
- As a user, I want to be able to save my pets list to file
- As a user, I want to be able to be able to load my pets list from file 


## Phase 4: Task 2
 - Test and design a class in your model package that is robust: 
 Class and method that have a robust design: Animal class, updateHealthInsurance method.
 
## Phase 4: Task 3
 - Remove assocation between PetOwnerGUI and LoadTab, so that all Tabs
 extend Tab, and then Tab class alone would associate with PetOwnerGUI. 
 - I would put the method called nextInput that belongs to the Add Class in another class called Input. 
 That is because Remove and Update are associated with the Add Class, and it would be better
 if all three (Add, Update, Remove) were associated with an Input class instead, given that the Remove 
 and Update classes do not actually need the function of adding new pets to the list of pets. 
 - 
 