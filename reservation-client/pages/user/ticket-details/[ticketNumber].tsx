import React, { useState, useEffect } from 'react'
import { Button, Col, Form, Input, Row } from 'reactstrap';
import { useRouter } from 'next/router'
import axios from 'axios';
import { useSelector } from 'react-redux';
import Router from 'next/router';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEnvelope, faKeyboard, faPhone } from '@fortawesome/free-solid-svg-icons'

import * as AuthenticationSlice from '../../../redux/auth.slice'

interface ITicketDetails {
  ticketNumber: string,
  name: string,
  email: string,
  contactNumber: string,
  status: string,
}

const TicketDetails = () => {

  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [contactNumber, setContactNumber] = useState('')
  const [status, setStatus] = useState('')

  const router = useRouter()
  const { ticketNumber } = router.query

  const token: string | null = useSelector(AuthenticationSlice.getToken)

  useEffect(() => {
    axios.get(`/api/ticket/${ticketNumber}`,
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        setName(response.data.name)
        setEmail(response.data.email)
        setContactNumber(response.data.contactNumber)
        setStatus(response.data.status)
      })
      .catch((error) => {
        console.error(error)
      })
  }, [])

  const submitForm = (e: any) => {
    e.preventDefault();
    axios.put(`/api/ticket/${ticketNumber}`,
      {
        ticketNumber: ticketNumber,
        name: name,
        email: email,
        contactNumber: contactNumber,
        status: status
      },
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        Router.push("/user/dashboard")
      })
      .catch((error) => {
        console.error(error)
      })
  }

  return (
    <div>
      <h2 className="p-b-20">Update Ticket</h2>
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
          </Col>
        </Row>

        {
          status === 'Saved' &&
          <Button className="btn-md btn-success" onClick={ () => setStatus('Saved') } type="submit">Save</Button>
        }
        <Button className="btn-md btn-danger" onClick={ () => setStatus('Reserved') } type="submit">Reserve</Button>
      </Form>
    </div>
  )
}

export default TicketDetails
