# Multi thread chat simulator in Java

This is a final project made in 2018 for my University's Object-Oriented Programming subject depeveloped by Lucia G. , Ezequiel W. and me. 
Basically, it's a multi thread chat simulator project made in Java. The main idea was work with the core concepts of OOP and Java language such as:

* Polymorphism
* Encapsulation
* Inheritance
* Abstraction
* Method overriding and Method overloading
* SOLID Design principles
* Design Patterns (GoF)
* Concurrency - Java Threads (Locks and Synchronization)
* Java Exceptions
* Java Serializer
* Java Swing and UI Layouts

We used design Patterns like Singleton, MVC (Model-View-Controller), Observer-Observable, Template Method, Decorator and Factory to solve different problems.


## Preview
![img](https://raw.githubusercontent.com/jngumy/chat-multi-thread-simulator/master/chat.png)

## Features
* Register 2 types of users: human or bot user.
* Bots can send messages to humans or other bots. Every user in the system is a Thread.
* Human User can broadcast a message (grupal chat) or send private message to other users. The messages are Question-Answer type.
* Every User has a zodiac element (Fire, Water, Air or Earth). Depending of the sign, the answers may vary (Double Dispath)
* Answer-Question persistent database (for the bots). Every time a bot receives a new answer, it will be stored on the database. A question can have
multiple answers, so the bot can decide for any of them.
* If a bot couldn't understand a question, it will ask to the user for its proper answer.

## Requeriments

Java Virtual Machine for Windows, Linux or MacOs.





