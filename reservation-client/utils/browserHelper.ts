const isBrowser = (): boolean => {
  return !!process.browser
}

const BrowserHelper = {
  isBrowser
}

export default BrowserHelper