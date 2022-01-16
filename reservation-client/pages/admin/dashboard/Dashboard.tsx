import React from 'react'
import EventTickets from '../../../components/event-tickets'
import HotEvents from '../../../components/hot-events'
import Router from 'next/router'
import { Button } from 'reactstrap'

const Dashboard = () => {

  const createEvent = () => {
    Router.push("/admin/event");
  }

  return (
    <>
      <EventTickets />
      <HotEvents />
      <Button className="btn-lg btn-success" onClick={ createEvent }>Create New Event</Button>
    </>
  )
}

export default Dashboard
