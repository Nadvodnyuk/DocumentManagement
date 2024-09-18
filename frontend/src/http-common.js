import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:9090/",
  withCredentials: true,
  headers: {
    // "Content-type": "application/json",
    Accept: "application/json",
  },
  // responseType: "blob",
});

export default instance;
