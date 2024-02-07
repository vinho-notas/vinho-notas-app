import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';

const Cadastro = () => {
    return (
        <div>           
            <Form className='form-cadastro'>
            <label className="h3 mb-3 fw-normal">Formulário de cadastro</label>

            <Form.Group as={Row} className="mb-3" controlId="formBasicName">
                <Form.Label as={Row} htmlFor="floatingInput">Nome</Form.Label>
                <Form.Control type="text" className="form-control" id="floatingInput" placeholder="Informe seu nome completo"  />          
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="formBasicDocumento">
                <Form.Label as={Row} htmlFor="floatingInput">Documento</Form.Label>
                <Form.Control type="text" className="form-control" id="floatingInput" placeholder="Informe seu CPF"  />          
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="formBasicDataNascimento">
                <Form.Label as={Row} htmlFor="floatingInput">Data de nascimento</Form.Label>
                <Form.Control type="text" className="form-control" id="floatingInput" placeholder="Informe sua data de nascimento"  />          
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="formBasicEndereco">
                <Form.Label as={Row} htmlFor="floatingInput">Endereço</Form.Label>
                <Form.Control type="text" className="form-control" id="floatingInput" placeholder="Informe seu endereço completo"  />          
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="formBasicCheckbox">
                <Form.Check type="checkbox" label="Concordo com os termos e condições de serviço" />
            </Form.Group>

            <Button variant="primary" type="submit">Submit</Button> 

            </Form>        




        </div>
    )
}

export default Cadastro