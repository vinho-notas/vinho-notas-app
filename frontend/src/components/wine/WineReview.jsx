import { Card } from 'react-bootstrap';
import { useState } from "react";
import SearchWineForm from './forms/SearchWineForm';
import WineReviewForm from './forms/WineReviewForm';
import WineReviewFinal from './forms/WineReviewFinal';

const WineReview = () => {
    const [step, setStep] = useState(1);

    const [formData, setFormData] = useState({
        whatTasted: '',
        whenTasted: '',
        whatSaw: '',
        whatAromas: '',
        whatFlavors: '',
        whatOpinion: '',
        pointScale: ''
    });

    const nextStep = () => {
        setStep(step + 1);
    };

    const prevStep = () => {
        setStep(step - 1);
    };

    const handleInputData = input => e => {
        const { value } = e.target;

        setFormData(prevState => ({
            ...prevState,
            [input]: value
        }));
    };


    switch (step) {        
        case 1:
            return (
                <Card style={{ marginTop: 10 }}>
                    <Card.Header as="h5">Avaliação de Vinho</Card.Header>
                    <WineReviewForm
                        prevStep={prevStep}
                        nextStep={nextStep}
                        handleFormData={handleInputData}
                        values={formData}
                    />

                </Card>
            );

        case 2:
            return (
                <Card style={{ marginTop: 10 }}>
                    <Card.Header as="h5">Avaliação de Vinho</Card.Header>
                    <WineReviewFinal
                        values={formData}
                    />
                </Card>
            );

        default:
            return (
                <div className='App'>
                </div>
            );
    }
}

export default WineReview