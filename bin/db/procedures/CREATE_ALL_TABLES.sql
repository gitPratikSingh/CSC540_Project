CREATE OR REPLACE PROCEDURE CREATE_ALL_TABLES AUTHID CURRENT_USER AS 
BEGIN          
        EXECUTE IMMEDIATE 
        'CREATE TABLE PSINGH22.customer
        ( customer_id INTEGER NOT NULL PRIMARY KEY,  
          customer_name VARCHAR(50) NOT NULL,  
          email VARCHAR(50),
          phone VARCHAR(20),
          address VARCHAR(200)
        )';
        
        
        EXECUTE IMMEDIATE 
        'CREATE TABLE PSINGH22.employee( 
            employee_id INTEGER NOT NULL PRIMARY KEY,
            employee_name VARCHAR(50),
            employee_email VARCHAR(50),
            employee_phone VARCHAR(50),
            employee_adress VARCHAR(50),
            employee_role VARCHAR(20),
            CONSTRAINT chk_role CHECK (employee_role in (''MANAGER'', ''RECEPTIONIST'', ''MECHANIC'')) ENABLE
        )';
        
        EXECUTE IMMEDIATE
        'CREATE TABLE car
        (
            customer_id INTEGER NOT NULL, 
            license_plate_number VARCHAR(50) ,
            car_year INTEGER,
            make VARCHAR(20),
            car_model VARCHAR(20),
            last_service_date VARCHAR(20), 
            last_service_type VARCHAR(20), 
            purchase_date TIMESTAMP, 
            last_recorded_milage INTEGER,
            PRIMARY KEY (license_plate_number),
            FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
        )';
        
        EXECUTE IMMEDIATE
        '
        CREATE TABLE inventory
        (
            inventory_id INTEGER,
            part_id INTEGER,
            name VARCHAR(50),
            current_quantity INTEGER,
            unit_price FLOAT,
            threshold_min_quantity INTEGER,
            mininmum_order_threshold INTEGER,
            labour_charge FLOAT,
            time_for_service INTEGER,
            PRIMARY KEY (inventory_id, part_id)
        )
        ';
        
        EXECUTE IMMEDIATE
        'CREATE TABLE service_center
        (
            service_center_id INTEGER,
            service_center_name VARCHAR(100),
            address VARCHAR(200),
            telephone_number VARCHAR(15),
            manager_id INTEGER,
            receptionist_id INTEGER,
            inventory_id INTEGER,
            PRIMARY KEY(service_center_id),
            FOREIGN KEY(manager_id) REFERENCES employee(employee_id), 
            FOREIGN KEY(receptionist_id) REFERENCES employee(employee_id)
        )';
        
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE distributor(
            distributor_id INTEGER NOT NULL PRIMARY KEY,
            name VARCHAR(100),
            address VARCHAR(200)
        )
        ';
        
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE orders (
            order_id INTEGER,
            order_date DATE,
            order_status VARCHAR(20),
            part_id INTEGER,
            quantity INTEGER,
            from_service_center_id INTEGER,
            to_service_center_id INTEGER,
            to_distributor_id INTEGER,
            PRIMARY KEY (order_id),
            FOREIGN KEY (from_service_center_id) REFERENCES service_center(service_center_id),
            FOREIGN KEY (to_service_center_id) REFERENCES service_center(service_center_id),
            FOREIGN KEY (to_distributor_id) REFERENCES distributor(distributor_id),
            CONSTRAINT chk_status CHECK (order_status in (''NEW'', ''PROCESSING'', ''COMPLETED'')) ENABLE
        )
        ';
        
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE appointment(
            appointment_id INTEGER,
            type_of_service VARCHAR(20), 
            license_plate_number VARCHAR(20), 
            current_milage INTEGER, 
            car_type VARCHAR(20),
            time_slot VARCHAR(20),
            model VARCHAR(20), 
            preferred_mechanic VARCHAR(20), 
            service_center_id INTEGER,
            PRIMARY KEY(appointment_id),
            FOREIGN KEY (service_center_id) REFERENCES service_center(service_center_id)
        )
        ';
        
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE invoice(
            invoice_id INTEGER,
            created_at TIMESTAMP,
            price FLOAT,
            service_center_id INTEGER, 
            PRIMARY KEY(invoice_id),
            FOREIGN KEY (service_center_id) REFERENCES service_center(service_center_id)
        )
        ';
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE payroll(
            payroll_id INTEGER,
            employee_id INTEGER,
            wage FLOAT,
            total_pay FLOAT, 
            type VARCHAR(20),
            PRIMARY KEY(payroll_id),
            FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
            CONSTRAINT chk_payroll_type CHECK (type in (''HOURLY'', ''MONTHLY'')) ENABLE
        )
        ';
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE hourly_payroll(
            payroll_id INTEGER,
            start_date TIMESTAMP,
            end_date TIMESTAMP, 
            hours FLOAT,
            PRIMARY KEY(payroll_id)
        )
        ';
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE monthly_payroll(
            payroll_id INTEGER,
            start_date TIMESTAMP,
            end_date TIMESTAMP, 
            month INTEGER,
            year INTEGER,
            PRIMARY KEY(payroll_id)
        )
        ';
        
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE timeslot(
            service_center_id INTEGER, 
            service_date Date,
            starttime TIMESTAMP,
            endtime TIMESTAMP,
            PRIMARY KEY(service_center_id, service_date, starttime)
        )
        ';
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE service(
            service_id INTEGER, 
            service_type VARCHAR(20), 
            service_center_id INTEGER, 
            PRIMARY KEY(service_id),
            CONSTRAINT chk_service_type CHECK (service_type in (''REPAIR'', ''MAINTENANCE'')) ENABLE
        )
        ';
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE maintenance_service(
            service_id INTEGER, 
            maintenance_type VARCHAR(20), 
            time TIMESTAMP,
            cost FLOAT,
            PRIMARY KEY(service_id, maintenance_type)
        )
        ';
        
        EXECUTE IMMEDIATE 
        '
        CREATE TABLE repair_service(
            service_id INTEGER, 
            repair_part_id INTEGER, 
            time TIMESTAMP,
            cost FLOAT,
            PRIMARY KEY(service_id, repair_part_id)
        )
        ';
        
  NULL;
END CREATE_ALL_TABLES;