import axios from "axios";

const API_URL = 'http://localhost:8084/api/v1/states';

export const getAllStates = () => {
    return axios.get(API_URL);
};

export const getStateById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const getStateByName = (stateName) => {
    return axios.get(`${API_URL}/name/${stateName}`);
};

export const getStateByUf = (uf) => {
    return axios.get(`${API_URL}/uf/${uf}`);
};
