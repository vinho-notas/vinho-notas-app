import 'bootstrap/dist/css/bootstrap.min.css';
import './login.scss'
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const Login = () => {
  return (
    <div >
        <Form className='form-login'>
            <label className="h3 mb-3 fw-normal">Please Sign In</label>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label for="floatingInput">Email address</Form.Label>
                <Form.Control type="email" className="form-control" id="floatingInput" placeholder="name@example.com"  />          
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label for="floatingPassword">Password</Form.Label>
                <Form.Control type="password" className="form-control" id="floatingPassword" placeholder="Password" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicCheckbox">
                <Form.Check type="checkbox" label="Check me out" />
            </Form.Group>

        <Button variant="primary" type="submit">Submit</Button>            
        </Form>
    </div>
  )
}

export default Login