import http from "../http-common";

class NewDocDataService {
  //document create
  createDocument(file, documentData) {
    let formData = new FormData();

    formData.append("file", file);

    for (let key in documentData) {
      formData.append(key, documentData[key]);
    }

    return http.post("/documents", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
}

export default new NewDocDataService();
