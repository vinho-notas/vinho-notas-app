import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllPerson } from '../../service/registration/PersonService';

const useListPersonComponentHook = () => {
    const [persons, setPersons] = useState([]);
    const navigate = useNavigate();

    const fetchPersons = async () => {
        try {
            const response = await getAllPerson();
            setPersons(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchPersons();
    }, []);

    return { persons, navigate, fetchPersons };
};

export default useListPersonComponentHook;