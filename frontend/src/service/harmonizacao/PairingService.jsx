import axios from "axios";

const API_URL = `${import.meta.env.VITE_API_BFF_URL}/pairing`;

export const getWineInformation = (wine) => {
    return axios.get(`${API_URL}/information`, { params: wine });
}

export const getWinePairing = (wine) => {
    return axios.get(`${API_URL}/pairings`, { params: wine });
};

export const getMenuPairing = (wine) => {
    return axios.get(`${API_URL}/menu`, { params: wine });
};
