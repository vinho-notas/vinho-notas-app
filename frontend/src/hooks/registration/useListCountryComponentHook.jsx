import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllCountries } from '../../service/registration/CountryService';

const useListCountryComponentHook = () => {
    const [country, setCountry] = useState([]);
    const navigate = useNavigate();

    const fetchCountry = async () => {
        try {
            const response = await getAllCountries();
            setCountry(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchCountry();
    }, []);


  return { country, navigate, fetchCountry };
};

export default useListCountryComponentHook;