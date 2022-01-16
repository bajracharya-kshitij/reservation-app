import type { AppProps } from 'next/app'

import { wrapper } from '../redux/store'
import { withRouter, useRouter } from 'next/router'

import Head from 'next/head';

import '../styles/globals.css'
import '../styles/main.css'
import '../styles/event.css'
import '../styles/util.css'
import AuthLayout from '../layouts/auth-layout';
import DefaultLayout from '../layouts/default-layout';

function MyApp({ Component, pageProps }: AppProps) {

  let showLayout = false;

  const router = useRouter()

  if (router.pathname.startsWith("/admin") || router.pathname.startsWith("/user")) {
    showLayout = true;
  }

  const LayoutComponent = showLayout ? AuthLayout : DefaultLayout;

  return (
    <>
      <Head>
        <title>Reservation App</title>
        <meta name="viewport" content="initial-scale=1.0, width=device-width" />
      </Head>

      <LayoutComponent>
        <div className="content-wrap">
          <Component { ...pageProps } />
        </div>
      </LayoutComponent>
    </>
  )
}

export default wrapper.withRedux(withRouter(MyApp));