import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createAddress } from '../../service/registration/AddressService';

const useAddressComponentHook = () => {
    const [addressDescription, setAddressDescription] = useState('');
    const [addressNumber, setAddressNumber] = useState('');
    const [complement, setComplement] = useState('');
    const [district, setDistrict] = useState('');
    const [zipCode, setZipCode] = useState('');
    const [city, setCity] = useState('');
    const [uf, setUf] = useState('');
    const [country, setCountry] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');    
    const [dthreg, setDthreg] = useState('');
    const [userreg, setUserreg] = useState('');
    const [dthalt, setDthalt] = useState('');
    const [useralt, setUseralt] = useState('');

    const navigate = useNavigate();

    const saveAddress = async () => {
        const address = {
            addressDescription,
            addressNumber,
            complement,
            district,
            zipCode,
            city,
            uf,
            country,
            phoneNumber,
            dthreg,
            userreg,
            dthalt,
            useralt        
        };

        try {
            await createAddress(address);
            navigate('/registration');
        } catch (error) {
            console.log(error);
        }
    };

    return {
        addressDescription, setAddressDescription,
        addressNumber, setAddressNumber,
        complement, setComplement,
        district, setDistrict,
        zipCode, setZipCode,
        city, setCity,
        uf, setUf,
        country, setCountry,
        phoneNumber, setPhoneNumber,
        dthreg, setDthreg,
        userreg, setUserreg,
        dthalt, setDthalt,
        useralt, setUseralt,
        saveAddress
    };

};

export default useAddressComponentHook;