The application server enables management of books, authors and lending lists of books (for example like in a library). The web Interface (API), and also the objects that are used to communicate with the outside world are defined in the specification and must be used to communicate through the web services. Outside the web interface you can use any other objects according to your design, if you deem it appropriate.

The web interface specification (API) that the application has to provide can be found here: https://app.swaggerhub.com/apis-docs/stuba-fei-uim-oop/OOPZadanie3Knihy/1.0.0-oas3

If the specification states that a 404 code should be returned, and the description does not say when, it is necessary to return it, if the given ID does not exist in the system.

System description
A detailed description of each operation is given in the API specification.

The system allows the addition, removal and modification of books in a library, including the change of an author (a book can have only one author), as well as increasing the amount of available books.

The system allows the creation and deletion of lists of lent books. Books can be added and removed from these lists (the same list can contain the same book at most once). The system allows lending of such lists, lending a lists increments lending count for each book on the list. Adding additional books to lists that are already lent out is not allowed.
