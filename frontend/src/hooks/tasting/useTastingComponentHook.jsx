import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllTastings } from "../../service/tasting/TastingService";

const useListTastingComponentHook = () => {
    const [tastings, setTastings] = useState([]);
    const navigate = useNavigate();

    const fetchTastings = async () => {
        try {
            const response = await getAllTastings();
            setTastings(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchTastings();
    }, []);

    return { tastings, navigate, fetchTastings };
};

export default useListTastingComponentHook;
