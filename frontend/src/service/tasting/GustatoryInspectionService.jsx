import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_TASTING_URL}/gustatory-inspection`;

export const createGustatoryInspection = (gustatoryInspection) => {
    return axios.post(API_URL, gustatoryInspection);
};

export const getAllGustatoryInspections = () => {
    return axios.get(API_URL);
};

export const getGustatoryInspectionById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateGustatoryInspection = (id, gustatoryInspection) => {
    return axios.put(`${API_URL}/${id}`, gustatoryInspection);
};

export const deleteGustatoryInspection = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
