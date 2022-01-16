import Router from 'next/router';
import { reAuthenticate } from '../redux/auth.slice';
import { getCookie, TOKEN_KEY, ROLE_KEY, SECRET_KEY } from './cookie';
import { isAuthenticatedRequired } from "./AuthenticatedURLUtil";

// checks if the page is being loaded on the server, and if so, get auth token from the cookie:
// eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
const initialize = async function (ctx: any): Promise<void> {
  //https://github.com/vercel/next.js/issues/2946#issuecomment-329235139
  const isServer = !!ctx.req;
  if (isServer) {
    if (ctx.req.headers.cookie) {
      //TODO(BD) fix this with slice
      await ctx.store.dispatch(reAuthenticate({
        token: getCookie(TOKEN_KEY, ctx.req),
        role: getCookie(ROLE_KEY, ctx.req)
      }));
    }
  }

  if (isAuthenticatedRequired(ctx.pathname)) {
    const token = ctx.store.getState().auth.token;
    if (!token) {
      typeof window !== "undefined"
        ? Router.push("/")
        : ctx.res.writeHead(302, { Location: "/" }).end();
    }
  }
}

export default initialize;
