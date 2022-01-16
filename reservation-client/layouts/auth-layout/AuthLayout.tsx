import React from 'react'
import Logout from '../logout';

export const AuthLayout = ({ children }: { children: React.ReactElement }): React.ReactElement => {

  return (

    <div className="page-wrapper chiller-theme">
      <Logout />
      <main className="page-content">
        <header className="cf">
          { children }
        </header>
      </main>
    </div>

  )
};

export default AuthLayout
