import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllWines } from "../../service/wine/WineService";

const useListWineComponentHook = () => {
    const [wines, setWines] = useState([]);
    const navigate = useNavigate();

    const fetchWines = async () => {
        try {
            const response = await getAllWines();
            setWines(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchWines();
    }, []);

    return { wines, navigate, fetchWines };

};

export default useListWineComponentHook;