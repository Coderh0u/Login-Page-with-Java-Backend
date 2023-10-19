import { useEffect, useState } from "react";
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
  const [resMsg, setResMsg] = useState("");
  const [allUsers, setAllUsers] = useState([]);

  const seeAllUsers = async () => {
    const res = await fetchData("/allUsers", "GET");
    if (res.ok) {
      setAllUsers(res.data);
      console.log(res.data);
    }
  };

  const logOutClick = async () => {
    const res = await fetchData("/logout", "PUT", undefined, undefined, {
      token: accessToken,
    });
    if (res.ok) {
      setResMsg(res.data.message);
      setLoginStatus(false);
      setAccessToken("");
      console.log(resMsg);
    }
  };
  useEffect(() => {
    if (userRole === "MANAGER") {
      seeAllUsers();
    }
  }, [loginStatus]);
  return (
    <>
      {loginStatus ? (
        <>
          <div style={userRole === "MANAGER" ? { float: "left" } : {}}>
            <h1>Hi, Welcome {user}</h1>
            <h2>Role: {userRole}</h2>
            <button
              onClick={() => {
                logOutClick();
              }}
            >
              Log Out
            </button>
          </div>
          {userRole === "MANAGER" ? (
            <>
              <div style={{ float: "right", border: "solid black" }}>
                <h3>List of Users</h3>
                {allUsers.map((username: any, index: number) => (
                  <p key={index}>{username.username}</p>
                ))}
              </div>
            </>
          ) : (
            <></>
          )}
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
