CREATE TABLE companies (
                           id UUID PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           created_at TIMESTAMP NOT NULL
);

CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       company_id UUID NOT NULL,

                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,

                       first_name VARCHAR(100),
                       last_name VARCHAR(100),

                       role VARCHAR(50),

                       created_at TIMESTAMP NOT NULL,

                       CONSTRAINT fk_user_company
                           FOREIGN KEY (company_id)
                               REFERENCES companies(id)
);