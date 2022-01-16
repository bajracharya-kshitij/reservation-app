import type { NextPage } from 'next'
import { useState } from 'react'
import axios from 'axios'
import Router from 'next/router'
import { useDispatch, useSelector } from 'react-redux'
import { Form, Input } from 'reactstrap'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEnvelope, faUnlock } from '@fortawesome/free-solid-svg-icons'

import * as AuthenticationSlice from "../redux/auth.slice"

const Home: NextPage = () => {

  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")
  const token: string | null = useSelector(AuthenticationSlice.getToken)

  const dispatch = useDispatch()

  const formSubmit = (event: any) => {
    event.preventDefault()
    axios.post(`/api/auth/login`,
      { email, password },
      {
        headers: {
          'Authorization': 'Bearer ' + token,
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        const token = response.data.accessToken
        const role = response.data.authorities[0].authority
        dispatch(AuthenticationSlice.authenticate({ token: token, role: role }))
        if (role == "ROLE_ADMIN") {
          Router.push('/admin/dashboard')
        } else {
          Router.push('/user/dashboard')
        }
      })
      .catch((e) => {
        console.error("error", e)
      });
  }

  return (
    <div className="limiter">
      <div className="container-login100">
        <div className="wrap-login100 p-l-50 p-r-50 p-t-77 p-b-30">
          <Form className="login100-form validate-form" onSubmit={ formSubmit }>
            <span className="login100-form-title p-b-55">
              Login
            </span>

            <div className="wrap-input100  m-b-16">
              <Input className="input100 login-input" type="email" name="email" placeholder="Email" onChange={ (e) => setEmail(e.target.value) } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-envelope">
                  <FontAwesomeIcon icon={ faEnvelope } />
                </span>
              </span>
            </div>

            <div className="wrap-input100  m-b-16">
              <Input className="input100 login-input" type="password" name="pass" placeholder="Password" onChange={ (e) => setPassword(e.target.value) } />
              <span className="focus-input100"></span>
              <span className="symbol-input100">
                <span className="lnr lnr-envelope">
                  <FontAwesomeIcon icon={ faUnlock } />
                </span>
              </span>
            </div>

            <div className="container-login100-form-btn p-t-25">
              <button className="login100-form-btn" type="submit">
                Login
              </button>
            </div>
          </Form>
        </div>
      </div>
    </div>
  )
}

export default Home
