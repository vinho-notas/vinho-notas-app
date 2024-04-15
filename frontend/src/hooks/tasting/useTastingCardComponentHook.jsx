import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { createTastingCard } from "../../service/tasting/TastingCardService";

const useTastingCardComponentHook = () => {
    const navigate = useNavigate();

    const [wineTasted, setWineTasted] = useState('');
    const [tastingData, setTastingData] = useState('');
    const [harvest, setHarvest] = useState('');
    const [grapes, setGrapes] = useState('');
    const [country, setCountry] = useState('');
    const [region, setRegion] = useState('');
    const [clarity, setClarity] = useState('');
    const [brightness, setBrightness] = useState('');
    const [viscosity, setViscosity] = useState('');
    const [colorRed, setColorRed] = useState('');
    const [colorWhite, setColorWhite] = useState('');
    const [colorRose, setColorRose] = useState('');
    const [visualInspectionClassification, setVisualInspectionClassification] = useState('');
    const [intensity, setIntensity] = useState('');
    const [olfactoryInspectionPersistence, setOlfactoryInspectionPersistence] = useState('');
    const [quality, setQuality] = useState('');
    const [fruity, setFruity] = useState('');
    const [dryFruits, setDryFruits] = useState('');
    const [florals, setFlorals] = useState('');
    const [vegetablesAndHerbs, setVegetablesAndHerbs] = useState('');
    const [minerals, setMinerals] = useState('');
    const [spices, setSpices] = useState('');
    const [animals, setAnimals] = useState('');
    const [empireumatics, setEmpireumatics] = useState('');
    const [wood, setWood] = useState('');
    const [chemicals, setChemicals] = useState('');
    const [lacteal, setLacteal] = useState('');
    const [sweets, setSweets] = useState('');
    const [olfactoryInspectionClassification, setOlfactoryInspectionClassification] = useState('');
    const [body, setBody] = useState('');
    const [sweetness, setSweetness] = useState('');
    const [tannin, setTannin] = useState('');
    const [acidity, setAcidity] = useState('');
    const [alcohol, setAlcohol] = useState('');
    const [gustatoryInspectionPersistence, setGustatoryInspectionPersistence] = useState('');
    const [maturity, setMaturity] = useState('');
    const [typicality, setTypicality] = useState('');
    const [gustatoryInspectionClassification, setGustatoryInspectionClassification] = useState('');
    const [opinion, setOpinion] = useState('');
    const [pointScale, setPointScale] = useState('');
    const [dthreg] = useState(new Date());
    const [userreg] = useState('admin');
    const [dthalt] = useState('');
    const [useralt, setUseralt] = useState('');

    const saveTastingCard = async () => {
        const tastingCard = {
            wineTasted,
            tastingData,
            harvest,
            grapes,
            country,
            region,
            clarity,
            brightness,
            viscosity,
            colorRed,
            colorWhite,
            colorRose,
            visualInspectionClassification,
            intensity,
            olfactoryInspectionPersistence,
            quality,
            fruity,
            dryFruits,
            florals,
            vegetablesAndHerbs,
            minerals,
            spices,
            animals,
            empireumatics,
            wood,
            chemicals,
            lacteal,
            sweets,
            olfactoryInspectionClassification,
            body,
            sweetness,
            tannin,
            acidity,
            alcohol,
            gustatoryInspectionPersistence,
            maturity,
            typicality,
            gustatoryInspectionClassification,
            opinion,
            pointScale,
            dthreg,
            userreg,
            dthalt,
            useralt
        };

        try {
            await createTastingCard(tastingCard);
            toast.success('Ficha de degustação salva com sucesso!');
            navigate('/tasting-list');
        } catch (error) {
            toast.error('Erro ao salvar a ficha de degustação!');
        }
    };

    return {
        wineTasted, setWineTasted,
        tastingData, setTastingData,
        harvest, setHarvest,
        grapes, setGrapes,
        country, setCountry,
        region, setRegion,
        clarity, setClarity,
        brightness, setBrightness,
        viscosity, setViscosity,
        colorRed, setColorRed,
        colorWhite, setColorWhite,
        colorRose, setColorRose,
        visualInspectionClassification, setVisualInspectionClassification,
        intensity, setIntensity,
        olfactoryInspectionPersistence, setOlfactoryInspectionPersistence,
        quality, setQuality,
        fruity, setFruity,
        dryFruits, setDryFruits,
        florals, setFlorals,
        vegetablesAndHerbs, setVegetablesAndHerbs,
        minerals, setMinerals,
        spices, setSpices,
        animals, setAnimals,
        empireumatics, setEmpireumatics,
        wood, setWood,
        chemicals, setChemicals,
        lacteal, setLacteal,
        sweets, setSweets,
        olfactoryInspectionClassification, setOlfactoryInspectionClassification,
        body, setBody,
        sweetness, setSweetness,
        tannin, setTannin,
        acidity, setAcidity,
        alcohol, setAlcohol,
        gustatoryInspectionPersistence, setGustatoryInspectionPersistence,
        maturity, setMaturity,
        typicality, setTypicality,
        gustatoryInspectionClassification, setGustatoryInspectionClassification,
        opinion, setOpinion,
        pointScale, setPointScale,
        saveTastingCard
    };
};

export default useTastingCardComponentHook