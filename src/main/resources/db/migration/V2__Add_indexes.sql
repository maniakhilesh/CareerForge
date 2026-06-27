-- Opportunities
CREATE INDEX idx_opportunities_company
ON opportunities(company);

CREATE INDEX idx_opportunities_title
ON opportunities(title);

CREATE INDEX idx_opportunities_location
ON opportunities(location);

-- Applications
CREATE INDEX idx_applications_status
ON applications(status);

CREATE INDEX idx_applications_user_id
ON applications(user_id);

CREATE INDEX idx_applications_opportunity_id
ON applications(opportunity_id);