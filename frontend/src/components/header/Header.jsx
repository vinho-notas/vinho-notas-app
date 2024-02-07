import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';
import './header.css';

const Header = () => {
  return (
    <div>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>        
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
                <Link className="me-auto p-1 d-inline-block" to="/">Home</Link>
                <Link className="me-auto p-1 d-inline-block" to="/login">Login</Link>             
            </Nav>
            </Navbar.Collapse>
        </Container>
    </Navbar>    
    </div>
  )
}

export default Header