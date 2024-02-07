import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import Login from './components/login/Login';
import { Routes, Route, } from 'react-router-dom';
import Header from './components/header/Header';
import Home from './components/home/Home';
import Cadastro from './components/cadastro/Cadastro';

function App() {
  return (
    <>
    <Header />       
      <Routes>
        <Route path="/" element={<Home />} />       
        <Route path="/login" element={<Login />} />   
        <Route path="/cadastro" element={<Cadastro />} />   
      </Routes>
    </>
  )
}

export default App
