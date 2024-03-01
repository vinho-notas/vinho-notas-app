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
import Footer from './components/footer/Footer';
import Tasting from './components/tasting/Tasting';
import TastingCard from './components/tasting/TastingCard';
import ListWineTastedForm from './components/tasting/forms/ListWineTastedForm';
import ListCountryComponent from './components/registration/forms/ListCountryComponent';
import ListStateComponent from './components/registration/forms/ListStateComponent';
import ListAddressComponent from './components/registration/forms/ListAddressComponent';
import ListPersonComponent from './components/registration/forms/ListPersonComponent';
import ListUserComponent from './components/registration/forms/ListUserComponent';

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registration" element={<Registration />} />
        <Route path="/countries" element={<ListCountryComponent />} />
        <Route path="/states" element={<ListStateComponent />} />
        <Route path="/persons" element={<ListPersonComponent />} />
        <Route path="/users" element={<ListUserComponent />} />
        <Route path="/address" element={<ListAddressComponent />} />
        <Route path="/wine" element={<Wine />} />
        <Route path="/wine-review" element={<WineReview />} />
        <Route path="/tasting" element={<Tasting />} />
        <Route path="/tasting-card" element={<TastingCard />} />
        <Route path="/search-wine" element={<SearchWine />} />
        <Route path="/wine-tasted-list" element={<ListWineTastedForm />} />     
      </Routes>
      <Footer />
    </>
  )
}

export default App
