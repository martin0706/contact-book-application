import React, { useState, useEffect } from 'react';
import { useParams,Link, useNavigate } from 'react-router-dom';
import style from "./EditPerson.module.css"

function EditPerson() {
    const { id } = useParams(); 
    const [person, setPerson] = useState(null); 
    const [errorData, setErrors] = useState();
    const [formData, setFormData] = useState({
        fullName: null,
        pin: null,
        emailType: null,
        email: null,
        addressType: null,
        addressInfo: null
    });
    const navigate = useNavigate();
    

    useEffect(() => {
        fetch(`http://localhost:8080/api/person/${id}`)
            .then(response => response.json())
            .then(data => setPerson(data))
            .catch(error => console.error('Error fetching person data:', error));
    }, [id]);

    useEffect(() => {
        if (person) {
            setFormData({
                fullName: person.fullName,
                pin: person.pin,
                emailType: person.emailType,
                email: person.email,
                addressType: person.addressType,
                addressInfo: person.addressInfo
            });
        }
    }, [person]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        const updatedValue = value === "" ? null : value;
        setFormData(({
          ...formData,
          [name]: updatedValue
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
    
        const requestOptions = {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(formData)
        };
    
        fetch(`http://localhost:8080/api/person/update/${id}`, requestOptions)
          .then(response => {
            if (!response.ok) {
              return response.json().then(data => {
                if (data && data.validationErrors) {
                  setErrors(data.validationErrors);
                  console.log(errorData)
                } else {
                  throw new Error('Failed to create person');
                }
              });
            }
    
            const emptyFormData = {
              fullName: null,
              pin: null,
              emailType: null,
              email: null,
              addressType: null,
              addressInfo: null
            };
            setFormData(emptyFormData);
            navigate('/api/person/all', { state: { successMessage: 'Person successfully updated' } });
          })
          .catch(error => {
            console.error('Error creating person:', error.message);
          });
      };
    if (!person) {
        return <p>Loading...</p>;
    }

    return (
        <div className={style.formContainer}>
            <h2>Create Person</h2>
            <form onSubmit={handleSubmit}>
                <div className={style.formGroup}>
                    <div className={style.labelInputWrapper}>
                        <label htmlFor="fullName">Full Name:</label>
                        <input
                            type="text"
                            id="fullName"
                            name="fullName"
                            value={formData.fullName}
                            onChange={handleChange}
                            className={`${style.input} ${errorData && errorData.fullName ? style.inputError : ''}`}
                        />
                        {errorData && errorData.fullName?.map((error, i) => <span key={i} className={style.error}>{`${i + 1}. ${error}`}</span>)}
                    </div>
                </div>
                <div className={style.formGroup}>
                    <div className={style.labelInputWrapper}>
                        <label htmlFor="pin">PIN:</label>
                        <input
                            type="text"
                            id="pin"
                            name="pin"
                            value={formData.pin}
                            onChange={handleChange}
                            className={`${style.input} ${errorData && errorData.pin ? style.inputError : ''}`}
                        />
                        {errorData && errorData.pin?.map((error, i) => <span key={i} className={style.error}>{`${i + 1}. ${error}`}</span>)}
                    </div>
                </div>
                <div className={style.formGroup}>
                    <div className={style.labelInputWrapper}>
                        <label htmlFor="emailType">Email Type:</label>
                        <input
                            type="text"
                            id="emailType"
                            name="emailType"
                            value={formData.emailType}
                            onChange={handleChange}
                            className={`${style.input} ${errorData && errorData.emailType ? style.inputError : ''}`}
                        />
                        {errorData && errorData.emailType?.map((error, i) => <span key={i} className={style.error}>{`${i + 1}. ${error}`}</span>)}
                    </div>
                </div>
                <div className={style.formGroup}>
                    <div className={style.labelInputWrapper}>
                        <label htmlFor="email">Email:</label>
                        <input
                            type="text"
                            id="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            className={`${style.input} ${errorData && errorData.email ? style.inputError : ''}`}
                        />
                        {errorData && errorData.email?.map((error, i) => <span key={i} className={style.error}>{`${i + 1}. ${error}`}</span>)}
                    </div>
                </div>
                <div className={style.formGroup}>
                    <div className={style.labelInputWrapper}>
                        <label htmlFor="addressType">Address Type:</label>
                        <input
                            type="text"
                            id="addressType"
                            name="addressType"
                            value={formData.addressType}
                            onChange={handleChange}
                            className={`${style.input} ${errorData && errorData.addressType ? style.inputError : ''}`}
                        />
                        {errorData && errorData.addressType?.map((error, i) => <span key={i} className={style.error}>{`${i + 1}. ${error}`}</span>)}
                    </div>
                </div>
                <div className={style.formGroup}>
                    <div className={style.labelInputWrapper}>
                        <label htmlFor="addressInfo">Address:</label>
                        <input
                            type="text"
                            id="addressInfo"
                            name="addressInfo"
                            value={formData.addressInfo}
                            onChange={handleChange}
                            className={`${style.input} ${errorData && errorData.addressInfo ? style.inputError : ''}`}
                        />
                        {errorData && errorData.addressInfo?.map((error, i) => <span key={i} className={style.error}>{`${i + 1}. ${error}`}</span>)}
                    </div>
                </div>
                <button type="submit" className="btn btn-primary">Update</button>
            </form>
            <Link to={`/api/person/all`}><button className="btn btn-dark" type="button">{"<<<"}Back</button></Link>
        </div>
    );
}

export default EditPerson;
