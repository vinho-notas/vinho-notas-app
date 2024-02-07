import './App.css'
import Login from './components/Login'
import { Routes, Route, Link, NavLink } from 'react-router-dom';

function App() {
  return (
    <>
     <nav>
      <ul>
        <li><Link to="/login">Login</Link></li>
      </ul>
     </nav>
      <Routes>       
        <Route path="/login" element={<Login />} />
      </Routes>
    </>
  )
}

export default App
