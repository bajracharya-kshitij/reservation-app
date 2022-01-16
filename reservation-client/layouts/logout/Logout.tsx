import { useDispatch } from "react-redux"
import * as AuthenticationSlice from "../../redux/auth.slice";
import { Button } from 'reactstrap';

const Logout = () => {

  const dispatch = useDispatch()

  const deAuthenticate = (): void => {
    dispatch(AuthenticationSlice.deAuthenticate())
  }

  return (
    <Button className="btn-md btn-danger" style={ { position: 'absolute', right: '30px', marginTop: '10px' } }
      onClick={ deAuthenticate }>Log Out</Button>
  )
}

export default Logout;