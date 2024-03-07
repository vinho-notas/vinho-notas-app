import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllGustatoryInspections } from '../../service/tasting/GustatoryInspectionService';

export const useGustatoryInspectionComponentHook = () => {
    const [gustatoryInspections, setGustatoryInspections] = useState([]);
    const navigate = useNavigate();

    const fetchGustatoryInspections = async () => {
        try {
            const response = await getAllGustatoryInspections();
            setGustatoryInspections(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchGustatoryInspections();
    }, []);

    return { gustatoryInspections, navigate, fetchGustatoryInspections };
};

export default useGustatoryInspectionComponentHook;
