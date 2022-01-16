import React, { useState, useEffect } from 'react'
import Router, { useRouter } from 'next/router'
import { useSelector } from 'react-redux';
import axios from 'axios';

import { Button } from 'reactstrap';

import * as AuthenticationSlice from '../../../redux/auth.slice'

const PaymentSingle = () => {

  const [price, setPrice] = useState(0)

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
        setPrice(response.data.price)
      })
      .catch((error) => {
        console.error(error)
      })
  }, [])

  const pay = () => {
    axios.post("/api/payment/pay", {
      ticketNumbers: [ticketNumber]
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
      <h3>Payment for ticket #{ ticketNumber }</h3>
      <p className="p-t-20">
        <div style={ { fontSize: '16px' } }>Total amount to be paid: Rs. { price }</div>
      </p>
      <Button className="btn-md btn-success" onClick={ pay }>Pay</Button>
    </div>
  )
}

export default PaymentSingle
