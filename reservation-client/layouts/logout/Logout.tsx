import { useDispatch } from "react-redux"
import * as AuthenticationSlice from "../../redux/auth.slice";
import { Button } from 'reactstrap';

const Logout = () => {

  const dispatch = useDispatch()

  const deAuthenticate = (): void => {
    dispatch(AuthenticationSlice.deAuthenticate())
  }

  return (
    <Button outline color="danger" className="log-out-btn" onClick={ deAuthenticate }>Log Out</Button>
  )
}

export default Logout;