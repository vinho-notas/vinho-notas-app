import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAllUser } from '../../service/registration/UserService';

const useListUserComponentHook = () => {
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    const fetchUsers = async () => {
        try {
            const response = await getAllUser();
            setUsers(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    return { users, navigate, fetchUsers };
};

export default useListUserComponentHook;