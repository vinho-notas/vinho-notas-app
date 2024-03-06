import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_TASTING_URL}/tasting`;

export const createTasting = (tasting) => {
    return axios.post(API_URL, tasting);
};

export const getAllTastings = () => {
    return axios.get(API_URL);
};

export const getTastingById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateTasting = (id, tasting) => {
    return axios.put(`${API_URL}/${id}`, tasting);
};

export const deleteTasting = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
