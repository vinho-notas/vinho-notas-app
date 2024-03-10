import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllAromas } from '../../service/tasting/AromasService';

export const useAromasComponentHook = () => {
    const [aromas, setAromas] = useState([]);
    const navigate = useNavigate();

    const fetchAromas = async () => {
        try {
            const response = await getAllAromas();
            setAromas(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchAromas();
    }, []);

    return { aromas, navigate, fetchAromas };
};

export default useAromasComponentHook;
