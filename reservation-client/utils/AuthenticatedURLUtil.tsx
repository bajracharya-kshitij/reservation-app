const isAuthenticatedRequired = (pathname: string): boolean => {
  return pathname.startsWith("/admin");
}

export {
  isAuthenticatedRequired
}
