import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_CADASTRO_URL}/countries`;

export const getAllCountries = () => {
    return axios.get(API_URL);
};

export const getCountryByName = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const getCountryByContinent = (name) => {
    return axios.get(`${API_URL}/name/${name}`);
};
