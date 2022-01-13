import type { NextPage } from 'next'
import Head from 'next/head'
import styles from '../styles/Home.module.css'
import Dashboard from './admin/dashboard'

const Home: NextPage = () => {
  return (
    <div className={ styles.container }>
      <Head>
        <title>Reservation App</title>
        <meta name="description" content="Reservation Application" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
    </div>
  )
}

export default Home
