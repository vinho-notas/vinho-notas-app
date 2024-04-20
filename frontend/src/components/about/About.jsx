import React from 'react'

const About = () => {
    return (
        <>
            <h1>Sobre</h1>
            <div style={{ textAlign: 'justify', fontFamily: 'arial', fontSize: '1.5em' }}>
                <p>Este é um projeto desenvolvido para fins educacionais, como trabalho de conclusão de curso de pós graduação na <a href='https://online.pucrs.br/pos-graduacao/desenvolvimento-full-stack' target='blank'>PUC RS, do curso de Especialização em Desenvolvimento Full Stack.</a></p>
                <p>Nosso objetivo é fornecer uma solução web, para o apreciador de vinho amador, ou ainda para aquele que deseja entrar nesse mundo dos vinhos. Em outras palavras, para o enófilo amador que tem dificuldade em compreender os métodos e técnicas de degustação devido às complexidades expostas anteriormente, o Vinho Notas é uma aplicação de desktop progressiva (PWA ). Ele facilita a experiência de degustar e, diferentemente dos websites e aplicativos de compras, essa solução melhora a experiência na jornada de descobertas no mundo dos vinhos, ajudando a organizar e registrar as degustações, permitindo recuperar o histórico e sugerindo opções para compras futuras.</p>
                <p>O Vinho Notas é um facilitador para enófilos amadores, uma plataforma PWA organizadora de degustações. A ideia é que seja gratuita para esse perfil de público, simples de usar e intuitiva. Por outro lado, não é um e-commerce, nem uma rede social e muito menos um app mobile.</p>
                <p>Basicamente, o que o Vinho Notas faz é armazenar as informações dos vinhos adquiridos, criar um rating (avaliação por nota) dos vinhos degustados e classificá-los de acordo com certos critérios definidos pelo usuário, como por exemplo, uva, país, cor do vinho etc. Ele também virtualiza a adega, ajuda na organização e auxilia o usuário no processo de degustação. Apesar de guardar os dados dos diversos usuários da aplicação, ele não faz comparação de avaliações feitas com as de outros usuários, e tão pouco recomenda a compra de um determinado vinho em uma loja específica.</p>
                <p>Ao usar essa aplicação, o usuário se sentirá mais confortável para descobrir novos rótulos ou para ingressar no mundo dos vinhos com mais segurança. Outro benefício que se pode obter a partir da sua utilização é evitar que o enófilo incorra em erros de decisão, pois terá um histórico de seus gostos e preferências, permitindo assim fazer escolhas mais assertivas.</p>
                <p>Portanto, isso torna a jornada dos novos consumidores de vinho mais fácil e melhora muito a experiência de quem já é experiente nesse ramo.</p>
                <p>O código fonte desse projeto pode ser verificar em <a href='https://github.com/vinho-notas/vinho-notas-app/' target='blank'>https://github.com/vinho-notas/vinho-notas-app.</a></p>
            </div>
        </>
    )
}

export default About