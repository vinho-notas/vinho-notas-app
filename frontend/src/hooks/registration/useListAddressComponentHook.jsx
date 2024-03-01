import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllAddress } from '../../service/registration/AddressService';

const useListAddressComponentHook = () => {
    const [address, setAddress] = useState([]);
    const navigate = useNavigate();

    const fetchAddress = async () => {
        try {
            const response = await getAllAddress();
            setAddress(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchAddress();
    }, []);

  return { address, navigate, fetchAddress };
}

export default useListAddressComponentHook