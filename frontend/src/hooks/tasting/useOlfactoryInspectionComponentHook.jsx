import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllOlfactoryInspections } from '../../service/tasting/OlfactoryInspectionService';

export const useOlfactoryInspectionComponentHook = () => {
    const [olfactoryInspections, setOlfactoryInspections] = useState([]);
    const navigate = useNavigate();

    const fetchOlfactoryInspections = async () => {
        try {
            const response = await getAllOlfactoryInspections();
            setOlfactoryInspections(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchOlfactoryInspections();
    }, []);

    return { olfactoryInspections, navigate, fetchOlfactoryInspections };
};

export default useOlfactoryInspectionComponentHook;
