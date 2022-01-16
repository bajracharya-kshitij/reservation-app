import Router from "next/router"
import { AnyAction } from 'redux'
import { HYDRATE } from 'next-redux-wrapper'
import { createSlice, PayloadAction } from "@reduxjs/toolkit"

import { AppState } from "./store"

export interface AuthenticationState {
  token: string | null,
  role: string | null
}

const initialState: AuthenticationState = {
  token: null,
  role: null
}

export const slice = createSlice({
  name: "auth",
  initialState: initialState,
  reducers: {
    authenticate: (state, action: PayloadAction<AuthenticationState>): void => {
      state.token = action.payload.token
      state.role = action.payload.role
    },
    deAuthenticate: (state): void => {
      Router.push('/');
      state.token = null
      state.role = null
    },
    reAuthenticate: (state, action: PayloadAction<AuthenticationState>): void => {
      state.token = action.payload.token
      state.role = action.payload.role
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(HYDRATE, (state, action: AnyAction) => {
        state.token = action.payload.auth.token
        state.role = action.payload.auth.role
      })
  }
})

export const {
  authenticate,
  deAuthenticate,
  reAuthenticate
} = slice.actions

export const isLoggedIn = (state: AppState): boolean => {
  return state.auth.token !== null && state.auth.token != undefined
}

export const getToken = (state: AppState): string | null => state.auth.token

export const getRole = (state: AppState): string | null => state.auth.role

export default slice.reducer