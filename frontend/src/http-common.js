import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/",
  withCredentials: true,
  headers: {
    // "Content-type": "application/json",
    Accept: "application/json",
  },
  // responseType: "blob",
});

export default instance;
