-- Create Database
CREATE DATABASE IF NOT EXISTS airlinemanagementsystem;
USE airlinemanagementsystem;

-- Create login table
CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

-- Create passenger table
CREATE TABLE IF NOT EXISTS passenger (
    name VARCHAR(100),
    nationality VARCHAR(50),
    phone VARCHAR(20),
    address VARCHAR(200),
    NID VARCHAR(20) PRIMARY KEY,
    gender VARCHAR(10)
);

-- Create flight table
CREATE TABLE IF NOT EXISTS flight (
    f_code VARCHAR(50) PRIMARY KEY,
    f_name VARCHAR(100),
    source VARCHAR(50),
    destination VARCHAR(50),
    departure_time TIME,
    arrival_time TIME,
    seats INT
);

-- Create reservation table
CREATE TABLE IF NOT EXISTS reservation (
    PNR VARCHAR(50) PRIMARY KEY,
    TIC VARCHAR(50),
    NID VARCHAR(20),
    name VARCHAR(100),
    nationality VARCHAR(50),
    flightname VARCHAR(100),
    flightcode VARCHAR(50),
    src VARCHAR(50),
    des VARCHAR(50),
    date VARCHAR(20),
    FOREIGN KEY (NID) REFERENCES passenger(NID)
);

-- Create cancel table
CREATE TABLE IF NOT EXISTS cancel (
    PNR VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100),
    cancellation_no VARCHAR(50),
    flightcode VARCHAR(50),
    date VARCHAR(20)
);

-- Insert test login credentials
INSERT INTO login VALUES 
('admin', 'admin123'),
('user', 'password'),
('test', 'test123');

-- Insert sample flights
INSERT INTO flight VALUES 
('AI001', 'Air India Express', 'Delhi', 'Mumbai', '08:00:00', '10:30:00', 150),
('AI002', 'Air India', 'Mumbai', 'Bangalore', '12:00:00', '14:15:00', 200),
('AI003', 'Air India', 'Delhi', 'Kolkata', '15:00:00', '17:45:00', 180),
('SG101', 'SpiceJet', 'Delhi', 'Chennai', '06:30:00', '09:45:00', 160),
('BA201', 'Biman Bangladesh', 'Dhaka', 'Chittagong', '09:00:00', '10:30:00', 140);

-- Insert sample passengers
INSERT INTO passenger VALUES 
('John Doe', 'Indian', '9876543210', '123 Main Street, Delhi', '123456789012', 'Male'),
('Jane Smith', 'Indian', '9876543211', '456 Oak Avenue, Mumbai', '123456789013', 'Female'),
('Rajesh Kumar', 'Indian', '9876543212', '789 Pine Road, Bangalore', '123456789014', 'Male'),
('Priya Sharma', 'Indian', '9876543213', '321 Elm Street, Kolkata', '123456789015', 'Female');

COMMIT;
