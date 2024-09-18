import http from "../http-common";

class InfoDataService {
  //regCard update
  async updateRegCard(id, data) {
    return http.patch(`/regCards/${id}`, data);
  }

}

export default new InfoDataService();
