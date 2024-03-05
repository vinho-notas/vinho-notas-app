import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_REVIEW_URL}/point-scales`;

export const createPointScale = (pointScale) => {
    return axios.post(API_URL, pointScale);
};

export const getAllPointScales = () => {
    return axios.get(API_URL);
};

export const getPointScaleById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updatePointScale = (id, pointScale) => {
    return axios.put(`${API_URL}/${id}`, pointScale);
};

export const deletePointScale = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
