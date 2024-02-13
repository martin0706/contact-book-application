import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import style from './PesronTable.module.css'

function PersonTable() {
  const [persons, setPersons] = useState([]);
  const [inputValue, setInputValue] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const location = useLocation();

  let successMessage = location.state && location.state.successMessage;
  const [successMessageDelete, setSuccessMessageDelete] = useState('');

  useEffect(() => {
    fetch('http://localhost:8080/api/person/all')
      .then(response => response.json())
      .then(data => setPersons(data))
      .catch(error => console.error('Error fetching persons:', error));
  }, []);

  const handleChange = (e) => {
    setInputValue(e.target.value); 
    navigate(`/api/person/all${e.target.value ? `?contains=${encodeURIComponent(e.target.value)}` : ''}`);
  }

  const handleDelete = (id) => {
    const confirmed = window.confirm("Are you sure you want to delete this person?");
    if (confirmed) {
      fetch(`http://localhost:8080/api/person/delete/id=${id}`, { method: 'Delete' })
      setPersons(persons.filter(person => person.id !== id));
      setSuccessMessageDelete("Person is deleted successfully");
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(`http://localhost:8080/api/person/search?contains=${encodeURIComponent(inputValue)}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to fetch');
        }
        return response.json();
      })
      .then((data) => {
        setPersons(data);
        setError(null);
      })
      .catch((error) => {
        setError('An error occurred. Please try again.');
        console.error('Error fetching data:', error);
      });
  };

  useEffect(() => {
    if (successMessage) {
      const timer = setTimeout(() => {
        navigate(location.pathname, { state: null });
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [successMessage, location.pathname, navigate])

  useEffect(() => {
    if (successMessageDelete) {
      const timer = setTimeout(() => {
        setSuccessMessageDelete('');
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [successMessageDelete])

  return (
    <div className="container-fluid">
      {successMessage && <div className={style["success-message"]}>{successMessage}</div>}
      {successMessageDelete && <div className={style["success-message"]}>{successMessageDelete}</div>}
      <form onSubmit={handleSubmit} className="form-inline" style={{ textAlign: 'center', display: 'block' }}>
        <input
          type="text"
          value={inputValue}
          onChange={handleChange}
          placeholder="Enter Full Name..."
          style={{ margin: '10px' }}
        />
        <button type="Submit" style={{ textAlign: 'center', padding: "5px 10px", fontSize:"small"}}>Search</button>
      </form>
      <Link to="/api/person/create"><button className='btn btn-success' type="button" style={{ margin: '20px' ,fontSize:"small"}}>Create New Person</button></Link>

      <table className="table table-striped" style={{ tableLayout:"fixed" }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>PIN</th>
            <th>Email Type</th>
            <th>Email</th>
            <th>Address Type</th>
            <th>Address</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {persons.map(person => (
            <tr key={person.id}>
              <td >{person.id}</td>
              <td style={{ padding: '0px', height: "auto", overflow:"auto" }}>{person.fullName}</td>
              <td >{person.pin}</td>
              <td style={{ padding: '0px' }}>{person.emailType}</td>
              <td style={{ padding: '0px' }}>{person.email}</td>
              <td style={{ padding: '0px' }}>{person.addressType}</td>
              <td style={{ padding: '0px' , height: "auto", overflow:"auto"}}>{person.addressInfo}</td>
              <td style={{ padding: '0px' }}>
                <Link to={`/api/person/update/${person.id}`}><button style={{fontSize:"small"}} className="btn btn-primary" type="button">Update</button></Link>
                <button className="btn btn-danger" type="button" style={{ marginLeft: '5px',fontSize:"small" }} onClick={() => handleDelete(person.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {persons.length === 0 && <p style={{ color: 'black' , textAlign: "center"}}>No persons found</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}

export default PersonTable;
