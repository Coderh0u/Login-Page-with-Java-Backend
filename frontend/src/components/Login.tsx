import React, { useContext, useState } from "react";
import ReactDOM from "react-dom";
import useFetch from "../custom_hooks/useFetch";
import styles from "./Modal.module.css";

const fetchData = useFetch();

const Login = (props: any) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const authRoot = document.querySelector<HTMLDivElement>("#modal-root")!;

  const loginClick = async () => {
    const res = await fetchData("/login", "GET", undefined, undefined, {
      username,
      password,
      token: props.accessToken,
    });
    if (res.ok) {
      props.setUser(res.data.username);
      props.setUserRole(res.data.userRole);
      props.setAccessToken(res.data.accessToken);
      props.setShowLogin(false);
      props.setLoginStatus(true);
    } else {
      setErrorMsg(res.data);
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
                    setErrorMsg("");
                  }}
                ></input>
              </div>
              <div>
                <div>Password:</div>
                <input
                  type="text"
                  onChange={(e) => {
                    setPassword(e.target.value);
                    setErrorMsg("");
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
              {errorMsg ? (
                <p style={{ color: "red", margin: "0" }}>{errorMsg}</p>
              ) : (
                <div style={{ height: "36px", margin: "0" }}></div>
              )}
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
