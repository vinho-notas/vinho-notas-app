import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAllTastingCards } from "../../service/tasting/TastingCardService";

const useListTastingCardComponentHook = () => {
    const [tastingCards, setTastingCards] = useState([]);
    const navigate = useNavigate();

    const fetchTastingCards = async () => {
        try {
            const response = await getAllTastingCards();
            setTastingCards(response.data);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchTastingCards();
    }, []);

    return { tastingCards, navigate, fetchTastingCards };
};

export default useListTastingCardComponentHook;
