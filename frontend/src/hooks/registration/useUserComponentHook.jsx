import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createUser } from '../../service/registration/UserService';

const useUserComponentHook = () => {
    const navigate = useNavigate();
    const [person, setPerson] = useState({
        id: '',
        name: '',
        document: '',
        birthDate: '',
        address: {
            addressDescription: '',
            addressNumber: '',
            complement: '',
            district: '',
            zipCode: '',
            city: '',
            uf: '',
            country: '',
            phoneNumber: ''
        }
    });
    const [enumProfile, setEnumProfile] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [dthreg, setDthreg] = useState('');
    const [userreg, setUserreg] = useState('');
    const [dthalt, setDthalt] = useState('');
    const [useralt, setUseralt] = useState('');

    const saveUser = async () => {
        const user = {
            person,
            enumProfile,
            email,
            password,
            dthreg,
            userreg,
            dthalt,
            useralt
        };

        try {
            await createUser(user);
            navigate('/registration');
        } catch (error) {
            console.log(error);
        }
    };

    return {
        person, setPerson,
        enumProfile, setEnumProfile,
        email, setEmail,
        password, setPassword,
        dthreg, setDthreg,
        userreg, setUserreg,
        dthalt, setDthalt,
        useralt, setUseralt,
        saveUser
    };
}

export default useUserComponentHook;