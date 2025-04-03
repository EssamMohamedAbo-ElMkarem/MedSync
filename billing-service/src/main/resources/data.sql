CREATE TABLE IF NOT EXISTS billing_account (
    id UUID PRIMARY KEY,
    patient_id UUID,
    name VARCHAR(255),
    email VARCHAR(255),
    status VARCHAR(50)
);


INSERT INTO billing_account (id, patient_id, name, email, status) VALUES
('f3b90c71-2d5e-4b32-910e-35cf9d836fa6', '34f1c8f8-b6b0-4831-8f53-22314d2845ab', 'James Smith', 'james.smith@medsync.com', 'ACTIVE'),
('a28462be-9f9e-4d5d-b2f3-67e287ee4c4d', '259701ff-c534-4aba-be5c-b0c7b23152bd', 'Olivia Brown', 'olivia.brown@medsync.com', 'ACTIVE'),
('db4868d6-9b83-4414-91b2-1a9a967c3e5e', 'b66f5076-9d84-4947-bba9-d1ea53abb265', 'Ahmed Hassan', 'ahmed.hassan@medsync.com', 'SUSPENDED'),
('8e4c29f2-55b3-4c84-a1d4-fc6d179a6b14', '437c53c4-2121-471e-93f7-6a6dea483fb9', 'Fatima Al Mansoori', 'fatima.mansoori@medsync.com', 'ACTIVE'),
('baf1dd59-cc33-493f-92dc-00bbcf1b5e7e', '1a6da0e7-a45e-4c9f-a37c-62fd60c403b7', 'William Johnson', 'william.johnson@medsync.com', 'INACTIVE'),
('f1e5f96b-6e4f-4bfa-8c8c-4ea18e0f8cf9', 'c51aea2b-a359-4cc3-b0ab-bb2cb0ae06e8', 'Sophie Taylor', 'sophie.taylor@medsync.com', 'ACTIVE');