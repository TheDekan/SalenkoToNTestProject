CREATE TABLE clients(id INTEGER not null primary key AUTO_INCREMENT, name VARCHAR(50), phone VARCHAR(50));

CREATE TABLE places (id INTEGER not null primary key AUTO_INCREMENT, client_id INTEGER, address VARCHAR(50), FOREIGN KEY (client_id) REFERENCES clients(id));

CREATE TABLE visits (id INTEGER not null primary key AUTO_INCREMENT, visit_time DATETIME, place_id INTEGER, FOREIGN KEY (place_id) REFERENCES places(id));


clients(id, name, phone)
places(id, client_id, address) FOREIGN KEY (client_id) REFERENCES clients(id)
visits(id, datetime, place_id) FOREIGN KEY (place_id) REFERENCES places(id)
