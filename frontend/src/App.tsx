import { useState } from "react";
import "./App.css";
import Login from "./components/Login";

function App() {
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);
  const [accessToken, setAccessToken] = useState("");
  return (
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
      {showLogin && (
        <Login
          showLogin={setShowLogin}
          setShowLogin={setShowLogin}
          accessToken={accessToken}
          setAccessToken={setAccessToken}
        ></Login>
      )}
    </>
  );
}

export default App;
