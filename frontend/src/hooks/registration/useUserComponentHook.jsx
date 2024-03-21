import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createUser } from '../../service/registration/UserService';

const useUserComponentHook = () => {
    const navigate = useNavigate();
    const [personId, setPersonId] = useState('');
    const [person, setPerson] = useState('');
    const [enumProfile, setEnumProfile] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [dthreg, setDthreg] = useState('');
    const [userreg, setUserreg] = useState('');
    const [dthalt, setDthalt] = useState('');
    const [useralt, setUseralt] = useState('');

    const saveUser = async (userData) => {
        try {
            await createUser(userData);
            navigate('/registration');
        } catch (error) {
            console.log(error);
        }
    };

    return {
        personId, setPersonId,
        enumProfile, setEnumProfile,
        email, setEmail,
        password, setPassword,
        dthreg, setDthreg,
        userreg, setUserreg,
        dthalt, setDthalt,
        useralt, setUseralt,
        person, setPerson,
        saveUser
    };
}

export default useUserComponentHook;