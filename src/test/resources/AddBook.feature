
Feature: As a librarian i should be able to create a new Book

  Background:
    Given Librarian is on the loginPage
    And Librarian enters valid email and password and click sign in.


  Scenario:As a librarian i should be able to create a new Book
    When Librarian clicks Books module
    And Librarian click “+ Add Book" button
    When Librarian enter bookName , ISBN , Year, Author and Description
    And librarian click save changes
    Then verify a new book is added