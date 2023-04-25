CREATE TABLE customers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  birthdate DATE,
  PRIMARY KEY (id)
);

CREATE TABLE vehicles (
  id BIGINT NOT NULL AUTO_INCREMENT,
  brand VARCHAR(255),
  model VARCHAR(255),
  model_year INT,
  vin VARCHAR(255),
  price DOUBLE,
  PRIMARY KEY (id)
);

CREATE TABLE leasing_contracts (
  id BIGINT NOT NULL AUTO_INCREMENT,
  contract_number VARCHAR(255),
  monthly_rate DOUBLE,
  customer_id BIGINT,
  vehicle_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

-- Insert customers
INSERT INTO customers (first_name, last_name, birthdate)
VALUES
  ('John', 'Doe', '1990-01-01'),
  ('Jane', 'Smith', '1995-02-15'),
  ('Mark', 'Johnson', '1985-05-10');

-- Insert vehicles
INSERT INTO vehicles (brand, model, model_year, vin, price)
VALUES
  ('Toyota', 'Camry', 2022, 'JTDKARFU6N3088226', 25000),
  ('Honda', 'Accord', 2023, '', 27000),
  ('Ford', 'Mustang', 2022, '1FA6P8CF8M5111111', 35000);

-- Insert leasing contracts
INSERT INTO leasing_contracts (contract_number, monthly_rate, customer_id, vehicle_id)
VALUES
  ('LC20220001', 400, 1, 1),
  ('LC20220002', 450, 2, 2),
  ('LC20220003', 500, 3, 3);