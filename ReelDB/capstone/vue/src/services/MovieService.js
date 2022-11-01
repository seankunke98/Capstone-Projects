import axios from 'axios';

export default {

    getAllMovies() {
        return axios.get('/movies')
    },

    getMovie(id) {
        return axios.get(`/movie/${id}`)
    },

    getNowPlaying() {
        return axios.get('/now-playing')
    },

    getMoviesWithFilters(filter) {
        return axios.post(`/movies/filter`, filter)
    }



}