CREATE TABLE IF NOT EXISTS QUESTION (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    QUESTION_TEXT VARCHAR(255) NOT NULL,
    OPTION_A VARCHAR(255) NOT NULL,
    OPTION_B VARCHAR(255) NOT NULL,
    OPTION_C VARCHAR(255) NOT NULL,
    OPTION_D VARCHAR(255) NOT NULL,
    CORRECT_ANSWER VARCHAR(255) NOT NULL
);

INSERT INTO QUESTION (QUESTION_TEXT, OPTION_A, OPTION_B, OPTION_C, OPTION_D, CORRECT_ANSWER)
VALUES
    ('What is the capital of France?', 'Paris', 'London', 'Berlin', 'Madrid', 'Paris'),
    ('What is 2 + 2?', '3', '4', '5', '6', '4'),
    ('Who wrote "Hamlet"?', 'Shakespeare', 'Dickens', 'Hemingway', 'Tolkien', 'Shakespeare');
