import React, { useEffect, useState } from 'react';
import { fetchCourses, fetchPricingContext } from './api';
import CourseCard from './CourseCard.jsx';

const COUNTRIES = [
  { label: 'India', code: 'IN' },
  { label: 'United States', code: 'US' },
  { label: 'Germany', code: 'DE' },
  { label: 'France', code: 'FR' },
  { label: 'United Kingdom', code: 'GB' },
];

function App() {
  const [selectedCountry, setSelectedCountry] = useState('US');
  const [courses, setCourses] = useState([]);
  const [pricingContext, setPricingContext] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const loadData = async (countryCode) => {
    try {
      setLoading(true);
      setError(null);
      const [context, courseList] = await Promise.all([
        fetchPricingContext(countryCode),
        fetchCourses(countryCode),
      ]);
      setPricingContext(context);
      setCourses(courseList);
    } catch (err) {
      console.error('Failed to load data', err);
      setError('Failed to load pricing data from backend.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadData(selectedCountry);
  }, [selectedCountry]);

  const handleCountryChange = (event) => {
    const newCode = event.target.value;
    setSelectedCountry(newCode);
  };

  return (
    <div className="app-container">
      <header className="app-header">
        <h1>Country Based Course Pricing Demo</h1>
        <p className="subtitle">
          This demo simulates AWS CloudFront geolocation by sending the
          selected country as a header to the Spring Boot backend.
        </p>
      </header>

      <section className="controls">
        <div className="control-group">
          <label htmlFor="country-select" className="control-label">
            Select Country (simulated)
          </label>
          <select
            id="country-select"
            value={selectedCountry}
            onChange={handleCountryChange}
          >
            {COUNTRIES.map((c) => (
              <option key={c.code} value={c.code}>
                {c.label} ({c.code})
              </option>
            ))}
          </select>
        </div>

        <div className="pricing-context">
          <h2>Detected Pricing Context</h2>
          {pricingContext ? (
            <ul>
              <li>
                <span className="label">Country Code:</span>{' '}
                <span>{pricingContext.countryCode}</span>
              </li>
              <li>
                <span className="label">Pricing Zone:</span>{' '}
                <span>{pricingContext.pricingZone}</span>
              </li>
              <li>
                <span className="label">Source:</span>{' '}
                <span>{pricingContext.source}</span>
              </li>
            </ul>
          ) : (
            <p>No pricing context loaded yet.</p>
          )}
        </div>
      </section>

      <main>
        {loading && <p>Loading courses...</p>}
        {error && <p className="error-text">{error}</p>}

        {!loading && !error && (
          <section className="courses-grid">
            {courses.map((course) => (
              <CourseCard key={course.courseId} course={course} />
            ))}
            {courses.length === 0 && (
              <p>No courses found for the selected country.</p>
            )}
          </section>
        )}
      </main>
    </div>
  );
}

export default App;

