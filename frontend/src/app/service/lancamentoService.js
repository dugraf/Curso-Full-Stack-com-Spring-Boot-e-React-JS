import ApiService from '../apiService'

class LancamentoService extends ApiService {

    constructor(){
        super('/api/lancamentos')
    }

    consultar(lancamentoFiltro){
        let params = `?ano=${lancamentoFiltro.ano}`     //ANO EH OBRIGATORIO

        if(lancamentoFiltro.mes)                        //NAO OBRIGATORIO
            params += `&mes=${lancamentoFiltro.mes}`

        if(lancamentoFiltro.mes)                        //NAO OBRIGATORIO
            params += `&tipo=${lancamentoFiltro.tipo}` 

        if(lancamentoFiltro.status)                     //NAO OBRIGATORIO
            params += `&status=${lancamentoFiltro.status}`

        //if(lancamentoFiltro.usuario)
        params += `&usuario=${lancamentoFiltro.usuario}`

        return this.get(params)
    }
}

export default LancamentoService;