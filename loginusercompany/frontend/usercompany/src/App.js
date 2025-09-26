import React, { useContext } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, AuthContext } from './contexts/AuthContext';
import Login from './pages/Login';
import Register from './pages/Register';
import UsersList from './pages/UsersList';
import CompaniesList from './pages/CompaniesList';
import Navbar from './components/Navbar';

function Private({ children }) {
  const { user } = useContext(AuthContext);
  return user ? children : <Navigate to="/login" />;
}

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/login" element={<Login/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/users" element={<Private><UsersList/></Private>} />
          <Route path="/companies" element={<Private><CompaniesList/></Private>} />
          <Route path="/" element={<Navigate to="/users" />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
