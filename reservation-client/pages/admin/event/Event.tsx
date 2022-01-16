import React, { useState } from 'react'
import { Button, Col, Form, Input, Row } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAddressCard, faKeyboard, faMoneyBill } from '@fortawesome/free-solid-svg-icons'
import axios from 'axios';
import { useSelector } from 'react-redux';

import * as AuthenticationSlice from '../../../redux/auth.slice'
import { Router } from 'next/router';

const Event = () => {

  const [name, setName] = useState('')
  const [location, setLocation] = useState('')
  const [numberOfTickets, setNumberOfTickets] = useState()
  const [price, setPrice] = useState()

  const token: string | null = useSelector(AuthenticationSlice.getToken)

  const submitForm = (e: any) => {
    e.preventDefault();
    axios.post(`/api/event/`,
      {
        name: name,
        location: location,
        tickets: {
          numberOfTickets: numberOfTickets,
          price: price
        }
      },
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        console.log(response)
      })
      .catch((error) => {
        console.error(error)
      })
  }

  return (

    <div>
      <h2 className="p-b-20">Create Event</h2>
      <Form onSubmit={ submitForm }>
        <Row>
          <Col>
            <div className="wrap-input100  m-b-16">
              <Input type="text" className="input100" placeholder="Name" onChange={ (e) => setName(e.target.value) } value={ name } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-keyboard">
                  <FontAwesomeIcon icon={ faKeyboard } />
                </span>
              </span>
            </div>

            <div className="wrap-input100  m-b-16">
              <Input type="text" className="input100" placeholder="Location" onChange={ (e) => setLocation(e.target.value) } value={ location } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-keyboard">
                  <FontAwesomeIcon icon={ faAddressCard } />
                </span>
              </span>
            </div>

            <div className="wrap-input100  m-b-16">
              <Input type="text" className="input100" placeholder="Number of Tickets" onChange={ (e) => setNumberOfTickets(e.target.value) } value={ numberOfTickets } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-keyboard">
                  <FontAwesomeIcon icon={ faKeyboard } />
                </span>
              </span>
            </div>

            <div className="wrap-input100  m-b-16">
              <Input type="text" className="input100" placeholder="Price" onChange={ (e) => setPrice(e.target.value) } value={ price } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-keyboard">
                  <FontAwesomeIcon icon={ faMoneyBill } />
                </span>
              </span>
            </div>
          </Col>
        </Row>

        <Button className="btn-md btn-success">Create</Button>
      </Form>
    </div>
  )
}

export default Event
