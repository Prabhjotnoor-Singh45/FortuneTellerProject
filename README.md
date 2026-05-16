# Final Project: Mystic Fortune Teller Application

### Author: Student
### Date: May 2026

---

## Program Description
The **Mystic Fortune Teller** is an interactive GUI Java application built using Swing. It utilizes a separate backend architectural layout to manage an `ArrayList` collection initialized with 10 classic text fortunes. Users can request a random calculation of their destiny, add custom assertions, preview indexed lists, and delete strings safely.

### Technical Elements Implemented:
* **Encapsulation & Architecture Separation:** GUI component modules do not perform array manipulation logic. 
* **Dynamic Arrays (`ArrayList`):** Used to handle growing and shrinking elements efficiently.
* **Exception Handling Blocks:** Comprehensive wrapping preventing dynamic runtime crashes:
    * `NumberFormatException` handled if the user inputs a word into the delete integer index input container.
    * `IndexOutOfBoundsException` caught gracefully when index lookup range fields fail.
    * `IllegalArgumentException` & `IllegalStateException` handled for empty submissions or structural depletion.
* **JavaDoc Compliance:** Clean API reference tagging throughout headers.

---

## Video Demonstration
A link to your testing video should be added here when uploaded to GitHub or hosted via an unlisted URL.
