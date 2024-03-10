import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllVisualInspections } from '../../service/tasting/VisualInspectionService';

export const useVisualInspectionComponentHook = () => {
    const [visualInspections, setVisualInspections] = useState([]);
    const navigate = useNavigate();

    const fetchVisualInspections = async () => {
        try {
            const response = await getAllVisualInspections();
            setVisualInspections(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchVisualInspections();
    }, []);

    return { visualInspections, navigate, fetchVisualInspections };
};

export default useVisualInspectionComponentHook;
