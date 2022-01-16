import React, { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import axios from 'axios';
import { useSelector } from "react-redux";
import * as AuthenticationSlice from "../../../redux/auth.slice";

interface IEventDetails {
  name: string,
  location: string,
  numberOfTicketsAvailable: number,
  numberOfTicketsSold: number,
  numberOfTicketsReserved: number,
}

const EventDetails = () => {

  const [details, setDetails] = useState<IEventDetails>({
    name: '',
    location: '',
    numberOfTicketsAvailable: 0,
    numberOfTicketsSold: 0,
    numberOfTicketsReserved: 0
  })

  const token = useSelector(AuthenticationSlice.getToken)

  const router = useRouter()
  const { id } = router.query

  const fetchApi = () => {
    axios.get(`/api/event/${id}`, {
      headers: {
        'Authorization': 'Bearer ' + token,
        'Content-Type': 'application/json'
      }
    })
      .then((response) => {
        setDetails(response.data)
      })
      .catch((error) => console.error(error))
  }

  useEffect(() => {
    fetchApi()
  }, [])

  return (
    <div>
      <h2>Event Details</h2>
      <div className="pd-t-20 pd-b-20">
        <div>Name: { details.name }</div>
        <div>Location: { details.location }</div>
        <div>Available Ticket Count: { details.numberOfTicketsAvailable }</div>
        <div>Sold Ticket Count: { details.numberOfTicketsSold }</div>
        <div>Reserved Ticket Count: { details.numberOfTicketsReserved }</div>
      </div>
    </div>
  )
}

export default EventDetails
