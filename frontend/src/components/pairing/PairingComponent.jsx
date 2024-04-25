import React, { useState } from 'react'
import { Card } from 'primereact/card';
import { InputText } from 'primereact/inputtext';
import { Toolbar } from 'primereact/toolbar';
import { Button } from 'primereact/button';
import { getWineInformation, getWinePairing, getMenuPairing } from '../../service/harmonizacao/PairingService';

const PairingComponent = () => {
    const [value, setValue] = useState('');
    const [responseText, setResponseText] = useState('');

    const formatResponse = (response) => {
        let formattedResponse = "Informações encontradas:\n";
        formattedResponse += response.response.replace(/\\n/g, "\n");
        return formattedResponse;
    };

    const onInformationClick = async () => {
        try {
            const response = await getWineInformation({ wine: value });
            setResponseText(formatResponse(response.data));
        } catch (error) {
            console.error('Erro ao obter informações sobre o vinho:', error);
        }
    };

    const onPairingClick = async () => {
        try {
            const response = await getWinePairing({ wine: value });
            setResponseText(formatResponse(response.data));
        } catch (error) {
            console.error('Erro ao obter sugestão de harmonização:', error);
        }
    };

    const onMenuClick = async () => {
        try {
            const response = await getMenuPairing({ wine: value });
            setResponseText(formatResponse(response.data));
        } catch (error) {
            console.error('Erro ao obter sugestão de menu:', error);
        }
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
                {responseText && (
                    <div className="mt-4">
                        <h3>Resposta do Serviço:</h3>
                        <pre>{responseText}</pre>
                    </div>
                )}
            </Card>

        </>
    )
}

export default PairingComponent