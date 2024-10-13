import React from "react";
import 'bootswatch/dist/flatly/bootstrap.css';
import Login from '../views/login'
import '../custom.css'
import CadastroUsuario from "../views/cadastroUsuario";
import Rotas from './rotas'
import Navbar from '../components/narvbar'
import 'toastr/build/toastr.css'
import 'toastr/build/toastr.min.js'

import {Button} from 'primereact/button'
import 'primereact/resources/themes/nova/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'

class App extends React.Component{
  render() {
    return(
      <>
      <Navbar />
      <Button label="Click" icon="pi pi-check" iconPos="right" />
      <div className="container">
        <Rotas />
      </div>
      </>
    )
  }
}

export default App;
