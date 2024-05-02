import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { createWine } from "../../service/wine/WineService";

const useWineComponentHook = () => {
    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [purchaseLocation, setPurchaseLocation] = useState("");
    const [purchaseDate, setPurchaseDate] = useState("");
    const [wineType, setWineType] = useState("");
    const [wineClassification, setWineClassification] = useState("");
    const [alcoholContent, setAlcoholContent] = useState("");
    const [volumeMl, setVolumeMl] = useState("");
    const [grape, setGrape] = useState("");
    const [winery, setWinery] = useState("");
    const [serviceTemperature, setServiceTemperature] = useState("");
    const [harvest, setHarvest] = useState("");
    const [country, setCountry] = useState("");
    const [guardTime, setGuardTime] = useState("");
    const [region, setRegion] = useState("");
    const [maturation, setMaturation] = useState("");
    const [harmonization, setHarmonization] = useState("");
    const [dthreg] = useState("");
    const [userreg] = useState("admin");
    const [dthalt, setDthalt] = useState("");
    const [useralt, setUseralt] = useState("");
    const navigate = useNavigate();

    const saveWine = async () => {       

        const wine = {
            name,
            price,
            purchaseLocation,
            purchaseDate,
            wineType,
            wineClassification,
            alcoholContent,
            volumeMl,
            grape,
            winery,
            serviceTemperature,
            harvest,
            country,
            guardTime,
            region,
            maturation,
            harmonization,
            dthreg,
            userreg,
            dthalt,
            useralt
        };

        try {
            await createWine(wine);
            toast.success("Wine created successfully!");
            navigate("/wine-list");
        } catch (error) {
            toast.error("Error creating wine! " + error);
        }
    };

    return {
        name, setName,
        price, setPrice,
        purchaseLocation, setPurchaseLocation,
        purchaseDate, setPurchaseDate,
        wineType, setWineType,
        wineClassification, setWineClassification,
        alcoholContent, setAlcoholContent,
        volumeMl, setVolumeMl,
        grape, setGrape,
        winery, setWinery,
        serviceTemperature, setServiceTemperature,
        harvest, setHarvest,
        country, setCountry,
        guardTime, setGuardTime,
        region, setRegion,
        maturation, setMaturation,
        harmonization, setHarmonization,
        dthreg, userreg, dthalt, useralt,
        saveWine
    };
};

export default useWineComponentHook;
