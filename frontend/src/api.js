import axios from 'axios';

// Simple API helper that always points to the Spring Boot backend.
// The frontend passes the country code via X-Country-Override header, which
// mimics how AWS CloudFront geolocation would influence pricing.
const apiClient = axios.create({
  baseURL: 'http://localhost:8080',
});

export const fetchPricingContext = async (countryCode) => {
  const headers = {};
  if (countryCode) {
    headers['X-Country-Override'] = countryCode;
  }
  const response = await apiClient.get('/api/pricing/context', { headers });
  return response.data;
};

export const fetchCourses = async (countryCode) => {
  const headers = {};
  if (countryCode) {
    headers['X-Country-Override'] = countryCode;
  }
  const response = await apiClient.get('/api/courses', { headers });
  return response.data;
};

