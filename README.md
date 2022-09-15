# Object-Oriented-Design-Parking_Lot
Aims at designing parking lot system having in mind some certain assumption


Let’s visualize the scenario
1.	Customer looks for green signal in parking display board to check the availability.
2.	If light is green, parking is available, otherwise not available.
3.	If parking is available, the customer will move towards the entry barrier where the automated parking system check for the availability/free slot and also captures the vehicle registration number through camera and then generates the ticket.
4.	What will the ticket contain?
5.	It contains several information like vehicle number, date and time of arrival and available parking slot number.
6.	After this, parking automated system then opens the entry barrier and update the status	 of parking display board.
7.	Customer move towards the assigned parking spot and parks the vehicle.
8.	When customer wants to exit, he moves towards the exit barrier.
9.	At exit barrier the customer scans the ticket on scanning the parking automated system calculates the parking charges using the entry exit timings and hourly charges and generates the bill.
10.	Now the exit barrier opens and the parking display board updates the status.


Here we are making few assumptions!!
Parking has single entry and single exit point.
Admin manages and configure the automated system.


![image](https://user-images.githubusercontent.com/79403322/190345741-8f947ac2-8571-4a6e-93cb-f55e8855afbd.png)

