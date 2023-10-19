import { useState } from "react";
import "./App.css";
import Login from "./components/Login";
// import { Route, Routes } from "react-router-dom";

function App() {
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);
  const [user, setUser] = useState("");
  const [userRole, setUserRole] = useState("");
  const [loginStatus, setLoginStatus] = useState(false);
  return (
    <>
      {loginStatus ? (
        <>
          <h1>Hi, Welcome{user}</h1>
          <h2>Role: {userRole}</h2>
        </>
      ) : (
        <>
          <h1>Hi Welcome To My Login Page</h1>
          <button
            onClick={() => {
              setShowLogin(true);
              console.log("login clicked");
            }}
          >
            Login
          </button>
          <button
            onClick={() => {
              setShowRegister(true);
              console.log("register clicked");
            }}
          >
            Register
          </button>
        </>
      )}
      {showLogin && (
        <Login
          setShowLogin={setShowLogin}
          setLoginStatus={setLoginStatus}
          setUser={setUser}
          setUserRole={setUserRole}
        ></Login>
      )}
      {/* {showLogin && (
        <Login
          setShowLogin={setShowLogin}
          setAccessToken={setAccessToken}
        ></Login>
      )} */}
    </>
  );
}

export default App;
