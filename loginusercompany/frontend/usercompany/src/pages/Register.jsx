import React, { useState } from 'react';
import api from '../api/axios';
import { useNavigate } from 'react-router-dom';

export default function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [companyId, setCompanyId] = useState('');
  const nav = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      const body = { username, password, fullName, email, companyId: companyId ? Number(companyId) : null };
      await api.post('/auth/register', body);
      alert('Đăng ký thành công. Vui lòng đăng nhập.');
      nav('/login');
    } catch (err) {
      console.error(err);
      alert(err.response?.data || 'Lỗi đăng ký');
    }
  };

  return (
    <div className="container mt-5" style={{maxWidth: 600}}>
      <h3>Register</h3>
      <form onSubmit={submit}>
        <input className="form-control mb-2" placeholder="Username" value={username} onChange={e=>setUsername(e.target.value)} required/>
        <input className="form-control mb-2" placeholder="Full name" value={fullName} onChange={e=>setFullName(e.target.value)} />
        <input type="email" className="form-control mb-2" placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)} />
        <input className="form-control mb-2" placeholder="Company ID (optional)" value={companyId} onChange={e=>setCompanyId(e.target.value)} />
        <input type="password" className="form-control mb-2" placeholder="Password" value={password} onChange={e=>setPassword(e.target.value)} required/>
        <button className="btn btn-success">Register</button>
      </form>
    </div>
  );
}
