CREATE TABLE customer (
	customer_id​​ INTEGER, 
	email VARCHAR(50),
	name VARCHAR(100),
	address VARCHAR(200),
	phone VARCHAR(20),
	PRIMARY KEY (​customer_id​​)
);

CREATE TABLE employee(
	employee_id​​ INTEGER,
	address VARCHAR(20),
	name VARCHAR(20), 
	email_address VARCHAR(20), 
	phone_number VARCHAR(20), 
	service_centre_id INTEGER, 
	role ENUM('MANAGER', 'RECEPTIONIST', 'MECHANIC'),
	PRIMARY KEY(​employee_id​​)
);

CREATE TABLE car
(
	customer_id INTEGER, 
	license_plate_number​​ VARCHAR(50) ,
	year INTEGER,
	make VARCHAR(20),
	model VARCHAR(20),
	last_service_date VARCHAR(20), 
	last_service_type VARCHAR(20), 
	purchase_date TIMESTAMP, 
	last_recorded_milage INTEGER,
	PRIMARY KEY (​License_plate_number​​),
	FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE service_centre
(
	service_centre_id​​ INTEGER,
	name VARCHAR(100),
	address VARCHAR(200),
	telephone_number VARCHAR(15),
	manager_id INTEGER,
	receptionist_id INTEGER,
	PRIMARY KEY(​service_centre_id​​),
	FOREIGN KEY(manager_id) REFERENCES employee(employee_id), 
	FOREIGN KEY(receptionist_id) REFERENCES employee(employee_id)
);

CREATE TABLE inventory
(
	inventory_id​​ INTEGER,
	part_id​​ INTEGER,
	name VARCHAR(50),
	current_quantity INTEGER,
	unit_price FLOAT,
	threshold_min_quantity INTEGER,
	mininmum_order_threshold INTEGER,
	labour_charge FLOAT,
	time_for_service INTEGER,
	PRIMARY KEY (​inventory_id, part_id​​),
	Foreign Key (service_centre_id) REFERENCES service_centre(service_centre_id) 
);

CREATE TABLE distributor(
	distributer_id INTEGER,
	name VARCHAR(100),
	address VRACHAR(200),
	PRIMARY KEY(distributer_id)
);

CREATE TABLE order (
	order_id INTEGER,
	date Date,
	order_status ENUM('NEW', 'PROCESSING', 'COMPLETED'),
	part_id INTEGER,
	quantity INTEGER,
	from_service_center_id INTEGER,
	to_service_centre_id INTEGER,
	to_distributor_id INTEGER,
	PRIMARY KEY (​order_id​​),
	FOREIGN KEY (from_service_center_id) REFERENCES service_center(service_center_id),
	FOREIGN KEY (to_service_center_id) REFERENCES service_center(service_center_id)
	FOREIGN KEY (to_distributor_id) REFERENCES distributer(distributer_id)
);

CREATE TABLE appointment(
	appointment_id​​ INTEGER,
	type_of_service VARCHAR(20), 
	license_plate_number VARCHAR(20), 
	current_milage INTEGER, 
	car_type VARCHAR(20),
	time_slot VARCHAR(20),
	model VARCHAR(20), 
	preferred_mechanic VARCHAR(20), 
	service_center_id​​ INTEGER,
	PRIMARY KEY(​appointment_id​​),
	FOREIGN KEY (service_center_id) REFERENCES service_center(service_center_id)
);

CREATE TABLE invoice(
	invoice_id​​ INTEGER,
	created_at TIMESTAMP,
	price FLOAT,
	service_center_id​​ INTEGER, 
	PRIMARY KEY(​invoice_id​​),
	FOREIGN KEY (service_center_id) REFERENCES service_center(service_center_id)
);

CREATE TABLE payroll(
	payroll_id​​ INTEGER,
	employee_id INTEGER,
	wage FLOAT,
	total_pay​​ FLOAT, 
	type ENUM('HOURLY', 'MONTHLY'),
	PRIMARY KEY(​payroll​​_id),
	FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);


CREATE TABLE hourly_payroll(
	payroll_id​​ INTEGER,
	start_date TIMESTAMP,
	end_date TIMESTAMP, 
	hours FLOAT,
	PRIMARY KEY(​payroll​​_id)
);

CREATE TABLE monthly_payroll(
	payroll_id​​ INTEGER,
	start_date TIMESTAMP,
	end_date TIMESTAMP, 
	month INTEGER,
	year INTEGER,
	PRIMARY KEY(​payroll​​_id)
);

CREATE TABLE timeslot(
	service_centre_id ​​INTEGER, 
	date ​​Date,
	starttime ​​TIMESTAMP,
	endtime ​​TIMESTAMP,
	PRIMARY_KEY(​service_centre_id, date, starttime​​)
);

CREATE TABLE service(
	service_id ​​INTEGER, 
	service_type ENUM('REPAIR', 'MAINTENANCE'), 
	service_center_id INTEGER, 
	PRIMARY_KEY(​service_id​​)
);


CREATE TABLE maintenance_service(
	service_id ​​INTEGER, 
	maintenance_type VARCHAR(20), 
	time TIMESTAMP,
	cost FLOAT,
	PRIMARY_KEY(​service_id​​, maintenance_type)
);

CREATE TABLE repair_service(
	service_id ​​INTEGER, 
	repair_part_id INTEGER, 
	time TIMESTAMP,
	cost FLOAT,
	PRIMARY_KEY(​service_id​​, repair_part_id)
);

