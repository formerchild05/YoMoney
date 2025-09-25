import MainPage from "./components/MainPage";
import "./app.css";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Login from "./components/Login";
import Hello from "./components/test/hello";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/main" element={<MainPage user={{ name: "John Doe", profilePicture: "image.png" }} />} />
        <Route path="/test" element={<Hello />} />


      </Routes>
    </BrowserRouter>
  );
}


export default App;
