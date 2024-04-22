import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_BFF_URL}/address`;

export const createAddress = (address) => {
    return axios.post(API_URL, address);
};

export const getAllAddress = () => {
    return axios.get(API_URL);
};

export const getAddressById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const updateAddress = (id, address) => {
    return axios.put(`${API_URL}/${id}`, address);
};

export const deleteAddress = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};

export const deleteAllAddress = (ids) => {
    return axios.delete(`${API_URL}/deleteAll`, { data: ids });
};