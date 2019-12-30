import Axios from "axios"

export default Axios.create({
    baseURL: 'http://localhost:3000/mercado/rs/produtos',
    headers: [{'Access-Control-Allow-Origin':'*'}],
    withCredentials: true

})