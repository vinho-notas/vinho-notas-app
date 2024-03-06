import axios from 'axios';

const API_URL = `${import.meta.env.VITE_API_TASTING_URL}/tasting-card`;

export const createTastingCard = (tastingCard) => {
    return axios.post(API_URL, tastingCard);
};

export const getAllTastingCards = () => {
    return axios.get(API_URL);
};

export const getTastingCardById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateTastingCard = (id, tastingCard) => {
    return axios.put(`${API_URL}/${id}`, tastingCard);
};

export const deleteTastingCard = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
