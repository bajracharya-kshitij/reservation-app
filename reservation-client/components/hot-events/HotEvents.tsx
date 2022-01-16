import React, { useState, useEffect } from 'react'
import { useSelector } from "react-redux";
import { Container, Row, Col } from "reactstrap";
import axios from 'axios'

import * as AuthenticationSlice from "../../redux/auth.slice";

interface IEvent {
  name: string,
  location: string
}

const HotEvents = () => {

  const [events, setEvents] = useState<IEvent[]>([])

  const token: string | null = useSelector(AuthenticationSlice.getToken)

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

  return (
    <div className="p-t-30 p-b-30">
      <h2 className="p-t-10 p-b-10">Hot Events</h2>

      <Container fluid>
        <Row>
          { events.map(event => {
            return <Col sm="4">
              <div className="event-card p-l-50 p-r-50 p-t-50 p-b-50">
                <div className="p-b-20">{ event.name }</div>
                <div className="p-t-20">{ event.location }</div>
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
