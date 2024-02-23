import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Stack from 'react-bootstrap/Stack';
import { useState } from 'react';

const SearchWineTastedForm = ({ nextStep, handleFormData, values }) => {
    const [error, setError] = useState(false);

    const submitFormData = (e) => {
        e.preventDefault();
        nextStep();
    };

    return (
        <>
            <Form onSubmit={submitFormData}>
                <Stack direction="horizontal" gap={3} style={{ marginTop: 20 }}>
                    <Form.Control
                        className="me-auto"
                        placeholder="Escreva o nome do vinho aqui..."
                        required
                        name='whatTasted'
                        onChange={handleFormData('whatTasted')}
                    />
                    <Button variant="primary" type="submit">Buscar</Button>
                </Stack>
            </Form>
        </>
    )
}

export default SearchWineTastedForm