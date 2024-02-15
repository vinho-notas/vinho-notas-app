import React from 'react'
import {
    FaRegArrowAltCircleRight,
    FaFacebookF,
    FaLinkedin,
    FaTwitter,
    FaYoutube,
  } from 'react-icons/fa';
  import './Footer.css';

const Footer = () => {
    const companyLinks = [
        { label: 'Sobre', icon: FaRegArrowAltCircleRight, Link: 'about' },
        { label: 'Fale conosco', icon: FaRegArrowAltCircleRight, Link: 'contact' },        
        { label: 'Política de privacidade', icon: FaRegArrowAltCircleRight, Link: '' },
        { label: 'Termos e condições de uso', icon: FaRegArrowAltCircleRight, Link: '' },
      ];
    
      const contactInfo = [
        { icon: 'fa-envelope', text: 'vanderlei.master@gmail.com' },
      ];
    
      const socialLinks = [
        { icon: FaTwitter, scale: 1.3 },
        { icon: FaFacebookF, scale: 1.3 },
        { icon: FaYoutube, size: 40, scale: 1.3 },
        { icon: FaLinkedin, scale: 1.3 },
      ];    

  return (
    <React.Fragment>
      <div
        className="container-fluid bg-dark text-light footer pt-5 mt-5 wow fadeIn"
        data-wow-delay="0.1s"
      >
        <div className="container py-2">
          <div className="row g-5">
            <div className="col-lg-3 col-md-6">
              <h4 className="section-title ff-secondary text-start text-primary fw-normal mb-4">
                Company
              </h4>
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
            <div className="col-lg-3 col-md-6">
              <h4 className="section-title ff-secondary text-start text-primary fw-normal mb-4">
                Contact
              </h4>
              {contactInfo.map((info, index) => (
                <p key={index} className="mb-2">
                  <i className={`fa ${info.icon} me-3`} />
                  {info.text}
                </p>
              ))}
              <div className="d-flex pt-2">
                {socialLinks.map((social, index) => (
                  <a
                    key={index}
                    className="btn btn-outline-light btn-social"
                    href=""
                  >
                    {React.createElement(social.icon, {
                      className: 'display-1',
                      style: { scale: social.scale, size: social.size },
                    })}
                  </a>
                ))}
              </div>
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