import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllAddress } from '../../service/registration/AddressService';

const useListAddressComponentHook = () => {
    const [address, setAddress] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    const fetchAddress = async () => {
        try {
           
            const response = await getAllAddress();
            setAddress(response.data);
        } catch (error) {
            console.log(error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchAddress();
    }, []);

  return { address, navigate, fetchAddress, loading, setLoading };
}

export default useListAddressComponentHook