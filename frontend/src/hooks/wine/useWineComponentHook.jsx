import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { createWine, getAllWines, getWineById, updateWine, deleteWine } from "../../service/wine/WineService";

const useWineComponentHook = () => {
    const [name, setName] = useState({});
    const [price, setPrice] = useState({});
    const [purchaseLocation, setPurchaseLocation] = useState({});
    const [purchaseDate, setPurchaseDate] = useState({});
    const [wineType, setWineType] = useState({});
    const [wineClassification, setWineClassification] = useState({});
    const [alcoholContent, setAlcoholContent] = useState({});
    const [volumeMl, setVolumeMl] = useState({});
    const [grape, setGrape] = useState({});
    const [winery, setWinery] = useState({});
    const [serviceTemperature, setServiceTemperature] = useState({});
    const [harvest, setHarvest] = useState({});
    const [country, setCountry] = useState({});
    const [guardTime, setGuardTime] = useState({});
    const [region, setRegion] = useState({});
    const [maturation, setMaturation] = useState({});
    const [harmonization, setHarmonization] = useState({});
    const [dthreg, setDthreg] = useState(Date.now());
    const [userreg, setUserreg] = useState("admin");
    const [dthalt, setDthalt] = useState({});
    const [useralt, setUseralt] = useState({});


    const saveWine = async (e) => {
        e.preventDefault();

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
        } catch (error) {
            toast.error("Error creating wine! " + error);
        }
    };

    useEffect(() => {
        saveWine();
    });
    return (
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
        dthreg, setDthreg,
        userreg, setUserreg,
        dthalt, setDthalt,
        useralt, setUseralt,
        saveWine
    )
}

export default useWineComponentHook