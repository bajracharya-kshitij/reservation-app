import type { AppProps } from 'next/app'

import { wrapper } from '../redux/store'
import { withRouter } from 'next/router'

import '../styles/globals.css'
import '../styles/main.css'
import '../styles/util.css'

function MyApp({ Component, pageProps }: AppProps) {
  return <Component { ...pageProps } />
}

export default wrapper.withRedux(withRouter(MyApp));
