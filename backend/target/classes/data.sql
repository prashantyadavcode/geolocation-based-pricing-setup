INSERT INTO course (id, title) VALUES
  (1, 'Java Spring Boot Masterclass'),
  (2, 'React Frontend Bootcamp'),
  (3, 'AWS for Developers');

INSERT INTO pricing_zone (code, currency, symbol) VALUES
  ('INDIA', 'INR', '₹'),
  ('GLOBAL_USD', 'USD', '$'),
  ('EUROPE', 'EUR', '€');

INSERT INTO country_pricing_zone (country_code, pricing_zone_code) VALUES
  ('IN', 'INDIA'),
  ('US', 'GLOBAL_USD'),
  ('CA', 'GLOBAL_USD'),
  ('GB', 'GLOBAL_USD'),
  ('DE', 'EUROPE'),
  ('FR', 'EUROPE');

INSERT INTO course_price (course_id, pricing_zone_code, amount) VALUES
  (1, 'INDIA', 1999),
  (1, 'GLOBAL_USD', 49),
  (1, 'EUROPE', 45),

  (2, 'INDIA', 2499),
  (2, 'GLOBAL_USD', 59),
  (2, 'EUROPE', 55),

  (3, 'INDIA', 2999),
  (3, 'GLOBAL_USD', 79),
  (3, 'EUROPE', 69);

