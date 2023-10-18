import React, { useContext, useState } from "react";
import useFetch from "../custom_hooks/useFetch";
// import AuthContext from "../context/auth";
import { useNavigate } from "react-router-dom";

const fetchData = useFetch();

const Login = () => {
  // const auth = useContext(AuthContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const authRoot = document.querySelector<HTMLDivElement>("#modal-root");
  const navigate = useNavigate();

  const loginClick = async() => {
    const res = await fetchData("/login", "GET", {username, password});
    if (res.ok) {

    }
  }

  return <></>;
};

export default Login;
