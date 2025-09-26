import React, { useEffect, useState } from 'react';
import api from '../api/axios';

export default function CompaniesList() {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    (async function fetch() {
      try {
        const res = await api.get('/companies');
        setCompanies(res.data);
      } catch (err) {
        console.error(err);
        alert('Không thể lấy danh sách companies.');
      }
    })();
  }, []);

  return (
    <div className="container mt-4">
      <h3>Companies</h3>
      <table className="table table-hover">
        <thead><tr><th>#</th><th>Name</th><th>Address</th><th>Phone</th></tr></thead>
        <tbody>
          {companies.map(c => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>{c.name}</td>
              <td>{c.address}</td>
              <td>{c.phone}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
