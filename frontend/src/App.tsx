import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

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
    </>
  );
}

export default App;
