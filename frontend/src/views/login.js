import React from 'react'
import Card from '../components/card'
import FormGroup from '../components/form-group'
import {withRouter} from 'react-router-dom'
import UsuarioService from '../app/service/usuarioService'
import LocalStorageService from '../app/service/localStorageService'
import { mensagemErro } from '../components/toastr' 

class Login extends React.Component {

    state = {
        email: '',
        password: '',
        mensagemErro: null
    }

    constructor() {
        super();
        this.service = new UsuarioService();
    }

    entrar = () => {
        this.service.autenticar({
            email: this.state.email,
            senha: this.state.password
        }).then(response => {
            LocalStorageService.addItem('_usuario_logado', response.data)
            this.props.history.push('/home')
        }).catch(erro => {
            mensagemErro(erro.response)
        })
    }

    prepareCadastrar = () => {
        this.props.history.push('/cadastro-usuarios')
    }

    render() {
        return (
            <div className='row'>
                <div className='col-md-6' style={{position : 'relative', left: '300px'}}>
                    <div className='bs-docs-section'>
                        <Card title="Login">
                            <div className='row'>
                                <span>{this.state.mensagemErro}</span>
                            </div>
                            <div className='col-lg-12'>
                                <div className='bs-component'>
                                    <fieldset>
                                        <FormGroup label="Email: *" htmlFor="exampleInputEmail">
                                            <input type="email"
                                            value={this.state.email}
                                            onChange={e => this.setState({email: e.target.value})}
                                            className='form-control'
                                            id="exampleInputEmail"
                                            aria-describedby='emailHelp'
                                            placeholder='Digite o Email'></input>
                                        </FormGroup>
                                        <FormGroup label="Senha: *" htmlFor="exampleInputPassword">
                                            <input type='password'
                                            value={this.state.password}
                                            onChange={e => this.setState({password: e.target.value})}
                                            className='form-control'
                                            id="exampleInputPassword"
                                            placeholder='Digite a senha'></input>
                                        </FormGroup>
                                        <button onClick={this.entrar} className='btn btn-success'>Entrar</button>
                                        <button onClick={this.prepareCadastrar} className='btn btn-danger'>Cadastrar</button>
                                    </fieldset>
                                </div>
                            </div>
                        </Card>
                    </div>
                </div>
            </div>
        )

    }
}

export default withRouter(Login)