import http from "../http-common";

class NewVersionDataService {
  //docVer create
  async createDocumentVersion(file, documentVersionData) {
    let formData = new FormData();

    formData.append("file", file);

    for (let key in documentVersionData) {
      formData.append(key, documentVersionData[key]);
    }

    return http.post("/documentVersions", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
  }
}

export default new NewVersionDataService();
