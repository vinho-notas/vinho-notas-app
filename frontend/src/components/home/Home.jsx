const Home = () => {
  return (<>
    <div style={{ textAlign: 'justify', fontSize: '1.3em', fontFamily: 'arial', marginTop: '20px' }}>
      <p style={{ textAlign: 'center' }}>Sejam bem-vindos ao Vinho Notas!</p>
      <p>Esta é uma aplicação para você que gosta de vinhos e quer guardar suas impressões sobre os vinhos que experimentou.</p>
      <p>Para começar, clique em <strong>"Vinho"</strong> no menu acima e adicione um vinho.</p>
      <p>Depois, clique no vinho que você adicionou e adicione suas impressões sobre ele, clicando no botão <strong>"Avaliar"</strong>.</p>
      <p>Você pode adicionar quantos rótulos quiser e para ver a lista daqueles que você adicionou, clique em <strong>"Vinho"</strong> no menu acima.</p>
      <p>Na tela também é possível editar ou excluir um vinho cadastrado.</p>
      <p>As avaliações registradas estão no menu <strong>"Vinho"</strong> -> <strong>"Avaliações"</strong>. Na tela de avaliações, é possível editar ou excluir uma avaliação que você fez sobre um dos vinhos cadastrados.</p>
      <p>Para uma degustação guiada você pode clicar no menu <strong>"Degustação"</strong> e depois em <strong>"Fichas de degustação"</strong>. Aqui você pode registrar a ficha diretamente, sem a necessidade de cadastrar o vinho previamente. Para isso basta clicar no botão <strong>"Novo"</strong>. Na tela você pode editar ou excluir uma degustação já cadastrada.</p>
      <p>Se estiver em busca de um sommelier que lhe dê dicas sobre um determinado vinho ou ainda sugira alguma harmonização para um rótulo específico, clique no menu <strong>"Harmonização"</strong>. Através de uma Inteligência Artificial baseada no ChatGpt o sistema irá lhe fornecer as informações sobre o vinho, sugerir alimentos que harmonizem com a sua escolha ou ainda um menu com entrada, prato principal e sobremesa que harmonize com o vinho escolhido. </p>
      <p style={{ textAlign: 'center' }}><em>Cheers! 🍷</em></p>
      <p style={{ textAlign: 'center' }}>A equipe do Vinho Notas</p>
    </div>
  </>
  );
};

export default Home