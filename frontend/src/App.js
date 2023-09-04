import './App.css';
import HomePage from './View/HomePage';
import LoginRegisterPage from './View/LoginRegisterPage';
import PrivateRoute from './Components/PrivateRoute/PrivateRoute';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import RestrictedPage from './View/RestrictedPage';

function App() {
  return (
    <div>
      <Router>
        <Routes>s
          <Route path="/" element={<LoginRegisterPage/>}/>
          <Route path="/home" element={<PrivateRoute role="false" fallbackpath="/"><HomePage/></PrivateRoute>}/>
          <Route path="/restricted" element={<PrivateRoute role="true" fallbackpath="/home"><RestrictedPage/></PrivateRoute>}/>
        </Routes>      
      </Router>
    </div>
  );
}

export default App;
