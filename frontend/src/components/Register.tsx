import React, { useState } from "react";
import ReactDOM from "react-dom";
import useFetch from "../custom_hooks/useFetch";
import styles from "./Modal.module.css";

const fetchData = useFetch();

const Register = (props: any) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const [role, setRole] = useState("");
  const [responseMsg, setResponseMsg] = useState("");
  const authRoot = document.querySelector<HTMLDivElement>("#modal-root")!;

  const registerClick = async () => {
    const res = await fetchData("/register", "PUT", undefined, undefined, {
      username,
      password,
      role,
    });
    if (res.ok) {
      setResponseMsg(res.data);
      console.log(res.data);
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
                props.setShowRegister(false);
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
                <div>Choose an account type:</div>
                <select
                  onChange={(e) => {
                    setRole(e.target.value);
                  }}
                >
                  <option value="USER">User</option>
                  <option value="MANAGER">Manager</option>
                </select>
              </div>
              <hr />
              <hr />
              <button
                onClick={() => {
                  registerClick();
                }}
              >
                Register
              </button>
              {errorMsg ? (
                <p style={{ color: "red", margin: "0" }}>{errorMsg}</p>
              ) : (
                <div style={{ height: "36px", margin: "0" }}></div>
              )}
              {/* {responseMsg ? (
                <p style={{ color: "green", margin: "0" }}>{responseMsg}</p>
              ) : (
                <div style={{ height: "36px", margin: "0" }}></div>
              )} */}
              <br />
            </div>
          </div>
        </div>,
        authRoot
      )}
    </>
  );
};

export default Register;
