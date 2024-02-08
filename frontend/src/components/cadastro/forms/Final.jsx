import React from "react";
import { Card } from "react-bootstrap";

const Final = ({ values }) => {

    const { name, document, birthDate, addressDescription, addressNumber, complement, 
        district, zipCode, city, uf, country, phoneNumber } = values;


  return (
    <>        
        <Card style={{ marginTop: 100, textAlign: "left" }}>
            <Card.Body>
                <p><strong>Nome completo:</strong> {name}{" "}</p>
                <p><strong>CPF:</strong> {document}{" "}</p>
                <p><strong>Data de nascimento:</strong> {birthDate}{" "}</p>
                <p><strong>Endereço:</strong> {addressDescription}{" "}</p>
                <p><strong>Número:</strong> {addressNumber}{" "}</p>
                <p><strong>Complemento:</strong> {complement}{" "}</p>
                <p><strong>Bairro:</strong> {district}{" "}</p>
                <p><strong>CEP:</strong> {zipCode}{" "}</p>
                <p><strong>Cidade:</strong> {city}{" "}</p>
                <p><strong>UF:</strong> {uf}{" "}</p>
                <p><strong>País:</strong> {country}{" "}</p>
                <p><strong>Telefone:</strong> {phoneNumber}{" "}</p>
            </Card.Body>

        </Card>
    </>
  );
};

export default Final;