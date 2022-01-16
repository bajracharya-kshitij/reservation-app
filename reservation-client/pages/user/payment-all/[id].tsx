import React, { useState, useEffect } from 'react'
import Router, { useRouter } from 'next/router'
import { useSelector } from 'react-redux';
import axios from 'axios'
import { Button } from 'reactstrap';

import * as AuthenticationSlice from '../../../redux/auth.slice'

const PaymentAll = () => {

  const [name, setName] = useState('')
  const [totalTickets, setTotalTickets] = useState(0)
  const [totalPrice, setTotalPrice] = useState(0)
  const [ticketNumbers, setTicketNumbers] = useState([])

  const router = useRouter()
  const { id } = router.query

  const token: string | null = useSelector(AuthenticationSlice.getToken)

  useEffect(() => {
    axios.get(`/api/event/${id}/payment`,
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        setName(response.data.name)
        setTotalTickets(response.data.totalTickets)
        setTotalPrice(response.data.totalPrice)
        setTicketNumbers(response.data.ticketNumbers)
      })
      .catch((error) => {
        console.error(error)
      })
  }, [])

  const pay = () => {
    axios.post("/api/payment/pay", {
      ticketNumbers: ticketNumbers
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
      <h3>Payment for all pending tickets of event { name }</h3>
      <p className="p-t-20">
        <div style={ { fontSize: '16px' } }>Total number of tickets: { totalTickets }</div>
        <div style={ { fontSize: '16px' } }>Total amount to be paid: Rs. { totalPrice }</div>
      </p>
      <Button className="btn-md btn-success" onClick={ pay }>Pay</Button>
    </div>
  )
}

export default PaymentAll
