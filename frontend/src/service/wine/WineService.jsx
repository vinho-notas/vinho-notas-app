import axios from "axios";

const API_URL = 'http://localhost:8084/api/v1/wines';

export const createWine = (wine) => {
    return axios.post(API_URL, wine);
};

export const getAllWines = () => {
    return axios.get(API_URL);
};

export const getWineById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateWine = (id, wine) => {
    return axios.put(`${API_URL}/${id}`, wine);
};

export const deleteWine = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};

export const deleteAllWine = (ids) => {
    return axios.delete(`${API_URL}/deleteAll`, { data: ids });
};