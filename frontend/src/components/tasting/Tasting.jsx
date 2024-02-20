import { useState } from "react";
import { Card } from "react-bootstrap";
import SearchWineTastedForm from "./forms/SearchWineTastedForm";
import WineTastedForm from "./forms/WineTastedForm";

const Tasting = () => {
    const [step, setStep] = useState(1);

    const [formData, setFormData] = useState({
        whatTasted: '',
        tastingDate: '',
        tastingType: '',
        tastingCards: ''
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
                <Card style={{ marginTop: 100 }}>
                    <Card.Header as="h5">Registrar degustação</Card.Header>
                    <SearchWineTastedForm
                        nextStep={nextStep}
                        handleFormData={handleInputData}
                        values={formData}
                        />
                </Card>
            );

            case 2:
                return (
                    <Card style={{ marginTop: 100 }}>
                        <Card.Header as="h5">Registrar degustação</Card.Header>
                        <Card.Body>
                            <WineTastedForm 
                                nextStep={nextStep}
                                prevStep={prevStep}
                                handleFormData={handleInputData}
                                values={formData}
                            />                            
                        </Card.Body>
                    </Card>
                );

            default:
            return (
                <div className='App'>
                </div>
            );
    }


    // return (
    //     <>
    //         <div>
    //             <h1>Tela de degustação</h1>
    //             <p>Na tela inicial tem uma tabela contendo todas as degustações registradas</p>
    //             <p>Na tabela tem as opções de atualizar e excluir</p>
    //             <h3>Registrar degustação</h3>

    //             <p>Selecione ou cadastre uma degustação:</p>
    //             <div>
    //                 <Form>
    //                     <Stack direction="horizontal" gap={3} style={{ marginTop: 20 }}>
    //                         <Form.Control
    //                             className="me-auto"
    //                             placeholder="Escreva o nome do vinho aqui..."
    //                             required
    //                         />
    //                         <Button variant="primary" type="submit">Buscar</Button>
    //                     </Stack>
    //                 </Form>
    //                 <hr />
    //             </div>
               
            
    //         </div>
    //     </>
    // )
}

export default Tasting