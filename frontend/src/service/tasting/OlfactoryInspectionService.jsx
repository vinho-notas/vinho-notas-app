import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_TASTING_URL}/olfactory-inspection`;

export const createOlfactoryInspection = (olfactoryInspection) => {
    return axios.post(API_URL, olfactoryInspection);
};

export const getAllOlfactoryInspections = () => {
    return axios.get(API_URL);
};

export const getOlfactoryInspectionById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateOlfactoryInspection = (id, olfactoryInspection) => {
    return axios.put(`${API_URL}/${id}`, olfactoryInspection);
};

export const deleteOlfactoryInspection = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
