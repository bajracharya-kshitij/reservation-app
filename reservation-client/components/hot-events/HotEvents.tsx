import React, { useState, useEffect } from 'react'
import { useSelector } from "react-redux";
import { Container, Row, Col } from "reactstrap";
import axios from 'axios'
import Router from 'next/router'

import * as AuthenticationSlice from "../../redux/auth.slice";

interface IEvent {
  id: number,
  name: string,
  location: string,
  numberOfTicketsAvailable: number
}

const HotEvents = () => {

  const [events, setEvents] = useState<IEvent[]>([])

  const token: string | null = useSelector(AuthenticationSlice.getToken)
  const role: string | null = useSelector(AuthenticationSlice.getRole)

  useEffect(() => {
    axios.get(`/api/event/`,
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        setEvents(response.data)
      })
      .catch((error) => {
        console.error(error)
      })
  }, [])

  const clickOnEvent = (id: number) => {
    if (role === 'ROLE_ADMIN') {
      Router.push(`/admin/event-details/${id}`)
    } else {
      Router.push(`/user/tickets/${id}`)
    }
  }

  return (
    <div className="p-t-30 p-b-30">
      <h2 className="p-t-10 p-b-10">Hot Events</h2>

      <Container fluid>
        <Row style={ { display: 'flex', margin: '0 -15px', flexWrap: 'wrap' } }>
          { events.map((event, index) => {
            return <Col key={ `card-${index}` } style={ { margin: "15px" } }>
              <div className="event-card p-l-50 p-r-50 p-t-50 p-b-50" style={ { cursor: 'pointer' } }
                onClick={ () => clickOnEvent(event.id) }>
                <div className="p-b-20">{ event.name }</div>
                <div className="p-t-20">{ event.location }</div>
                <div className="p-t-20">{ event.numberOfTicketsAvailable } tickets available</div>
              </div>
            </Col>
          })
          }
        </Row>
      </Container>

    </div>
  )
}

export default HotEvents
