import ApiService from "../apiService";

class UsuarioService extends ApiService {
    constructor(){
        super('/api/usuarios')
    }
}

export default UsuarioService;