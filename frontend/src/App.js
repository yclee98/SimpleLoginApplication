import './App.css';
import HomePage from './View/HomePage';
import LoginRegisterPage from './View/LoginRegisterPage';
import PrivateRoute from './Components/PrivateRoute/PrivateRoute';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<LoginRegisterPage/>}/>
          <Route path="/home" element={<PrivateRoute><HomePage/></PrivateRoute>}/>
        </Routes>      
      </Router>
    </div>
  );
}

export default App;
