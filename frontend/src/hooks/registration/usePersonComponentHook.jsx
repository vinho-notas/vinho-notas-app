import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createPerson } from '../../service/registration/PersonService';

const usePersonComponentHook = () => {
    const [name, setName] = useState('');
    const [document, setDocument] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [address, setAddress] = useState({
        addressDescription: '',
        addressNumber: '',
        complement: '',
        district: '',
        zipCode: '',
        city: '',
        uf: '',
        country: '',
        phoneNumber: ''
    });
    const [dthreg, setDthreg] = useState('');
    const [userreg, setUserreg] = useState('');
    const [dthalt, setDthalt] = useState('');
    const [useralt, setUseralt] = useState('');
    const navigate = useNavigate();

    const savePerson = async () => {
        const person = {
            name,
            document,
            birthDate,
            address,
            dthreg,
            userreg,
            dthalt,
            useralt
        };

        try {
            await createPerson(person);
            navigate('/registration');
        } catch (error) {
            console.log(error);
        }
    };

    return {
        name, setName,
        document, setDocument,
        birthDate, setBirthDate,
        address, setAddress,
        dthreg, setDthreg,
        userreg, setUserreg,
        dthalt, setDthalt,
        useralt, setUseralt,
        savePerson
    };
};

export default usePersonComponentHook;
