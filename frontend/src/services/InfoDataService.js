import http from "../http-common";

class InfoDataService {
  //regCard getOne
  async getRegCard(id) {
    return http.get(`/regCards/${id}`);
  }
  
  //regCard update
  async updateRegCard(id, data) {
    return http.put(`/regCards/${id}`, data);
  }

  //docVer getAllByDocId
  async getDocVerByDocId(id) {
    return http.get(`/documentVersions/byDocument/${id}`);
  }

  //docVer download
  downloadDocumentVersion(id) {
    return axios.get(`/documentVersions/download/${id}`, {
      responseType: 'blob',
    });
  }

}

export default new InfoDataService();
