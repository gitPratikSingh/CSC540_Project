create or replace PROCEDURE CREATE_ALL_TABLES AUTHID CURRENT_USER AS 
BEGIN          
        EXECUTE IMMEDIATE 
        'CREATE TABLE PSINGH22.Service_Center
            (
                service_center_id INTEGER,
                telephone VARCHAR(10),
                name VARCHAR(50),
                address VARCHAR(100),
                PRIMARY KEY(service_center_id )
            )';
            
        EXECUTE IMMEDIATE 
        'CREATE TABLE Invoice
        (
            invoice_id  INTEGER,
            price FLOAT,
            PRIMARY KEY(invoice_id)
        )';
        
        
        
EXECUTE IMMEDIATE 
        '        
        CREATE TABLE Parts
        (   part_id INTEGER NOT NULL PRIMARY KEY,  
            part_name VARCHAR(50),
            unit_price VARCHAR(50)
        )';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Payroll
        (
            payroll_id VARCHAR(50),
            payroll_type VARCHAR(50),
            PRIMARY KEY(payroll_id)
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Monthly_Payroll
        (
            payroll_id VARCHAR(50),
            month VARCHAR(50),
            monthly_salary INTEGER,
            PRIMARY KEY(payroll_id),
            FOREIGN KEY(payroll_id) REFERENCES Payroll(payroll_id)
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Hourly_Payroll
        (
            payroll_id VARCHAR(50),
            per_hour_wage FLOAT,
            total_hours_per_month INTEGER,
            PRIMARY KEY(payroll_id),
            FOREIGN KEY(payroll_id) references Payroll
        )
        ';
EXECUTE IMMEDIATE 
        '
        CREATE TABLE Has_Parts
        (  
            service_center_id INTEGER,
            part_id INTEGER,
            current_quantity INTEGER,
            min_quantity_threshold INTEGER,
            min_order_threshold INTEGER,
            PRIMARY KEY (service_center_id, part_id),
            FOREIGN KEY (part_id) REFERENCES Parts(part_id),
            FOREIGN KEY (service_center_id) REFERENCES Service_Center(service_center_id)
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Supported_Services
        (
            make VARCHAR(50),
            model VARCHAR(50),
            service_type VARCHAR(50),
            year INTEGER,
            month VARCHAR(50),
            miles INTEGER,
            PRIMARY KEY (make, model, service_type)
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Has_Services
        (
            service_center_id INTEGER,
            make VARCHAR(50),
            model VARCHAR(50),
            service_type VARCHAR(50),
            PRIMARY KEY (service_center_id, make, model, service_type),
            FOREIGN KEY (service_center_id) REFERENCES Service_Center(service_center_id)
        )
        ';
        
EXECUTE IMMEDIATE 
        '
        CREATE TABLE Required_Parts
        (
            service_center_id INTEGER,
            make VARCHAR(50),
            model VARCHAR(50),
            service_type VARCHAR(50),
            part_id INTEGER,
            PRIMARY KEY (service_center_id, make, model, service_type, part_id),
            FOREIGN KEY (service_center_id) REFERENCES Service_Center,
            FOREIGN KEY (part_id) REFERENCES Parts  
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Orders
        (
            service_center_id INTEGER,
            order_id INTEGER,
            order_date TIMESTAMP,
            part_id INTEGER,
            quantity INTEGER,
            status VARCHAR(50),
            PRIMARY KEY (order_id),
            FOREIGN KEY(service_center_id)REFERENCES Service_Center,
            FOREIGN KEY (part_id) REFERENCES Parts  
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Timeslots
        (
            service_center_id INTEGER,
            start_time TIMESTAMP,
            end_time TIMESTAMP,
            status VARCHAR(50),
            PRIMARY KEY(service_center_id, start_time),
            FOREIGN KEY(service_center_id)REFERENCES Service_Center
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Car
        (
            license_plate_number VARCHAR(50),
            make VARCHAR(20),
            year TIMESTAMP,
            model VARCHAR(20),
            last_service_date VARCHAR(20),
            last_service_type VARCHAR(20),
            purchase_date TIMESTAMP,
            last_recorded_miles INTEGER,
            PRIMARY KEY (license_plate_number)
        )   
        ';
EXECUTE IMMEDIATE 
        '
        CREATE TABLE Users
        (
            email VARCHAR(20),
            name VARCHAR(20),
            address VARCHAR(20),
            phone VARCHAR(20),
            PRIMARY KEY(email)
        )
        ';
        
EXECUTE IMMEDIATE 
        '
        CREATE TABLE Customer
        (
            customer_id INTEGER,
            email VARCHAR(50),
            name VARCHAR(50),
            address VARCHAR(200),
            phone VARCHAR(20),
            PRIMARY KEY (customer_id),
            FOREIGN KEY(email)REFERENCES Users(email)
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Employee
        (
            employee_id INTEGER UNIQUE,
            email VARCHAR(50),
            role VARCHAR(50), 
            PRIMARY KEY (employee_id, email),
            FOREIGN KEY(email)REFERENCES Users(email)
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE has_cars
        (
            license_plate_number Varchar(50),
            customer_id INTEGER,
            PRIMARY KEY(license_plate_number, customer_id),
            FOREIGN KEY(license_plate_number) REFERENCES Car,
            FOREIGN KEY(customer_id) REFERENCES Customer
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Appointment 
        (
            appointment_id INTEGER,
            status VARCHAR(20),
            preferred_mechanic INTEGER,
            PRIMARY KEY(appointment_id),
            FOREIGN KEY(preferred_mechanic) REFERENCES Employee (employee_id)
        )
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Has_Invoice 
        (
            appointment_id  INTEGER,
            invoice_id  INTEGER,
            PRIMARY KEY(appointment_id),
            FOREIGN KEY(appointment_id) REFERENCES Appointment(appointment_id),
            FOREIGN KEY(invoice_id) REFERENCES Invoice(invoice_id)
        )
        ';
        
EXECUTE IMMEDIATE 
        '
        CREATE TABLE Has_Pay
        (
            payroll_id VARCHAR(50),
            employee_id INTEGER,
            PRIMARY KEY(payroll_id,employee_id),
            FOREIGN KEY(payroll_id) references Payroll(payroll_id),
            FOREIGN KEY(employee_id) references Employee(employee_id)
        )       
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Booked
        (
            customer_id INTEGER,
            service_center_id INTEGER,
            appointment_id INTEGER,
            PRIMARY KEY(appointment_id,customer_id, service_center_id),
            FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
            FOREIGN KEY(service_center_id) REFERENCES Service_Center(service_center_id),
            FOREIGN KEY(appointment_id) REFERENCES Appointment(appointment_id)
        )        
        ';

EXECUTE IMMEDIATE 
        '
        CREATE TABLE Has_Mechanic
        (
            service_center_id INTEGER,
            employee_id INTEGER,
            PRIMARY KEY(service_center_id, employee_id), 
            FOREIGN KEY(employee_id) REFERENCES Employee(employee_id),
            FOREIGN KEY(service_center_id) REFERENCES Service_Center(service_center_id)
        )
        ';
        
EXECUTE IMMEDIATE 
        '
        CREATE TABLE Has_Receptionist
        (
            service_center_id INTEGER,
            employee_id INTEGER,
            PRIMARY KEY(service_center_id, employee_id),
            FOREIGN KEY(employee_id) REFERENCES Employee(employee_id),
            FOREIGN KEY(service_center_id) REFERENCES Service_Center(service_center_id)
        )
        ';
        
EXECUTE IMMEDIATE 
        '
        CREATE TABLE Has_manager
        (
            service_center_id INTEGER,
            employee_id INTEGER,
            PRIMARY KEY( service_center_id, employee_id), 
            FOREIGN KEY(employee_id) REFERENCES Employee(employee_id),
            FOREIGN KEY(service_center_id) REFERENCES Service_Center(service_center_id)
        )
        ';        

EXECUTE IMMEDIATE 'create sequence Service_Center_Seq';
EXECUTE IMMEDIATE 'create sequence Invoice_Seq';
EXECUTE IMMEDIATE 'create sequence Parts_Seq';
EXECUTE IMMEDIATE 'create sequence Payroll_Seq';
EXECUTE IMMEDIATE 'create sequence Monthly_Payroll_Seq';
EXECUTE IMMEDIATE 'create sequence Hourly_Payroll_Seq';
EXECUTE IMMEDIATE 'create sequence Has_Parts_Seq';
EXECUTE IMMEDIATE 'create sequence Supported_Services_Seq';
EXECUTE IMMEDIATE 'create sequence Required_Parts_Seq';
EXECUTE IMMEDIATE 'create sequence Orders_Seq';
EXECUTE IMMEDIATE 'create sequence Timeslots_Seq';
EXECUTE IMMEDIATE 'create sequence Car_Seq';
EXECUTE IMMEDIATE 'create sequence Users_Seq';
EXECUTE IMMEDIATE 'create sequence Customer_Seq';
EXECUTE IMMEDIATE 'create sequence Employee_Seq';
EXECUTE IMMEDIATE 'create sequence Has_Cars_Seq';
EXECUTE IMMEDIATE 'create sequence Has_Invoice_Seq';
EXECUTE IMMEDIATE 'create sequence Has_Pay_Seq';
EXECUTE IMMEDIATE 'create sequence Appointment_Seq';
EXECUTE IMMEDIATE 'create sequence Booked_Seq';
EXECUTE IMMEDIATE 'create sequence Has_Mechanic_Seq';
EXECUTE IMMEDIATE 'create sequence Has_Receptionist_Seq';
EXECUTE IMMEDIATE 'create sequence Has_manager_Seq';

  NULL;
END CREATE_ALL_TABLES;