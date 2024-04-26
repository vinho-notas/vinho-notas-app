import React, { useState } from 'react'
import { Card } from 'primereact/card';
import { InputText } from 'primereact/inputtext';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { ProgressSpinner } from 'primereact/progressspinner';
import { getWineInformation, getWinePairing, getMenuPairing } from '../../service/harmonizacao/PairingService';

const PairingComponent = () => {
    const [value, setValue] = useState('');
    const [responseText, setResponseText] = useState('');
    const [loading, setLoading] = useState(false);

    const formatResponse = (response) => {
        let formattedResponse = "Informações encontradas:\n";
        formattedResponse += response.response.replace(/\\n/g, "\n");
        return formattedResponse;
    };

    const fetchData = async (fetchFunction) => {
        try {
            setLoading(true);
            const response = await fetchFunction({ wine: value });
            setResponseText(formatResponse(response.data));
        } catch (error) {
            console.error('Erro ao obter informações:', error);
        } finally {
            setLoading(false);
        }
    };

    const onInformationClick = async () => {
        fetchData(getWineInformation);
    };

    const onPairingClick = async () => {
        fetchData(getWinePairing);
    };

    const onMenuClick = async () => {
        fetchData(getMenuPairing);
    };

    const toolbarTemplate = () => {
        return (
            <div className="flex flex-wrap gap-2">
                <Button label="Informações sobre o vinho" icon="pi pi-info-circle" severity="success" onClick={onInformationClick} raised style={{ borderRadius: '20px' }} />
                <Button label="Sugestão de hamonização" icon="pi pi-check-square" severity="secondary" onClick={onPairingClick} raised style={{ borderRadius: '20px' }} />
                <Button label="Sugestão de menu" icon="pi pi-check" severity="Primary" onClick={onMenuClick} raised style={{ borderRadius: '20px' }} />
            </div>
        );
    };

    return (
        <>
            <Card style={{ marginTop: 10 }} title="Sugestão de harmonização">
                <div>
                    <label htmlFor="wine" className='flex justify-content-star'>Escreva aqui o nome do vinho que você deseja harmonizar</label>
                    <InputText id="wine" value={value} onChange={(e) => setValue(e.target.value)} style={{ width: '900px' }} className='flex justify-content-star' />
                </div>
                <Toolbar className="mb-4" start={toolbarTemplate}></Toolbar>
                {loading ? (
                    <div className="flex justify-content-center mt-4">
                        <ProgressSpinner />
                    </div>
                ) : (
                    responseText && (
                        <div className="mt-4">
                            <h3>Resposta do Serviço:</h3>
                            <pre style={{ textAlign: 'left', fontFamily: 'arial', fontSize: '1.2rem'}}>{responseText}</pre>
                        </div>
                    )
                )}
            </Card>

        </>
    )
}

export default PairingComponent