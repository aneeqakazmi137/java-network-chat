# Socket-Based Network Communication System

This project is a professional graphical communication interface developed in Java, demonstrating real-time data exchange between a central Controller and a remote Compute Node.

## Technical Specifications

* **Socket Programming**: Implements TCP/IP protocols on port 5000 to establish reliable, bidirectional communication.
* **Multithreading**: Utilizes Java Threading to manage background network listening, ensuring the user interface remains responsive during active connections.
* **Java Swing Interface**: Features a custom-designed graphical user interface with a dark-themed aesthetic for improved readability and user experience.
* **Event-Driven Architecture**: Employs ActionListeners to handle real-time message broadcasting and status updates.

## Operating Instructions

1. **Initialize the Controller**: Execute `NetworkTool.java` to start the Server/Controller application.
2. **Connect the Node**: Execute `NetworkNode.java` to connect the Client/Node to the active Controller.
3. **Communication**: Once the connection is established, the "NODE CONNECTED" status will appear, and messages can be transmitted between the two interfaces.

## Project Structure

* **NetworkTool.java**: Acts as the Server/Controller, managing incoming connections and logging communication.
* **NetworkNode.java**: Acts as the Client/Node, connecting to the server to send and receive data.
