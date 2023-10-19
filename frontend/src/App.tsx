import { useState } from "react";
import "./App.css";
import Login from "./components/Login";
import Register from "./components/Register";
import useFetch from "./custom_hooks/useFetch";

const fetchData = useFetch();

function App() {
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);
  const [user, setUser] = useState("");
  const [userRole, setUserRole] = useState("");
  const [accessToken, setAccessToken] = useState("");
  const [loginStatus, setLoginStatus] = useState(false);
  const [allUsers, setAllUsers] = useState([]);

  const logOutClick = async () => {};
  return (
    <>
      {loginStatus ? (
        <>
          <h1>Hi, Welcome {user}</h1>
          <h2>Role: {userRole}</h2>
          <button
            onClick={() => {
              setShowLogin(true);
              console.log("login clicked");
            }}
          >
            Log Out
          </button>
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
          accessToken={accessToken}
          setAccessToken={setAccessToken}
        ></Login>
      )}
      {showRegister && <Register setShowRegister={setShowRegister}></Register>}
    </>
  );
}

export default App;
