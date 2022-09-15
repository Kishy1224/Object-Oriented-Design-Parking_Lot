package com.parking;

import java.util.*;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

public class OOD_parking {

    public class ParkingLot {
        private String name;
        private int capacity;
        private String Location;

        public double getParkingCharges() {
            return parkingCharges;
        }

        private double parkingCharges;



        private Admin admin;

        private AutomatedSystem automatedSystem;
        private ArrayList<ParkingSpot> parkingSpots;
        private ArrayList<ParkingSpot> availableSpots;
        private DisplayBoard displayBoard;



        public ParkingLot(String name, int capacity, String location, double parkingCharges, AutomatedSystem automatedSystem, DisplayBoard displayBoard) {
            this.name = name;
            this.capacity = capacity;
            Location = location;
            this.parkingCharges = parkingCharges;
            this.automatedSystem = automatedSystem;
            this.automatedSystem.setParkingLot(this);
            this.displayBoard = displayBoard;
            this.parkingSpots = createParkingSpots(capacity);
            availableSpots = (ArrayList<ParkingSpot>) parkingSpots.clone();
        }
        private ArrayList<ParkingSpot> createParkingSpots(int capacity){
            ArrayList<ParkingSpot> tempList = new ArrayList<>();
            for (int i = 0; i <capacity ; i++) {
                ParkingSpot temp = new ParkingSpot("MPL "+i);
                tempList.add(temp);
            }
            return tempList;
        }

        public DisplayBoard getDisplayBoard() {
            return displayBoard;
        }

        public ParkingSpot getAvailableSpot() {
            return availableSpots.remove(0);
        }

        public int getAvailability(){
            return availableSpots.size();
        }

        public void updateParkingCharges(double newCharges){
            this.parkingCharges = newCharges;
        }
        public void setAdmin(Admin admin) {
            this.admin = admin;
        }

        public void main(String[] args) {
            DisplayBoard displayBoard = new DisplayBoard();
            AutomatedSystem automatedSystem = new AutomatedSystem(1);
            ParkingLot myParkingLot = new ParkingLot("MyParkingLot",100, "Sec-135,Noida",50,automatedSystem,displayBoard);

            // Checking the availability of this parking Lot
            int availavility = myParkingLot.getAvailability();
            System.out.println(availavility);

            // Creating Customer
            Customer piyush = new Customer("Piyush","UP85 AX 5454");

            // Automated System Generating Ticket
            Ticket piyushTicket = myParkingLot.automatedSystem.generateTicket(piyush);

            // Printing Ticket Info
            System.out.println(piyushTicket);

            // Creating Customer
            Customer ayush = new Customer("Ayush","UP86 AB 9999");

            // Automated System Generating Ticket
            Ticket ayushTicket = myParkingLot.automatedSystem.generateTicket(ayush);

            // Printing Ticket Info
            System.out.println(ayushTicket);

        }

    }


    public class AutomatedSystem {
        private int id;



        private ParkingLot parkingLot;
        HashMap<Integer,Ticket> currentTickets;

        public AutomatedSystem(int id){
            this.id = id;
            currentTickets = new HashMap<>();
        }

        public ParkingLot getParkingLot() {
            return parkingLot;
        }

        public void setParkingLot(ParkingLot parkingLot) {
            this.parkingLot = parkingLot;
        }
        private ParkingSpot fetchAvailableSpot(){
            return this.parkingLot.getAvailableSpot();
        }

        public Ticket generateTicket(Customer customer){
            ParkingSpot availableSpot = fetchAvailableSpot();
            Vehicle vehicle = customer.getVehicle();
            Ticket ticket = new Ticket(vehicle,availableSpot);
            currentTickets.put(ticket.getId(),ticket);
            return ticket;
        }
        public Ticket scanTicket(){
            // Code for scanning the ticket and return ticketId
            // Here we are assuming that scanned ticket id is 1234
            return currentTickets.get(1234);
        }

        public double caluclateCharges(){
            Ticket ticket= scanTicket();
            long duration = Duration.between(LocalDateTime.now(),ticket.getArrivalTime()).toHours();
            double charges = duration * parkingLot.getParkingCharges();
            return charges;
        }

        public void openEntryBarrier(){
            // Code for opening Entry Barrier
            this.parkingLot.getDisplayBoard().update(Status.FULL);
        }
        public void closeEntryBarrier(){
            // Code for closing Entry Barrier
        }
        public void openExitBarrier(){
            // Code for opening Entry Barrier
            this.parkingLot.getDisplayBoard().update(Status.AVAILABLE);
        }
        public void closeExitBarrier(){
            // Code for closing Entry Barrier
        }

    }



    public class Customer {
        private String name;
        private Vehicle vehicle;

        public Customer(String name, String vehicleNumber) {
            this.name = name;
            this.vehicle = new Vehicle(vehicleNumber);
        }

        public Vehicle getVehicle() {
            return vehicle;
        }
    }



    public class Admin {
        String name;

    }


    public enum Status {
        AVAILABLE, FULL;
    }

    public class DisplayBoard {
        public Status status;

        public DisplayBoard() {
            this.status = Status.AVAILABLE;
        }

        public void update(Status newStatus){
            this.status = newStatus;
        }
    }


    public class ParkingSpot {
        String spotNumber;

        public ParkingSpot(String spotNumber) {
            this.spotNumber = spotNumber;
        }

        public String getSpotNumber() {
            return spotNumber;
        }
    }


    public class Ticket {
        static int idCounter=0;
        private int id;
        private String vehicleRegistrationNumber;


        private LocalDateTime arrivalTime;
        private String parkingSpotNumber;

        public int getId() {
            return id;
        }

        public Ticket (Vehicle vehicle, ParkingSpot availableSpot) {
            this.id = ++idCounter;
            this.vehicleRegistrationNumber = vehicle.getVehicleNumber();
            this.parkingSpotNumber = availableSpot.getSpotNumber();
            this.arrivalTime = LocalDateTime.now();
        }

        public LocalDateTime getArrivalTime() {
            return arrivalTime;
        }

        @Override
        public String toString() {
            return "Ticket{" +
                    "id=" + id +
                    ", vehicleRegistrationNumber='" + vehicleRegistrationNumber + '\'' +
                    ", arrivalTime=" + arrivalTime +
                    ", parkingSpotNumber='" + parkingSpotNumber + '\'' +
                    '}';
        }
    }


    public class Vehicle {
        String vehicleNumber;

        public Vehicle(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }
    }

}
