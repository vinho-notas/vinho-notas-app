import React from 'react'
import { FaLinkedin, FaRegArrowAltCircleRight, FaGithub, FaMailBulk } from 'react-icons/fa';
import './Footer.css';

const Footer = () => {
  const companyLinks = [
    { label: 'Sobre', icon: FaRegArrowAltCircleRight, Link: 'about' },
    { label: 'Fale conosco', icon: FaMailBulk, Link: 'mailto:vanderlei.master@gmail.com' },
    { label: 'Linkedin', icon: FaLinkedin, Link: 'https://www.linkedin.com/in/vanderlei-kleinschmidt-a1557731/'},
    { label: 'Github', icon: FaGithub, Link: 'https://github.com/vinho-notas/vinho-notas-app/' }
  ];  
  
  return (
    <React.Fragment>
      <div
        className="container-fluid text-light footer pt-5 mt-5 wow fadeIn"
        data-wow-delay="0.1s"
      >
        <div className="container py-2">
          <div className="row g-5">
            <div className="col-lg-3 col-md-6">
              {companyLinks.map((link, index) => (
                <a
                  key={index}
                  className="btn btn-link d-flex align-items-center"
                  href={link.Link}
                >
                  {React.createElement(link.icon, { className: 'me-2' })}
                  {link.label}
                </a>
              ))}
            </div>          

          </div>
        </div>

        <div className="container">
          <div className="copyright">
            <div className="row">
              <div className="col-md-6 text-center text-md-start mb-3 mb-md-0">
                ©{' '}
                <a className="border-bottom" href="#">
                  Vinho Notas
                </a>
                , All Right Reserved.
                <br />
                <br />
              </div>
            </div>
          </div>
        </div>

      </div>
    </React.Fragment>
  )
}

export default Footer