# ToMeOrToYou
A simple message Rest Api woking on SpringBoot

## What am I looking at? 🤨
ToMeOrToYou has been created as a way to learn the SpringBoot Framework, so if you encounter a bug or simply you wish to contribute, I'll be very happy to have your feedback.
Some of the controller methods may not seem very "apropiate", but right now they are used for testing purposes.

### Well, then, how does it work? ✏
It is a REST API that holds Users and conversations between them, storing them in a MongoDB Database.

By now a Conversation can only be created for only two users but check the roadmap to see the future applications of this app.

## Roadmap🚗
Yes, this isn't the final product.

- [x] Create a basic Message Api
- [ ] Add JUnit tests
- [ ] Add User autentication through OAuth or similar
- [ ] Single use tokens for the logged user
- [ ] Group Chats
- [ ] Add images or attachments
- [ ] Deploy it into a WebServer
- [ ] Create a frontend App
- [ ] Find a way to add WebSockets
  
### Test it at home
To run the Project you will need to have installed or be able to run:
  - MongoDB 4.4.1
  - OpenJDK 14.0.2
  - Maven

Simply download the project, compile it, and run it!
And there you have it, your very own bootleg message application.

Coming with the repo, there is a file called [Tome Or To You.postman_collection.json](https://github.com/bocdagla/ToMeOrToYou/blob/main/Tome%20Or%20To%20You.postman_collection.json), using postman you can test the API if you have been able to install the Project
