import axios from "axios";

const httpClient = axios.create({
    baseURL: 'http://localhost:8080'
})

class ApiService {
    constructor(apiUrl) {
        this.apiUrl = apiUrl;
    }

    get(url) {
        return httpClient.get(url)
    }

    post(url, objeto) {
        return httpClient.post(url, objeto)
    }

    put(url, objeto) {
        return httpClient.put(url, objeto)
    }

    delete(url) {
        return httpClient.delete(url)
    }
}

export default ApiService;