import { Button } from "primereact/button";
import { useState, useRef } from 'react';
import { InputText } from 'primereact/inputtext';
import { Dialog } from 'primereact/dialog';
import { login } from '../../service/registration/UserService';
import { useNavigate } from "react-router-dom";
import { Toast } from 'primereact/toast';
        

const Login = () => {
    const [visible, setVisible] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const toast = useRef(null);

    const processLogin = async () => {
        const auth = {
            email: username,
            password: password
        };

        try {
            const response = await login(auth);
            console.log(response.data);
            setVisible(false);
            navigate('/home');
        } catch (error) {   
            if (toast.current) {
                toast.current.show({ severity: 'error', summary: 'Error', detail: error.response.data.message, life: 3000 });
            } else {
                console.error('Toast not found')
            }
        }

    };

    return (
        <>
            <div className="jumbotron">
                <h1 className="display-4">Vinho Notas</h1>
                <h3>Bem-vindo à nossa plataforma de degustação de vinhos!</h3>
                <p className="lead">Aqui, você terá a oportunidade de explorar, registrar e compartilhar suas experiências de degustação de vinhos de forma simples e intuitiva. Prepare-se para mergulhar em um universo de aromas, sabores e experiências sensoriais únicas. Estamos ansiosos para ajudá-lo em suas descobertas e tornar a sua experiência o mais memorável e prazerosa possível.</p>
                <p className="lead"><em>Cheers! 🍷</em></p>
                <p className="lead">A equipe do Vinho Notas</p>
                <hr className="my-4"></hr>
                <Button label="LOGIN" icon="pi pi-user" onClick={() => setVisible(true)} text size="large" />
            </div>

            <div>
                <Dialog
                    visible={visible}
                    modal
                    onHide={() => setVisible(false)}
                    content={({ hide }) => (
                        <div className="flex flex-column px-8 py-5 gap-4" style={{ borderRadius: '12px', backgroundImage: 'radial-gradient(circle at left top, var(--primary-400), var(--primary-700))' }}>
                            <div className="inline-flex flex-column gap-2">
                                <label htmlFor="username" className="text-primary-50 font-semibold">E-mail</label>
                                <InputText id="username" label="Username" className="bg-white-alpha-20 border-none p-3 text-primary-50" value={username} onChange={(e) => setUsername(e.target.value)} />
                            </div>
                            <div className="inline-flex flex-column gap-2">
                                <label htmlFor="password" className="text-primary-50 font-semibold">Senha</label>
                                <InputText id="password" label="Password" className="bg-white-alpha-20 border-none p-3 text-primary-50" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                            </div>
                            <div className="flex align-items-center gap-2">
                                <Toast ref={toast} />
                                <Button label="Entrar" onClick={processLogin} text className="p-3 w-full text-primary-50 border-1 border-white-alpha-30 hover:bg-white-alpha-10" style={{ borderRadius: '20px' }}></Button>
                                <Button label="Cancelar" onClick={(e) => hide(e)} text className="p-3 w-full text-primary-50 border-1 border-white-alpha-30 hover:bg-white-alpha-10" style={{ borderRadius: '20px' }}></Button>
                            </div>
                            <div>
                                <p className="text-primary-50 text-center">Não possui uma conta? <a href="/registration" className="text-primary-100 hover:text-primary-50">Cadastre-se</a></p>
                            </div>
                        </div>
                    )}
                ></Dialog>
            </div>
        </>
    );
};

export default Login