import axios from 'axios';

const API_URL = 'http://localhost:8084/api/v1/users';

export const createUser = (user) => {
    return axios.post(API_URL, user);
};

export const getAllUser = () => {
    return axios.get(API_URL);
};

export const getUserById = (id) => {
    return axios.get(`${API_URL}/${id}`);
};

export const getUserByName = (name) => {
    return axios.get(`${API_URL}/name/${name}`);
};

export const updateUser = (id, user) => {
    return axios.put(`${API_URL}/${id}`, user);
};

export const deleteUser = (id) => {
    return axios.delete(`${API_URL}/${id}`);
};

export const login = (auth) => {
    return axios.post(`${API_URL}/login`, auth);
}

export const deleteAllUser = (ids) => {
    return axios.delete(`${API_URL}/deleteAll`, { data: ids });
};