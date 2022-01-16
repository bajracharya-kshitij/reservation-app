import React, { useState, useEffect } from 'react'
import { Button, Col, Form, Input, Row } from 'reactstrap';
import axios from 'axios';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEnvelope, faKeyboard, faPhone } from '@fortawesome/free-solid-svg-icons'
import Router from 'next/router';

import * as AuthenticationSlice from '../../../redux/auth.slice'

const Tickets = () => {

  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [contactNumber, setContactNumber] = useState('')
  const [numberOfTickets, setNumberOfTickets] = useState()
  const [status, setStatus] = useState('')
  const [eventName, setEventName] = useState('')

  const token: string | null = useSelector(AuthenticationSlice.getToken)

  const router = useRouter()
  const { id } = router.query

  useEffect(() => {
    axios.get(`/api/event/${id}`,
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        setEventName(response.data.name)
      })
      .catch((error) => {
        console.error(error)
      })
  }, [])

  const submitForm = (e: any) => {
    e.preventDefault();
    axios.post(`/api/ticket/buy`,
      {
        eventId: id,
        name: name,
        email: email,
        contactNumber: contactNumber,
        numberOfTickets: numberOfTickets,
        status: status
      },
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        Router.push(`/user/payment-all/${id}`)
      })
      .catch((error) => {
        console.error(error)
      })
  }

  return (

    <div>
      <h2 className="p-b-20">Buy Tickets for { eventName }</h2>
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
              <Input type="email" className="input100" placeholder="Email" onChange={ (e) => setEmail(e.target.value) } value={ email } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-keyboard">
                  <FontAwesomeIcon icon={ faEnvelope } />
                </span>
              </span>
            </div>

            <div className="wrap-input100  m-b-16">
              <Input type="text" className="input100" placeholder="Contact Number" onChange={ (e) => setContactNumber(e.target.value) } value={ contactNumber } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-keyboard">
                  <FontAwesomeIcon icon={ faPhone } />
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
          </Col>
        </Row>

        <Button className="btn-md btn-success m-r-10" onClick={ () => setStatus('Saved') } type="submit">Save</Button>
        <Button className="btn-md btn-danger" onClick={ () => setStatus('Reserved') } type="submit">Reserve</Button>
      </Form>
    </div>
  )
}

export default Tickets
