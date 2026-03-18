import axios from 'axios';

// The base URL is configurable so the same build works locally and on Vercel.
// - Local dev: defaults to http://localhost:8080
// - Vercel: set VITE_API_BASE_URL to your deployed backend URL
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
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

