import { Card } from "react-bootstrap";

const WineReviewFinal = ({ values }) => {

    const { whatTasted, whenTasted, whatSaw, whatAromas, whatFlavors, whatOpinion, pointScale } = values;

  return (
    <>
    <Card style={{ marginTop: 100, textAlign: "left" }}>
            <Card.Body>
                <p><strong>Vinho avaliado:</strong> {whatTasted}{" "}</p>
                <p><strong>Quando foi avaliado:</strong> {whenTasted}{" "}</p>
                <p><strong>O que viu:</strong> {whatSaw}{" "}</p>
                <p><strong>Que aromas sentiu:</strong> {whatAromas}{" "}</p>
                <p><strong>Que sabores percebeu:</strong> {whatFlavors}{" "}</p>
                <p><strong>Opinião geral:</strong> {whatOpinion}{" "}</p>
                <p><strong>Nota atribuída:</strong> {pointScale}{" "}</p>
            </Card.Body>

        </Card>
    </>
  )
}

export default WineReviewFinal