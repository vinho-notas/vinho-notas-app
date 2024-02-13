import { Card } from 'react-bootstrap';
import Table from 'react-bootstrap/Table';
import Form from 'react-bootstrap/Form';

const VinhoList = () => {
  return (
    <Card style={{ marginTop: 100 }}>
      <Card.Header as="h5" >Lista de Vinhos</Card.Header>
      <Table responsive>
        <thead>
          <tr>
            <th>#</th>
            <th>Rótulo</th>
            <th>Preço</th>
            <th>Local de compra</th>
            <th>Data de compra</th>
            <th>Tipo de vinho</th>
            <th>Classificação</th>
            <th>Graduação alcoólica</th>
            <th>Volume da garrafa (ml)</th>
            <th>Uva</th>
            <th>Produtor</th>
            <th>Temperatura de serviço</th>
            <th>Safra</th>
            <th>País de origem</th>
            <th>Tempo de guarda</th>
            <th>Região</th>
            <th>Maturação</th>
            <th>Harmonização</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><Form.Check /></td>
            <td>Portada Winemaker's Selection 2020</td>
            <td>R$ 70,00</td>
            <td>www.evino.com.br</td>
            <td>01/01/2021</td>
            <td>Vinho Tinto</td>
            <td>Vinho Seco</td>
            <td>12,5</td>
            <td>750</td>
            <td>Tempranillo Blanco (80.00%), Verdejo (20.00%)</td>
            <td>Bodegas D. Mateos</td>
            <td>9.0</td>
            <td>2021</td>
            <td>Espanha</td>
            <td>15 anos</td>
            <td>La Rioja</td>
            <td>10 meses em barricas de carvalho francês</td>
            <td>Peixe assado na folha de bananeira, frutos do mar, arroz com brócolis, espetinho de camarão, massas, burrata, saladas.</td>
          </tr>
          <tr>
            <td><Form.Check /></td>
            <td>Chateau Margaux</td>
            <td>R$ 1.200,00</td>
            <td>Empório do Vinho</td>
            <td>01/01/2021</td>
            <td>Vinho Tinto</td>
          </tr>
          <tr>
            <td><Form.Check /></td>
            <td>Chateau Latour</td>
            <td>R$ 1.400,00</td>
            <td>Empório do Vinho</td>
            <td>01/01/2021</td>
            <td>Vinho Tinto</td>
          </tr>
        </tbody>
      </Table>
    </Card>
  )
}

export default VinhoList