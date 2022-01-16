import React from 'react'

export const DefaultLayout = ({ children }: { children: React.ReactElement }): React.ReactElement => {
  return (
    <div>
      { children }
    </div>
  )
}

export default DefaultLayout
