import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllStates } from '../../service/registration/StateService';

const useLIstStateComponentHook = () => {
    const [state, setState] = useState([]);
    const navigate = useNavigate();

    const fetchState = async () => {
        try {
            const response = await getAllStates();
            setState(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchState();
    }, []);

  return { state, navigate, fetchState }
}

export default useLIstStateComponentHook