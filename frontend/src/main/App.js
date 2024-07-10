import React from "react";
import 'bootswatch/dist/flatly/bootstrap.css';
import Login from '../views/login'
import '../custom.css'
import CadastroUsuario from "../views/cadastroUsuario";
import Rotas from './rotas'
import Navbar from '../components/narvbar'
import 'toastr/build/toastr.css'
import 'toastr/build/toastr.min.js'

class App extends React.Component{
  render() {
    return(
      <>
      <Navbar />
      <div className="container">
        <Rotas />
      </div>
      </>
    )
  }
}

export default App;
