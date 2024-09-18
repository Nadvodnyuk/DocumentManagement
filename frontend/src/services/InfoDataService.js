import http from "../http-common";

class InfoDataService {
  //regCard getOne
  async getRegCard(id) {
    return http.get(`/regCards/${id}`);
  }

  //docVer getAllByDocId
  async getDocVerByDocId(id) {
    return http.get(`/documentVersions/byDocument/${id}`);
  }

  //docVer download
  downloadDocumentVersion(id) {
    return http.get(`/documentVersions/download/${id}`, {
      responseType: 'blob',
    });
  }

}

export default new InfoDataService();
