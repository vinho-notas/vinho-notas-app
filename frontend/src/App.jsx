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
import OlfactoryInspection from './components/tasting/forms/OlfactoryInspection';
import Aromas from './components/tasting/forms/Aromas';
import VisualInspection from './components/tasting/forms/VisualInspection';

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
        <Route path="/tasting" element={<Tasting />} />
        <Route path="/tasting-card" element={<TastingCard />} />
        <Route path="/search-wine" element={<SearchWine />} />
        <Route path="/visual-inspection" element={<VisualInspection />} />
        <Route path="/olfactory-inspection" element={<OlfactoryInspection />} />       
      </Routes>      
      <Footer />
    </>
  )
}

export default App
