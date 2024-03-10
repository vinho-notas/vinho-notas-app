import axios from 'axios';

const API_URL = `${import.meta.env.VITE_API_TASTING_URL}/aromas`;

export const createAromas = (aromas) => {
    return axios.post(API_URL, aromas);
};

export const getAllAromas = () => {
    return axios.get(API_URL);
};

export const getAromasById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateAromas = (id, aromas) => {
    return axios.put(`${API_URL}/${id}`, aromas);
};

export const deleteAromas = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
