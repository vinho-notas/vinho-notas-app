import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from "react-bootstrap";
import { useState } from "react";
import StepOne from './forms/StepOne';
import StepTwo from './forms/StepTwo';
import StepThree from './forms/StepThree';
import Final from './forms/Final';


const Cadastro = () => {
    const [step, setStep] = useState(1);

    const [formData, setFormData] = useState({
        name: '', 
        document: '',
        birthDate: '',
        addressDescription: '',
        addressNumber: '',
        complement: '',
        district: '',
        zipCode: '',
        city: '',
        uf: '',
        country: '',
        phoneNumber: ''
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
                <div className='App'>
                    <Container>
                        <Row>
                            <Col md={{ span: 6, offset: 3 }} className='custom-margin'>
                                <StepOne
                                    nextStep={nextStep}
                                    handleFormData={handleInputData}
                                    values={formData}
                                />
                            </Col>
                        </Row>
                    </Container>
                </div>
            );
        case 2:
            return (
                <div className='App'>
                    <Container>
                        <Row>
                            <Col md={{ span: 6, offset: 3 }} className='custom-margin'>
                                <StepTwo
                                    nextStep={nextStep}
                                    prevStep={prevStep}
                                    handleFormData={handleInputData}
                                    values={formData}
                                />
                            </Col>
                        </Row>
                    </Container>
                </div>
            );
        case 3:
            return (
                <div className='App'>
                    <Container>
                        <Row>
                            <Col md={{ span: 6, offset: 3 }} className='custom-margin'>
                                <StepThree
                                    nextStep={nextStep}
                                    prevStep={prevStep}
                                    handleFormData={handleInputData}
                                    values={formData}
                                />
                            </Col>
                        </Row>
                    </Container>
                </div>
            );
        case 4:
            return (
                <div className='App'>
                    <Container>
                        <Row>
                            <Col md={{ span: 6, offset: 3 }} className='custom-margin'>
                                <Final
                                    values={formData}
                                />
                            </Col>
                        </Row>
                    </Container>
                </div>
            );
        default:
            return (
                <div className='App'>
                </div>
            );
    }
};

export default Cadastro