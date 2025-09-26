import React, { useEffect, useState } from 'react';
import api from '../api/axios';

export default function UsersList() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    (async function fetch() {
      try {
        const res = await api.get('/users');
        setUsers(res.data);
      } catch (err) {
        console.error(err);
        alert('Không thể lấy danh sách users (xem console).');
      }
    })();
  }, []);

  return (
    <div className="container mt-4">
      <h3>Users</h3>
      <table className="table table-striped">
        <thead><tr><th>#</th><th>Username</th><th>Full name</th><th>Email</th><th>Company</th></tr></thead>
        <tbody>
          {users.map(u => (
            <tr key={u.id}>
              <td>{u.id}</td>
              <td>{u.username}</td>
              <td>{u.fullName}</td>
              <td>{u.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
