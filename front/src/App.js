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
        <Route path="/main" element={<MainPage />} />
        <Route path="/test" element={<Hello />} />
        <Route path="/" element={<Login />} />

      </Routes>
    </BrowserRouter>
  );
}


export default App;
