-- ============================
-- Users
-- ============================

CREATE TABLE users (

    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL

);

-- ============================
-- Opportunities
-- ============================

CREATE TABLE opportunities (

    id UUID PRIMARY KEY,

    title VARCHAR(255) NOT NULL,

    company VARCHAR(255) NOT NULL,

    location VARCHAR(255),

    salary VARCHAR(255),

    description VARCHAR(5000),

    source_url VARCHAR(255),

    employment_type VARCHAR(255),

    created_at TIMESTAMP NOT NULL

);

-- ============================
-- Applications
-- ============================

CREATE TABLE applications (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    opportunity_id UUID NOT NULL,

    status VARCHAR(50) NOT NULL,

    applied_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_application_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT fk_application_opportunity
        FOREIGN KEY (opportunity_id)
        REFERENCES opportunities(id)

);