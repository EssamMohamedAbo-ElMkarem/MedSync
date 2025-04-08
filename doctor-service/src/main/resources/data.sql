
CREATE TABLE IF NOT EXISTS doctor (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    joining_date DATE NOT NULL
);

INSERT INTO doctor (id, name, email, specialization, address, joining_date) VALUES
(gen_random_uuid(), 'Ahmed Abdelrahman', 'ahmed.abdelrahman@medsync.com', 'Cardiology', '10 Tahrir St, Cairo', '2015-03-22'),
(gen_random_uuid(), 'Sarah Williams', 'sarah.williams@medsync.com', 'Dermatology', '123 Main St, New York', '2018-05-15'),
(gen_random_uuid(), 'Mohamed Fathy', 'mohamed.fathy@medsync.com', 'Orthopedic Surgery', '22 Nile St, Cairo', '2017-08-02'),
(gen_random_uuid(), 'Nadine Ibrahim', 'nadine.ibrahim@medsync.com', 'Pediatrics', '15 Ramsis St, Giza', '2020-11-10'),
(gen_random_uuid(), 'John Miller', 'john.miller@medsync.com', 'General Surgery', '45 Broadway, Boston', '2019-06-25'),
(gen_random_uuid(), 'Mariam Adel', 'mariam.adel@medsync.com', 'Obstetrics & Gynecology', '5 Zamalek St, Cairo', '2021-07-18'),
(gen_random_uuid(), 'Omar Youssef', 'omar.youssef@medsync.com', 'Neurology', '30 Mohandessin St, Cairo', '2016-04-12'),
(gen_random_uuid(), 'Emily Stone', 'emily.stone@medsync.com', 'Oncology', '87 Sunset Blvd, LA', '2017-01-20'),
(gen_random_uuid(), 'Khaled Hassan', 'khaled.hassan@medsync.com', 'Internal Medicine', '28 Mokattam St, Cairo', '2018-02-07'),
(gen_random_uuid(), 'David Lee', 'david.lee@medsync.com', 'Gastroenterology', '456 Elm St, Chicago', '2016-11-14'),
(gen_random_uuid(), 'Amira Hassan', 'amira.hassan@medsync.com', 'Cardiology', '12 Garden City, Cairo', '2019-10-22'),
(gen_random_uuid(), 'Joseph Thompson', 'joseph.thompson@medsync.com', 'Neurology', '33 Beverly Hills, LA', '2020-05-15'),
(gen_random_uuid(), 'Ahmed Nabil', 'ahmed.nabil@medsync.com', 'Orthopedic Surgery', '17 Alexandria St, Cairo', '2015-09-10'),
(gen_random_uuid(), 'Mona Salim', 'mona.salim@medsync.com', 'Pediatrics', '10 City Center, Alexandria', '2018-03-13'),
(gen_random_uuid(), 'Daniel Clark', 'daniel.clark@medsync.com', 'General Surgery', '19 5th Ave, NY', '2021-01-07'),
(gen_random_uuid(), 'Fayza Khalil', 'fayza.khalil@medsync.com', 'Oncology', '23 Downtown Cairo', '2019-07-29'),
(gen_random_uuid(), 'Mark Davis', 'mark.davis@medsync.com', 'Obstetrics & Gynecology', '61 Queens Blvd, NYC', '2021-02-18'),
(gen_random_uuid(), 'Sara Ahmed', 'sara.ahmed@medsync.com', 'Dermatology', '14 New Cairo, Cairo', '2020-08-24'),
(gen_random_uuid(), 'Adham El Sayed', 'adham.elsayed@medsync.com', 'Internal Medicine', '36 Heliopolis St, Cairo', '2017-11-01'),
(gen_random_uuid(), 'Helen Carter', 'helen.carter@medsync.com', 'Gastroenterology', '98 Ocean Dr, Miami', '2020-04-13'),
(gen_random_uuid(), 'Tariq Fawzy', 'tariq.fawzy@medsync.com', 'Cardiology', '55 Shubra St, Cairo', '2015-12-25'),
(gen_random_uuid(), 'Stephanie Roberts', 'stephanie.roberts@medsync.com', 'Pediatrics', '38 Cairo Tower, Cairo', '2019-10-14');
