import axios from 'axios';

const API_URL = 'http://localhost:8084/api/v1/persons';

export const createPerson = (person) => {
    return axios.post(API_URL, person);
};

export const getAllPerson = () => {
    return axios.get(API_URL);
};

export const getPersonById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const getPersonByName = (name) => {
    return axios.get(`${API_URL}/name/${name}`);
};

export const updatePerson = (id, person) => {
    return axios.put(`${API_URL}/${id}`, person);
};

export const deletePerson = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};

export const deleteAllPerson = (ids) => {
    return axios.delete(`${API_URL}/deleteAll`, { data: ids });
};