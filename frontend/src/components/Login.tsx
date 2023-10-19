import React, { useContext, useState } from "react";
import ReactDOM from "react-dom";
import useFetch from "../custom_hooks/useFetch";
import jwt_decode from "jwt-decode";
import styles from "./Modal.module.css";

const fetchData = useFetch();

const Login = (props: any) => {
  // const auth = useContext(AuthContext);
  const [username, setUsername] = useState("user2");
  const [password, setPassword] = useState("password2");
  const [accessToken, setAccessToken] = useState("");
  const [error, setError] = useState("");
  const authRoot = document.querySelector<HTMLDivElement>("#modal-root")!;

  const loginClick = async () => {
    const res = await fetchData("/login", "GET", undefined, undefined, {
      username,
      password,
    });
    if (res.ok) {
      console.log(res.data);
      props.setUser(res.data.username);
      props.setUserRole(res.data.userRole);
      props.setShowLogin(false);
      props.setLoginStatus(true);
    } else {
      console.log(res.data.errorMsg);
    }
  };

  return (
    <>
      {ReactDOM.createPortal(
        <div className={styles.backdrop}>
          <div className={styles.modal}>
            <button
              className={styles.closeButton}
              onClick={() => {
                props.setShowLogin(false);
              }}
            >
              <img
                src="../../assets/close.png
              "
                className={styles.close}
              />
            </button>
            <div className={styles.loginDetails}>
              <div>
                <div>Username:</div>
                <input
                  type="text"
                  onChange={(e) => {
                    setUsername(e.target.value);
                    setError("");
                  }}
                ></input>
                {error ? (
                  <p style={{ color: "red", margin: "0" }}>{error}</p>
                ) : (
                  <div style={{ height: "36px", margin: "0" }}></div>
                )}
              </div>
              <div>
                <div>Password:</div>
                <input
                  type="text"
                  onChange={(e) => {
                    setPassword(e.target.value);
                    setError("");
                  }}
                ></input>
              </div>
              <hr />
              <hr />
              <button
                onClick={() => {
                  loginClick();
                }}
              >
                Log In
              </button>
              {error ? <div>{error}</div> : ""}
              <br />
            </div>
          </div>
        </div>,
        authRoot
      )}
    </>
  );
};

export default Login;
