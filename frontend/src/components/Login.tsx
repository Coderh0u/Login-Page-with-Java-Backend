import React, { useContext, useState } from "react";
import ReactDOM from "react-dom";
import useFetch from "../custom_hooks/useFetch";
// import AuthContext from "../context/auth";
import styles from "./Modal.module.css";

const fetchData = useFetch();

const Login = (props: any) => {
  // const auth = useContext(AuthContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const authRoot = document.querySelector<HTMLDivElement>("#modal-root")!;

  const loginClick = async () => {
    const res = await fetchData("/login", "GET", { username, password });
    if (res.ok) {
      // do something
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

              <button
                onClick={() => {
                  loginClick();
                }}
              >
                Log In
              </button>
              <br />

              <button
                onClick={() => {
                  props.setShowLogin(false);
                  props.setShowRegister(true);
                }}
              >
                Register
              </button>
            </div>
          </div>
        </div>,
        authRoot
      )}
    </>
  );
};

export default Login;
