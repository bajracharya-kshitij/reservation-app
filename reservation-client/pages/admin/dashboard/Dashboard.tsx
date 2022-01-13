import React from 'react'
import axios from 'axios'

const Dashboard = () => {

  const generate = () => {
    axios.post(`/api/ticket/`, {
      "numberOfTickets": 2,
      "price": 500
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => {
      console.log(response)
    })
  }

  return (
    <button onClick={ generate }>
      Generate Tickets
    </button>
  )
}

export default Dashboard
