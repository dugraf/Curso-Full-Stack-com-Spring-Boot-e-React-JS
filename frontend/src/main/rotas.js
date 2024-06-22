import React from 'react'

import Login from '../views/login'
import CadastroUsuario from '../views/cadastroUsuario'

import {Routes, Route, Switch, HashRouter} from 'react-router-dom'

function Rotas(){
    return (
        <HashRouter>
            <Routes>
                <Route exact path="/login" element={<Login/>}/>
                <Route exact path="/cadastro-usuarios" element={<CadastroUsuario/>}/>
            </Routes>
        </HashRouter>
    )
}

export default Rotas