import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import Login from './components/login/Login';
import { Routes, Route, } from 'react-router-dom';
import Header from './components/header/Header';
import Home from './components/home/Home';
import Registration from './components/registration/Registration';
import Wine from './components/wine/Wine';
import WineReview from './components/wine/WineReview';
import SearchWine from './components/wine/SearchWine';

function App() {
  return (
    <>
    <Header />       
      <Routes>
        <Route path="/" element={<Home />} />       
        <Route path="/login" element={<Login />} />   
        <Route path="/registration" element={<Registration />} />   
        <Route path="/wine" element={<Wine />} />
        <Route path="/wine-review" element={<WineReview />} />
        <Route path="/search-wine" element={<SearchWine />} />
      </Routes>
    </>
  )
}

export default App
