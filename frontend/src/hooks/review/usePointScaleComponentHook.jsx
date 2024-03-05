import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllPointScales } from "../../service/pointScale/PointScaleService";

const useListPointScaleComponentHook = () => {
    const [pointScales, setPointScales] = useState([]);
    const navigate = useNavigate();

    const fetchPointScales = async () => {
        try {
            const response = await getAllPointScales();
            setPointScales(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchPointScales();
    }, []);

    return { pointScales, navigate, fetchPointScales };
};

export default useListPointScaleComponentHook;