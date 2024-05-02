import axios from "axios";

const API_URL = 'http://localhost:8084/api/v1/countries';

export const getAllCountries = () => {
    return axios.get(API_URL);
};

export const getCountryByName = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const getCountryByContinent = (name) => {
    return axios.get(`${API_URL}/name/${name}`);
};
