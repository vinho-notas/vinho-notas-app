import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_TASTING_URL}/visual-inspection`;

export const createVisualInspection = (visualInspection) => {
    return axios.post(API_URL, visualInspection);
};

export const getAllVisualInspections = () => {
    return axios.get(API_URL);
};

export const getVisualInspectionById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateVisualInspection = (id, visualInspection) => {
    return axios.put(`${API_URL}/${id}`, visualInspection);
};

export const deleteVisualInspection = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};
