import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import './App.css';
import PersonTable from './Components/PersonTable';
import Header from './Components/Header';
import CreatePersonForm from './Components/CreatePersonForm';
import Footer from './Components/Footer';
import EditPerson from './Components/EditPerson';

function App() {
  return (
    <Router>
      <div className='container-fluid' >
        <Header title="Contact book" />
        <Routes>
          <Route path="/" element={<Navigate to="/api/person/all" replace />} />
          <Route path="/api/person/all" element={<PersonTable />} />
          <Route path="/api/person/create" element={<CreatePersonForm />} />
          <Route path="/api/person/update/:id" element={<EditPerson />} />
        </Routes>
        <Footer></Footer>
      </div>
    </Router>
  );
}

export default App;
