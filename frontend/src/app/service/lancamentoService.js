import ApiService from '../apiService'

class LancamentoService extends ApiService {

    constructor(){
        super('/api/lancamentos')
    }

    obterListaMeses() {
        return([
            {label: 'Selecione...', value: ''},
            {label: 'Janeiro', value: 1},
            {label: 'Fevereiro', value: 2},
            {label: 'Março', value: 3},
            {label: 'Abril', value: 4},
            {label: 'Maio', value: 5},
            {label: 'Junho', value: 6},
            {label: 'Julho', value: 7},
            {label: 'Agosto', value: 8},
            {label: 'Setembro', value: 9},
            {label: 'Outubro', value: 10},
            {label: 'Novembro', value: 11},
            {label: 'Dezembro', value: 12},
        ])
    }

    obterListaTipos() {
        return([
            {label: 'Selecione...', value: ''},
            {label: 'Despesa', value: 'DESPESA'},
            {label: 'Receita', value: 'RECEITA'},
        ])
    }

    consultar(lancamentoFiltro){
        let params = `?ano=${lancamentoFiltro.ano}`     //ANO EH OBRIGATORIO

        if(lancamentoFiltro.mes)                        //NAO OBRIGATORIO
            params += `&mes=${lancamentoFiltro.mes}`

        if(lancamentoFiltro.mes)                        //NAO OBRIGATORIO
            params += `&tipo=${lancamentoFiltro.tipo}` 

        if(lancamentoFiltro.status)                     //NAO OBRIGATORIO
            params += `&status=${lancamentoFiltro.status}`

        if(lancamentoFiltro.descricao)
            params += `&descricao=${lancamentoFiltro.descricao}`

        //if(lancamentoFiltro.usuario)
        params += `&usuario=${lancamentoFiltro.usuario}`

        return this.get(params)
    }

    deletar(id) {
        return this.delete(`/${id}`)
    }
}

export default LancamentoService;