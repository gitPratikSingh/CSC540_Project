create or replace PROCEDURE DROP_ALL_TABLES AS 
BEGIN
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE orders';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE car';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE customer';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE inventory';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE distributor';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE appointment';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE invoice';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    
    
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE hourly_payroll';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE monthly_payroll';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE payroll';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE timeslot';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;

    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE service_center';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE employee';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE maintenance_service';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE repair_service';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE service';
        EXCEPTION
            WHEN OTHERS THEN
                NULL;
    END;
    
  NULL;
END DROP_ALL_TABLES;