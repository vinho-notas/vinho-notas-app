import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import Login from './components/login/Login';
import { Routes, Route, } from 'react-router-dom';
import Header from './components/header/Header';
import Home from './components/home/Home';
import Footer from './components/footer/Footer';
import TastingCard from './components/tasting/TastingCard';
import ListAddressComponent from './components/registration/forms/ListAddressComponent';
import ListPersonComponent from './components/registration/forms/ListPersonComponent';
import ListUserComponent from './components/registration/forms/ListUserComponent';
import ListWineComponent from './components/wine/forms/ListWineComponent';
import ListPointScaleComponent from './components/review/ListPointScaleComponent';
import ListTastingComponent from './components/tasting/forms/ListTastingCardComponent';
import PersonRegistration from './components/registration/PersonRegistration';
import UserRegistration from './components/registration/UserRegistration';
import PairingComponent from './components/pairing/PairingComponent';
import About from './components/about/About';
import WineRegistration from './components/wine/WineRegistration';

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />

        <Route path="/registration" element={<PersonRegistration />} />   
        <Route path="/user-registration" element={<UserRegistration />} /> 
        <Route path="/users" element={<ListUserComponent />} />
        <Route path="/persons" element={<ListPersonComponent />} />
        <Route path="/address" element={<ListAddressComponent />} />
        
        <Route path="/wine-registration" element={<WineRegistration />} />
        <Route path="/wine-list" element={<ListWineComponent />} />
        <Route path="/wine-review-list" element={<ListPointScaleComponent />} />  
       
        <Route path="/tasting-card" element={<TastingCard />} />
        <Route path="/tasting-list" element={<ListTastingComponent />} />

        <Route path="/pairing-list" element={<PairingComponent />} />
        <Route path='/about' element={<About />} />
      </Routes>
      <Footer />
    </>
  )
}

export default App
