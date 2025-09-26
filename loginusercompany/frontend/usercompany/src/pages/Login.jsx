import React, { useState, useContext } from 'react';
import api from '../api/axios';
import { AuthContext } from '../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

export default function Login(){
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useContext(AuthContext);
  const nav = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post('/auth/login', { username, password });
      const { token, username: name, role } = res.data;
      login({ token, username: name, role });

      // điều hướng theo role
      if(role && role.includes('ADMIN')) {
        nav('/users');
      } else {
        nav('/companies');
      }
    } catch(err) {
      alert('Đăng nhập thất bại');
      console.error(err);
    }
  };

  return (
    <div className="container mt-5">
      <h2>Login</h2>
      <form onSubmit={submit}>
        <input value={username} onChange={e=>setUsername(e.target.value)} className="form-control mb-2" placeholder="username"/>
        <input type="password" value={password} onChange={e=>setPassword(e.target.value)} className="form-control mb-2" placeholder="password"/>
        <button className="btn btn-primary">Login</button>
      </form>
    </div>
  );
}
