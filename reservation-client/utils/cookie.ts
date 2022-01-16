
import cookie from 'js-cookie';
import BrowserHelper from "./browserHelper";

export const setCookie = (key: string, value: string): void => {
  if (BrowserHelper.isBrowser()) {
    cookie.set(key, value, {
      expires: 1,
      path: '/admin'
    });
  }
};

export const removeCookie = (key: string): void => {
  if (BrowserHelper.isBrowser()) {
    cookie.remove(key, {
      expires: 1
    });
  }
};

export const getCookie = (key: string, req?): string => {
  return BrowserHelper.isBrowser() ? getCookieFromBrowser(key) : getCookieFromServer(key, req);
};

const getCookieFromBrowser = (key: string | undefined): string => {
  return cookie.get(key);
};

const getCookieFromServer = (key: string, req) => {
  if (!req.headers.cookie) {
    return undefined;
  }
  const rawCookie = req.headers.cookie
    .split(';')
    .find(c => c.trim().startsWith(`${key}=`));
  if (!rawCookie) {
    return undefined;
  }
  return rawCookie.split('=')[1];
};

export const TOKEN_KEY = "token"
export const ROLE_KEY = "role"
export const SECRET_KEY = "secret"
