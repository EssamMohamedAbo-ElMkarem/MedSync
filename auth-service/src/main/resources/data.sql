-- Insert data for users from Egypt
CREATE TABLE IF NOT EXISTS users (
id UUID PRIMARY KEY,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
role VARCHAR(50) NOT NULL
);


INSERT INTO users (id, email, password, role) VALUES
(gen_random_uuid(), 'ahmed.ali@medsync', 'password123', 'ADMIN'),
(gen_random_uuid(), 'sara.hassan@medsync', 'password123', 'VIEWER'),
(gen_random_uuid(), 'mohamed.ibrahim@medsync', 'password123', 'VIEWER'),
(gen_random_uuid(), 'mona.shukri@medsync', 'password123', 'ADMIN'),
(gen_random_uuid(), 'omar.sayed@medsync', 'password123', 'VIEWER'),
(gen_random_uuid(), 'essam@medsync', 'password', 'USER');  -- New user

-- Insert data for users from the US
INSERT INTO users (id, email, password, role) VALUES
(gen_random_uuid(), 'john.doe@medsync', 'password123', 'ADMIN'),
(gen_random_uuid(), 'emily.smith@medsync', 'password123', 'VIEWER'),
(gen_random_uuid(), 'james.johnson@medsync', 'password123', 'VIEWER'),
(gen_random_uuid(), 'linda.williams@medsync', 'password123', 'ADMIN'),
(gen_random_uuid(), 'david.brown@medsync', 'password123', 'VIEWER');
