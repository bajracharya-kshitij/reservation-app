import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { useSelector } from "react-redux";
import { Container, Row, Col } from "reactstrap";
import * as AuthenticationSlice from "../../redux/auth.slice";
import Router from 'next/router';

interface ITickets {
  ticketNumber: string,
  status: string
}

const MyTickets = () => {

  const [tickets, setTickets] = useState<ITickets[]>([])

  const token = useSelector(AuthenticationSlice.getToken)

  const fetchApi = () => {
    axios.get('/api/ticket/myTickets', {
      headers: {
        'Authorization': 'Bearer ' + token,
        'Content-Type': 'application/json'
      }
    })
      .then((response) => {
        setTickets(response.data)
      })
      .catch((error) => console.error(error))
  }

  useEffect(() => {
    fetchApi()
  }, [])

  const clickOnTicket = (ticketNumber: string) => {
    Router.push(`/user/ticket-details/${ticketNumber}`)
  }

  return (
    <div className="p-t-30 p-b-30">
      <h2 className="p-t-10 p-b-10">My Tickets</h2>

      <Container fluid>
        <Row>
          { tickets.map((ticket, index) => {
            return <Col key={ `card-${index}` }>
              <div className="event-card p-l-50 p-r-50 p-t-50 p-b-50" style={ { cursor: 'pointer' } }
                onClick={ () => clickOnTicket(ticket.ticketNumber) }
              >
                <div className="p-b-20">Ticket #{ ticket.ticketNumber }</div>
                <div className="p-t-20">{ ticket.status }</div>
              </div>
            </Col>
          })
          }
        </Row>
      </Container>

    </div>
  )
}

export default MyTickets
