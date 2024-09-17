import http from "../http-common";

class HomeDataService {
  //regCard getAll

  async getAll() {
    return http.get("/regCards");
  }
}

export default new HomeDataService();
